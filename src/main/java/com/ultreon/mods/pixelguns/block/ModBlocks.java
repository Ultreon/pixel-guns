package com.ultreon.mods.pixelguns.block;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block UFO_INTERIOR = ModBlocks.register("ufo_interior", new UfoInteriorBlock(FabricBlockSettings.of(Material.METAL)));
    public static final Block WEAPON_TABLE = ModBlocks.register("weapon_table", new WeaponTableBlock(FabricBlockSettings.of(Material.WOOD).strength(2.5f).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, PixelGuns.res(name), block);
    }
}