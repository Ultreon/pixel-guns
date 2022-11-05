package com.ultreon.mods.pixelguns.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BlueprintBundleItem extends Item {
    public BlueprintBundleItem(Properties settings) {
        super(settings);
    }

    public InteractionResultHolder<ItemStack> use(@NotNull Level world, Player user, @NotNull InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        for (int i = 0; i < BlueprintItem.BLUEPRINT_ITEM_LIST.size(); ++i) {
            ItemStack bluePrint = new ItemStack(BlueprintItem.BLUEPRINT_ITEM_LIST.get(i));
            if (user.getInventory().getFreeSlot() > -1) {
                user.addItem(bluePrint);
                continue;
            }
            user.spawnAtLocation(bluePrint.getItem());
        }
        user.level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.2f, ((user.getRandom().nextFloat() - user.getRandom().nextFloat()) * 0.7f + 1.0f) * 2.0f);
        user.awardStat(Stats.ITEM_USED.get(this));
        if (!user.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}

