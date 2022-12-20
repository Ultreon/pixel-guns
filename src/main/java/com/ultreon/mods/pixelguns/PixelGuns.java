package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.block.ModBlocks;
import com.ultreon.mods.pixelguns.item.gun.AbstractGunItem;
import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PixelGuns implements ModInitializer {
    public static final String MOD_ID = "pixel_guns";
    public static final Logger LOGGER = LoggerFactory.getLogger("pixel_guns");
    public static final Identifier RECOIL_PACKET_ID = new Identifier("pixel_guns", "recoil");
    
    public static final String NBT_NAME = "pixelGuns";

    public static Identifier res(String path) {
        return new Identifier(MOD_ID, path);
    }

    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModSounds.registerSounds();
        ServerPlayNetworking.registerGlobalReceiver(new Identifier(MOD_ID, "reload"), (server, player, serverPlayNetworkHandler, buf, packetSender) -> {
            if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof AbstractGunItem) {
                player.getStackInHand(Hand.MAIN_HAND).getOrCreateNbt().putBoolean("isReloading", buf.readBoolean());
            }
        });

        Config.init();
    }
}