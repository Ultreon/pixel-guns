package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.NbtNames;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
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

public class InfinityGunItem extends GunItem implements IAnimatable {
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
        Vec3d vec3d = user.getPos().add(0.0D, 1.600000023841858D, 0.0D);
        Vec3d vec3d2 = user.raycast(5, 5, true).getPos();
        Vec3d vec3d3 = vec3d2.normalize();
        if (world instanceof ServerWorld serverWorld)
        for(int i = 1; i < MathHelper.floor(vec3d2.length()) + 7; ++i) {
            Vec3d vec3d4 = vec3d.add(vec3d3.multiply((double)i));
            serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM, vec3d4.x, vec3d4.y, vec3d4.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        }
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