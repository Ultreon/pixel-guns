package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.item.GunItem;
import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PixelGuns implements ModInitializer {
    public static final String MOD_ID = "pixel_guns";
    public static final Logger LOGGER = LoggerFactory.getLogger("pixel_guns");
    public static final ResourceLocation RECOIL_PACKET_ID = new ResourceLocation("pixel_guns", "recoil");
    public static final CreativeModeTab MISC = FabricItemGroupBuilder.build(new ResourceLocation("pixel_guns", "misc"), () -> new ItemStack(ModItems.MAGNUM_REVOLVER_BLUEPRINT));
    public static final CreativeModeTab GUNS = FabricItemGroupBuilder.build(new ResourceLocation("pixel_guns", "guns"), () -> new ItemStack(ModItems.MAGNUM_REVOLVER));
    
    public static final String NBT_NAME = "pixelGuns";

    public static ResourceLocation res(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public void onInitialize() {
        ModItems.registerModItems();
        ModSounds.registerSounds();
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation(MOD_ID, "reload"), (server, player, serverPlayNetworkHandler, buf, packetSender) -> {
            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof GunItem) {
                player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putBoolean("isReloading", buf.readBoolean());
            }
        });

        Config.init();
    }
}