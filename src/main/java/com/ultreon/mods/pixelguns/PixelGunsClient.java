package com.ultreon.mods.pixelguns;

import com.mojang.blaze3d.platform.InputConstants;
import com.ultreon.mods.pixelguns.client.model.EnergyOrbModel;
import com.ultreon.mods.pixelguns.client.renderer.*;
import com.ultreon.mods.pixelguns.entity.ModEntities;
import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.util.ModelPredicateProvider;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.minecraft.client.KeyMapping;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@Environment(value = EnvType.CLIENT)
public class PixelGunsClient implements ClientModInitializer {
    public static KeyMapping reloadToggle = new KeyMapping("key.pixel_guns.reloadtoggle", InputConstants.Type.KEYSYM, 82, "category.pixel_guns.binds");

    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(reloadToggle);

        ModelPredicateProvider.registerModels();

        EntityRendererRegistry.register(ModEntities.BULLET_ENTITY_TYPE, BulletRenderer::new);
        EntityRendererRegistry.register(ModEntities.ENERGY_ORB_ENTITY_TYPE, EnergyOrbRenderer::new);
        EntityRendererRegistry.register(ModEntities.GRENADE, GrenadeEntityRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new ArmoredArmorRenderer(), ModItems.ARMORED_VEST);
        
        EntityModelLayerRegistry.registerModelLayer(EnergyOrbModel.LAYER_LOCATION, EnergyOrbModel::createBodyLayer);
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
        
        GeoItemRenderer.registerItemRenderer(ModItems.INFINITY_GUN, new InfinityGunRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.KATANA, new KatanaItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.CROWBAR, new CrowbarItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.GRENADE, new GrenadeItemRenderer());
    }
}