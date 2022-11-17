package com.ultreon.mods.pixelguns.item;

import java.util.function.Predicate;

import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

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

public class GrenadeItem extends ProjectileWeaponItem implements IAnimatable, ISyncable {

    public GrenadeItem(Properties settings) {
        super(settings);
		GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof Player)) return;
		if (!world.isClientSide) {
			stack.removeTagKey("GeckoLibID");
		}
		if (remainingUseTicks < 0) return;

        Player playerEntity = (Player) user;

        if (stack.isEmpty() && !playerEntity.isCreative()) return;
		int useTicks = this.getUseDuration(stack) - remainingUseTicks;
		if (useTicks < 10) return;
		float throwStrength = GrenadeItem.getThrowStrength(useTicks);
        if (!world.isClientSide) {
			GrenadeEntity grenade = new GrenadeEntity(world, playerEntity);
			grenade.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0f, throwStrength * 1.5f, 1.0f);
            world.addFreshEntity(grenade);
        }
        if (!playerEntity.isCreative()) {
            stack.shrink(1);
        }
		user.swing(InteractionHand.MAIN_HAND);
        playerEntity.awardStat(Stats.ITEM_USED.get(this));
    }

    public static float getThrowStrength(int useTicks) {
		float throwStrength = (float) useTicks / 30;
		if (throwStrength < 1) return throwStrength;
        return 1;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 120;
    }

	@Override
	public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
		return false;
	}

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);

		if (!world.isClientSide) {
			dispatchAnimationToClients((ServerLevel) world, player, stack, GrenadeState.PULLING_PIN);
		}
		return InteractionResultHolder.fail(stack);
    }

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
		if (user instanceof Player) {
			((Player) user).getCooldowns().addCooldown(this, 20);
		}
        stack.removeTagKey("GeckoLibID");
        return stack;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ModItems.GRENADE);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }


    /*
     * Animation Side
     */

	private AnimationFactory factory = new AnimationFactory(this);

	private void dispatchAnimationToClients(ServerLevel world, Player player, ItemStack stack, int animation) {
		final int id = GeckoLibIdTracker.from(world).getNextId(GeckoLibIdTracker.Type.ITEM);
		stack.getOrCreateTag().putInt("GeckoLibID", id);
		GeckoLibNetwork.syncAnimation(player, this, id, animation);
		for (Player otherPlayer : PlayerLookup.tracking(player)) {
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