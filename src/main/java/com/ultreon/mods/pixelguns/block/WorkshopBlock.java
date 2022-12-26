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
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

// TODO Block facing directions like the Anvil
// TODO Fix non transparent part of texture
public class WorkshopBlock extends Block {

    private static final Text TITLE = Text.of("Weapon Table");
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);

    public WorkshopBlock(Settings settings) {
        super(settings);
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
