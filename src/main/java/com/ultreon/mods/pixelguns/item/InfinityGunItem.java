package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.NbtNames;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.List;

public class InfinityGunItem extends GunItem implements IAnimatable {
    public static final List<InfinityGunItem> BLUEPRINT_ITEM_LIST = new ArrayList<>();

    public InfinityGunItem(Properties settings) {
        super(settings, 80f, 30, 5, ModItems.ENERGY_GUN_BATTERY,
                40, 0, 20, 1, 1,
                SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_DOOR_OPEN,
                SoundEvents.BEACON_DEACTIVATE, 1, false, 5, -1, -1);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return new AnimationFactory(this);
    }

    @Override
    public void shoot(Level world, Player user, ItemStack stack) {
        super.shoot(world, user, stack);
        CompoundTag infinityGun = stack.getOrCreateTagElement(NbtNames.INFINITY_GUN);
        infinityGun.putBoolean(NbtNames.IS_SHOOTING, true);
    }

    @Override
    public boolean useOnRelease(ItemStack stack) {
        CompoundTag infinityGun = stack.getOrCreateTagElement(NbtNames.INFINITY_GUN);
        if (infinityGun.getBoolean(NbtNames.IS_SHOOTING)) {
            infinityGun.putBoolean(NbtNames.IS_SHOOTING, false);
            return true;
        }

        return super.useOnRelease(stack);
    }
}