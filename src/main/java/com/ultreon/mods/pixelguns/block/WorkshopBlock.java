package com.ultreon.mods.pixelguns.block;

import com.ultreon.mods.pixelguns.screen.WorkshopScreenHandler;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

// TODO Fix non transparent part of texture
// TODO Fix adjacent blocks preventing saw from being rendered
public class WorkshopBlock extends HorizontalFacingBlock implements Waterloggable {

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private static final Text TITLE = Text.of("Weapon Table");
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    public WorkshopBlock(Settings settings) {
        super(settings);
        setDefaultState(
                getDefaultState()
                .with(WATERLOGGED, false)
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState blockState, Direction direction, BlockState blockState2, WorldAccess worldAccess, BlockPos blockPos, BlockPos blockPos2) {
        if (blockState.get(WATERLOGGED)) {
            worldAccess.createAndScheduleFluidTick(blockPos, Fluids.WATER, Fluids.WATER.getTickRate(worldAccess));
        }
        return super.getStateForNeighborUpdate(blockState, direction, blockState2, worldAccess, blockPos, blockPos2);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx)
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER)
                .with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }

    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState blockState, World world, BlockPos blockPos) {
        return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> new WorkshopScreenHandler(i, playerInventory, new Inventory() {
            @Override
            public int size() {
                return 9;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public ItemStack getStack(int i) {
                return null;
            }

            @Override
            public ItemStack removeStack(int i, int j) {
                return null;
            }

            @Override
            public ItemStack removeStack(int i) {
                return null;
            }

            @Override
            public void setStack(int i, ItemStack itemStack) {

            }

            @Override
            public void markDirty() {

            }

            @Override
            public boolean canPlayerUse(PlayerEntity playerEntity) {
                return false;
            }

            @Override
            public void clear() {

            }
        }), TITLE);
    }
}
