package com.ultreon.mods.pixelguns.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractBulletEntity extends ThrowableItemProjectile {
    public AbstractBulletEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public AbstractBulletEntity(EntityType<? extends ThrowableItemProjectile> entityType, double d, double e, double f, Level level) {
        super(entityType, d, e, f, level);
    }

    public AbstractBulletEntity(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity livingEntity, Level level) {
        super(entityType, livingEntity, level);
    }

    public abstract void setAccel(Vec3 velocity);
}
