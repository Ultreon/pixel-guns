package com.ultreon.mods.pixelguns.item;

import java.util.function.Predicate;

import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;
import software.bernie.geckolib3.world.storage.GeckoLibIdTracker;

public class GrenadeItem extends RangedWeaponItem implements IAnimatable, ISyncable {

    public GrenadeItem(Settings settings) {
        super(settings);
		GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity)) return;
		if (!world.isClient) {
			stack.removeSubNbt("GeckoLibID");
		}
		if (remainingUseTicks < 0) return;

        PlayerEntity playerEntity = (PlayerEntity) user;

        if (stack.isEmpty() && !playerEntity.isCreative()) return;
		int useTicks = this.getMaxUseTime(stack) - remainingUseTicks;
		if (useTicks < 10) return;
		float throwStrength = GrenadeItem.getThrowStrength(useTicks);
        if (!world.isClient) {
			GrenadeEntity grenade = new GrenadeEntity(world, playerEntity);
			grenade.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0f, throwStrength * 1.5f, 1.0f);
            world.spawnEntity(grenade);
        }
        if (!playerEntity.isCreative()) {
            stack.decrement(1);
        }
		user.swingHand(Hand.MAIN_HAND);
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    public static float getThrowStrength(int useTicks) {
		float throwStrength = (float) useTicks / 30;
		if (throwStrength < 1) return throwStrength;
        return 1;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 120;
    }

	@Override
	public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
		return false;
	}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);

		if (!world.isClient) {
			dispatchAnimationToClients((ServerWorld) world, player, stack, GrenadeState.PULLING_PIN);
		}
		return TypedActionResult.fail(stack);
    }

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (user instanceof PlayerEntity) {
			((PlayerEntity) user).getItemCooldownManager().set(this, 20);
		}
        stack.removeSubNbt("GeckoLibID");
        return stack;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ModItems.GRENADE);
    }

    @Override
    public int getRange() {
        return 15;
    }


    /*
     * Animation Side
     */

	private AnimationFactory factory = new AnimationFactory(this);

	private void dispatchAnimationToClients(ServerWorld world, PlayerEntity player, ItemStack stack, int animation) {
		final int id = GeckoLibIdTracker.from(world).getNextId(GeckoLibIdTracker.Type.ITEM);
		stack.getOrCreateNbt().putInt("GeckoLibID", id);
		GeckoLibNetwork.syncAnimation(player, this, id, animation);
		for (PlayerEntity otherPlayer : PlayerLookup.tracking(player)) {
			GeckoLibNetwork.syncAnimation(otherPlayer, this, id, animation);
		}
	}

	private PlayState predicate(AnimationEvent<GrenadeItem> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("grenade.throw"));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<GrenadeItem>(this, "controller", 1, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

	private class GrenadeState {
		public static final int PULLING_PIN = 1;
	}

	@Override
	public void onAnimationSync(int id, int state) {
		@SuppressWarnings("unchecked")
		final AnimationController<GrenadeItem> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controller");
		switch (state) {
			case GrenadeState.PULLING_PIN:
				controller.setAnimation(new AnimationBuilder().addAnimation("grenade.throw"));
				break;
		}
	}
}