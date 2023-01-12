package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.item.gun.GunItem;

import com.ultreon.mods.pixelguns.registry.BlockRegistry;
import com.ultreon.mods.pixelguns.registry.EntityRegistry;
import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.registry.SoundRegistry;
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

    public void onInitialize() {
        new EntityRegistry();
        new ItemRegistry();
        new BlockRegistry();
        new SoundRegistry();


        ServerPlayNetworking.registerGlobalReceiver(new Identifier(MOD_ID, "reload"), (server, player, serverPlayNetworkHandler, buf, packetSender) -> {
            if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof GunItem) {
                player.getStackInHand(Hand.MAIN_HAND).getOrCreateNbt().putBoolean("isReloading", buf.readBoolean());
            }
        });

        Config.init();
    }
}