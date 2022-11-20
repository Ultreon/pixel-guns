package com.ultreon.mods.pixelguns.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class AbstractBulletEntity extends ThrownItemEntity {
    public AbstractBulletEntity(EntityType<? extends ThrownItemEntity> entityType, World level) {
        super(entityType, level);
    }

    public AbstractBulletEntity(EntityType<? extends ThrownItemEntity> entityType, double d, double e, double f, World level) {
        super(entityType, d, e, f, level);
    }

    public AbstractBulletEntity(EntityType<? extends ThrownItemEntity> entityType, LivingEntity livingEntity, World level) {
        super(entityType, livingEntity, level);
    }

    public abstract void setAccel(Vec3d velocity);
}
