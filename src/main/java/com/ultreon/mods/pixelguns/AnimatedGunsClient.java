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
public class AnimatedGunsClient implements ClientModInitializer {
    public static KeyMapping reloadToggle = new KeyMapping("key.anim_guns.reloadtoggle", InputConstants.Type.KEYSYM, 82, "category.anim_guns.binds");

    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(reloadToggle);
        ModelPredicateProvider.registerModels();
        EntityRendererRegistry.register(AnimatedGuns.BulletEntityType, BulletEntityRenderer::new);
        ClientPlayNetworking.registerGlobalReceiver(AnimatedGuns.RECOIL_PACKET_ID, (client, handler, buf, sender) -> {
            float kick = buf.readFloat();
            client.execute(() -> {
                if (client.player != null) {
                    client.player.setXRot(kick);
                }
            });
        });
    }
}

