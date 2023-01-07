package com.ultreon.mods.pixelguns.entity.projectile;

import com.ultreon.mods.pixelguns.registry.ModEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RocketEntity extends Entity implements IAnimatable {

    public RocketEntity(EntityType<? extends RocketEntity> entityType, World world) {
        super(entityType, world);
    }

    public RocketEntity(World world) {
        super(ModEntities.ROCKET, world);
    }

    @Override
    public void tick() {
        this.setPosition(this.getPos().add(this.getVelocity()));
        super.tick();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbtCompound) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbtCompound) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void initDataTracker() {

    }

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    @Override
    public void registerControllers(AnimationData animationData) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
