package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.NbtNames;
import com.ultreon.mods.pixelguns.item.gun.AbstractGunItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class InfinityGunItem extends AbstractGunItem implements IAnimatable {
    public static final List<InfinityGunItem> BLUEPRINT_ITEM_LIST = new ArrayList<>();

    public InfinityGunItem(Settings settings) {
        super(settings, 80f, 30, 5, ModItems.ENERGY_GUN_BATTERY,
                40, 0, 20, 1, 1,
                SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_IRON_DOOR_OPEN,
                SoundEvents.BLOCK_BEACON_DEACTIVATE, 1, false, 5, -1, -1);
    }

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void shoot(World world, PlayerEntity user, ItemStack stack) {
        super.shoot(world, user, stack);
        NbtCompound infinityGun = stack.getOrCreateSubNbt(NbtNames.INFINITY_GUN);
        infinityGun.putBoolean(NbtNames.IS_SHOOTING, true);
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        NbtCompound infinityGun = stack.getOrCreateSubNbt(NbtNames.INFINITY_GUN);
        if (infinityGun.getBoolean(NbtNames.IS_SHOOTING)) {
            infinityGun.putBoolean(NbtNames.IS_SHOOTING, false);
            return true;
        }

        return super.isUsedOnRelease(stack);
    }
}