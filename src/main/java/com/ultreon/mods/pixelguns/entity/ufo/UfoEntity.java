package com.ultreon.mods.pixelguns.entity.ufo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class UfoEntity extends AbstractUfoEntity {

    public UfoEntity(EntityType<? extends AbstractUfoEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public float getThirdPersonCameraDistance() {
        return 4 * this.getScale();
    }

    @Override
    public float getScale() {
        return 4.0f;
    }

    @Override
    public Entity abduct() {
        this.getBlockPos();
        return null;
    }
}