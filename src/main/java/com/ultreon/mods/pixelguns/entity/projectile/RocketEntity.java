package com.ultreon.mods.pixelguns.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.world.World;

public class RocketEntity extends ProjectileEntity {

    public RocketEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {

    }
}
