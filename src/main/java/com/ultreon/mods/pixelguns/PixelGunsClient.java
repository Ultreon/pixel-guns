package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.client.armor.renderer.ArmoredArmorRenderer;
import com.ultreon.mods.pixelguns.client.armor.renderer.HazardArmorRenderer;
import com.ultreon.mods.pixelguns.client.entity.renderer.*;
import com.ultreon.mods.pixelguns.client.gui.ingame.WorkshopScreen;
import com.ultreon.mods.pixelguns.client.item.renderer.*;
import com.ultreon.mods.pixelguns.registry.BlockRegistry;
import com.ultreon.mods.pixelguns.registry.EntityRegistry;
import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.registry.ModScreenHandlerType;
import com.ultreon.mods.pixelguns.util.ModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@Environment(value = EnvType.CLIENT)
public class PixelGunsClient implements ClientModInitializer {
    public static KeyBinding reloadToggle = new KeyBinding("key.pixel_guns.reloadtoggle", InputUtil.Type.KEYSYM, 82, "category.pixel_guns.binds");

    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.WORKSHOP, RenderLayer.getCutout());
        KeyBindingHelper.registerKeyBinding(reloadToggle);

        HandledScreens.register(ModScreenHandlerType.WORKSHOP_SCREEN_HANDLER, WorkshopScreen::new);

        ModelPredicateProvider.registerModels();

        EntityRendererRegistry.register(EntityRegistry.GRENADE, GrenadeEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ROCKET, RocketEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.NUCLEAR_BOMB, NuclearBombEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.NUCLEAR_EXPLOSION, NuclearExplosionEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.GAS, GasRenderer::new);

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