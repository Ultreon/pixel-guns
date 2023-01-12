package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.client.armor.renderer.ArmoredArmorRenderer;
import com.ultreon.mods.pixelguns.client.armor.renderer.HazardArmorRenderer;
import com.ultreon.mods.pixelguns.client.gui.ingame.WorkshopScreen;
import com.ultreon.mods.pixelguns.client.item.renderer.*;
import com.ultreon.mods.pixelguns.registry.*;
import com.ultreon.mods.pixelguns.util.ModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@Environment(value = EnvType.CLIENT)
public class PixelGunsClient implements ClientModInitializer {


    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.WORKSHOP, RenderLayer.getCutout());

        KeybindRegistry.registerKeybinds();

        HandledScreens.register(ScreenHandlerRegistry.WORKSHOP_SCREEN_HANDLER, WorkshopScreen::new);

        ModelPredicateProvider.registerModels();

        EntityRegistry.registerRenderers();

        GeoArmorRenderer.registerArmorRenderer(new ArmoredArmorRenderer(), ItemRegistry.ARMORED_VEST);
        GeoArmorRenderer.registerArmorRenderer(new HazardArmorRenderer(), ItemRegistry.GAS_MASK);

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
        
        GeoItemRenderer.registerItemRenderer(ItemRegistry.INFINITY_GUN, new InfinityGunItemRenderer());
        GeoItemRenderer.registerItemRenderer(ItemRegistry.ROCKET_LAUNCHER, new RocketLauncherItemRenderer());
        GeoItemRenderer.registerItemRenderer(ItemRegistry.KATANA, new KatanaItemRenderer());
        GeoItemRenderer.registerItemRenderer(ItemRegistry.CROWBAR, new CrowbarItemRenderer());
        GeoItemRenderer.registerItemRenderer(ItemRegistry.GRENADE, new GrenadeItemRenderer());
    }
}