package com.ultreon.mods.pixelguns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

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
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return VoxelShapes.cuboid(-0.4f, 0.0f, -0.4f, 0.5f, 0.3f, 1.4f);
    }
}