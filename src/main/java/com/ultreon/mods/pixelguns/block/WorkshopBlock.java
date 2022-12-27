package com.ultreon.mods.pixelguns.block;

import com.ultreon.mods.pixelguns.screen.WorkshopScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
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

// TODO Fix non transparent part of texture
// TODO Fix adjacent blocks preventing saw from being rendered
public class WorkshopBlock extends EasyBlock {

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
    protected boolean isWaterloggable() {
        return true;
    }

    @Override
    protected boolean isRotatable() {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext ctx) {
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

    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
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
