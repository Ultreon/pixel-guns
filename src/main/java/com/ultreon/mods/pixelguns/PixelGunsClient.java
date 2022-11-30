package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.client.armor.renderer.*;
import com.ultreon.mods.pixelguns.client.entity.model.EnergyOrbEntityModel;
import com.ultreon.mods.pixelguns.client.entity.renderer.*;
import com.ultreon.mods.pixelguns.client.item.renderer.*;
import com.ultreon.mods.pixelguns.entity.ModEntities;
import com.ultreon.mods.pixelguns.entity.ufo.UfoInput;
import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.util.ModelPredicateProvider;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@Environment(value = EnvType.CLIENT)
public class PixelGunsClient implements ClientModInitializer {
    public static KeyBinding reloadToggle = new KeyBinding("key.pixel_guns.reloadtoggle", InputUtil.Type.KEYSYM, 82, "category.pixel_guns.binds");

    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(reloadToggle);

        ModelPredicateProvider.registerModels();

        EntityRendererRegistry.register(ModEntities.ENERGY_ORB_ENTITY_TYPE, EnergyOrbEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.GRENADE, GrenadeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.NUCLEAR_BOMB, NuclearBombEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.NUCLEAR_EXPLOSION, NuclearExplosionEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.GAS, GasRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new ArmoredArmorRenderer(), ModItems.ARMORED_VEST);
        GeoArmorRenderer.registerArmorRenderer(new HazardArmorRenderer(), ModItems.GAS_MASK);
        
        EntityModelLayerRegistry.registerModelLayer(EnergyOrbEntityModel.LAYER_LOCATION, EnergyOrbEntityModel::createBodyLayer);
        ClientPlayNetworking.registerGlobalReceiver(PixelGuns.RECOIL_PACKET_ID, (client, handler, buf, sender) -> {
            float kick = buf.readFloat();
            client.execute(() -> {
                if (client.player != null) {
                    if (Config.DO_RECOIL.get()) {
                        client.player.setPitch(kick);
                    }
                }
            });
        });
        
        GeoItemRenderer.registerItemRenderer(ModItems.INFINITY_GUN, new InfinityGunItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.ROCKET_LAUNCHER, new RocketLauncherItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.KATANA, new KatanaItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.CROWBAR, new CrowbarItemRenderer());
        GeoItemRenderer.registerItemRenderer(ModItems.GRENADE, new GrenadeItemRenderer());

        EntityRendererRegistry.register(ModEntities.UFO, (ctx) -> new UfoEntityRenderer(ctx));

        UfoInput.registerKeybinds();
    }
}