package com.ultreon.mods.pixelguns.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {
    public static int itemCountInInventory(Player player, Item item) {
        int itemCount = 0;
        for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (currentStack.isEmpty() || !currentStack.sameItemStackIgnoreDurability(new ItemStack(item))) continue;
            itemCount += currentStack.getCount();
        }
        return Math.max(itemCount, 0);
    }

    public static void removeItemFromInventory(Player player, Item item, int count) {
        int itemsToRemove = count;
        for (int i = 0; i < player.getInventory().getContainerSize(); ++i) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (currentStack.isEmpty() || !currentStack.sameItemStackIgnoreDurability(new ItemStack(item))) continue;
            if (currentStack.getCount() > itemsToRemove) {
                currentStack.shrink(itemsToRemove);
                break;
            }
            currentStack.setCount(0);
            if ((itemsToRemove -= currentStack.getCount()) == 0) break;
        }
    }
}

