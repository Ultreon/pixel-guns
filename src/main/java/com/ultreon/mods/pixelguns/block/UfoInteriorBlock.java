package com.ultreon.mods.pixelguns.block;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class UfoInteriorBlock extends EasyBlock {
    public UfoInteriorBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean isWaterloggable() {
        return true;
    }

    @Override
    protected boolean isRotatable() {
        return true;
    }

    @Override
    public VoxelShape getShape() {
        return VoxelShapes.cuboid(0.0f, 0.0f, 0.2f, 1.0f, 0.3f, 1.0f);
    }
}