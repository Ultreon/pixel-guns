package com.ultreon.mods.pixelguns.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public abstract class EasyBlock extends Block {

    public EasyBlock(Settings settings) {
        super(settings);
    }

    protected abstract boolean isWaterloggable();
    protected abstract boolean isRotatable();
    protected VoxelShape getShape() {
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction dir = state.get(Properties.HORIZONTAL_FACING);
        return switch (dir) {
            case NORTH -> rotateShape(0, this.getShape());
            case SOUTH -> rotateShape(2, this.getShape());
            case EAST -> rotateShape(1, this.getShape());
            case WEST -> rotateShape(3, this.getShape());
            default -> VoxelShapes.fullCube();
        };
    }

    private static VoxelShape rotateShape(int times, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        if (isRotatable()) {
            return state.with(Properties.FACING, rotation.rotate(state.get(Properties.FACING)));
        }
        return super.rotate(state, rotation);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        if (isRotatable()) {
            return state.rotate(mirror.getRotation(state.get(Properties.FACING)));
        }
        return super.mirror(state, mirror);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState blockState2, WorldAccess worldAccess, BlockPos blockPos, BlockPos blockPos2) {
        if (isWaterloggable() && state.get(Properties.WATERLOGGED)) {
            worldAccess.createAndScheduleFluidTick(blockPos, Fluids.WATER, Fluids.WATER.getTickRate(worldAccess));
        }
        return super.getStateForNeighborUpdate(state, direction, blockState2, worldAccess, blockPos, blockPos2);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        if (isWaterloggable()) {
            builder.add(Properties.WATERLOGGED);
        }
        if (isRotatable()) {
            builder.add(Properties.HORIZONTAL_FACING);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = super.getPlacementState(ctx);
        if (isWaterloggable()) {
            blockState = blockState.with(Properties.WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
        }
        if (isRotatable()) {
            blockState = blockState.with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
        }
        return blockState;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (isWaterloggable()) {
            return state.get(Properties.WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
        }
            return super.getFluidState(state);
    }
}
