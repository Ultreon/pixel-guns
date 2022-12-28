package com.ultreon.mods.pixelguns.item;

import java.util.function.Predicate;

import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;
import com.ultreon.mods.pixelguns.registry.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class GrenadeItem extends RangedWeaponItem implements IAnimatable {

    public GrenadeItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity playerEntity)) return;

		if (remainingUseTicks < 0) return;

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

	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}