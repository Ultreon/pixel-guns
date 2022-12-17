package com.ultreon.mods.pixelguns.block;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block UFO_INTERIOR = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(PixelGuns.MOD_ID, "ufo_interior"), UFO_INTERIOR);
    }
}