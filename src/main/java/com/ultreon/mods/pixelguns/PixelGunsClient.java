package com.ultreon.mods.pixelguns;

import com.mojang.blaze3d.platform.InputConstants;
import com.ultreon.mods.pixelguns.entity.projectile.BulletEntityRenderer;
import com.ultreon.mods.pixelguns.util.ModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;

@Environment(value = EnvType.CLIENT)
public class PixelGunsClient implements ClientModInitializer {
    public static KeyMapping reloadToggle = new KeyMapping("key.pixel_guns.reloadtoggle", InputConstants.Type.KEYSYM, 82, "category.pixel_guns.binds");

    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(reloadToggle);
        ModelPredicateProvider.registerModels();
        EntityRendererRegistry.register(PixelGuns.BulletEntityType, BulletEntityRenderer::new);
        ClientPlayNetworking.registerGlobalReceiver(PixelGuns.RECOIL_PACKET_ID, (client, handler, buf, sender) -> {
            float kick = buf.readFloat();
            client.execute(() -> {
                if (client.player != null) {
                    if (Config.DO_RECOIL.get()) {
                        client.player.setXRot(kick);
                    }
                }
            });
        });
    }
}

