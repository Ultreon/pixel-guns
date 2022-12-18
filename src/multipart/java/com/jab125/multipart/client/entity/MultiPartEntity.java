/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package com.jab125.multipart.client.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;

public class MultiPartEntity<T extends Entity & MultiPartHolder<T, R>, R extends MultiPartEntity<T, R>>
        extends Entity {
    public final T owner;
    public final String name;
    private final EntityDimensions partDimensions;

    public MultiPartEntity(T entity, String string, float f, float g) {
        super(entity.getType(), entity.world);
        this.partDimensions = EntityDimensions.changing(f, g);
        this.calculateDimensions();
        this.owner = entity;
        this.name = string;
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbtCompound) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbtCompound) {
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean damage(DamageSource damageSource, float f) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        }
        return this.owner.damagePart((R) this, damageSource, f);
    }

    @Override
    public boolean isPartOf(Entity entity) {
        return this == entity || this.owner == entity;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityDimensions getDimensions(EntityPose entityPose) {
        return this.partDimensions;
    }

    @Override
    public boolean shouldSave() {
        return false;
    }
}

