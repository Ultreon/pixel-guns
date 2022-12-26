package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.registry.ModItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModCreativeTab {
    public static final ItemGroup MISC = FabricItemGroupBuilder.build(new Identifier("pixel_guns", "misc"), () -> new ItemStack(ModItems.MAGNUM_REVOLVER_BLUEPRINT));
    public static final ItemGroup WEAPONS = FabricItemGroupBuilder.build(new Identifier("pixel_guns", "guns"), () -> new ItemStack(ModItems.MAGNUM_REVOLVER));
}
