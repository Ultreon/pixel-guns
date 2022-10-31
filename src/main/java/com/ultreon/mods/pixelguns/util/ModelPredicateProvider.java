package com.ultreon.mods.pixelguns.util;

import com.ultreon.mods.pixelguns.item.GunItem;
import com.ultreon.mods.pixelguns.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;

public class ModelPredicateProvider {
    public static void registerModels() {
        ModelPredicateProvider.registerGun(ModItems.PISTOL);
        ModelPredicateProvider.registerGun(ModItems.HEAVY_PISTOL);
        ModelPredicateProvider.registerGun(ModItems.MAGNUM_REVOLVER);
        ModelPredicateProvider.registerGun(ModItems.MACHINE_PISTOL);
        ModelPredicateProvider.registerGun(ModItems.LIGHT_ASSAULT_RIFLE);
        ModelPredicateProvider.registerGun(ModItems.HEAVY_ASSAULT_RIFLE);
        ModelPredicateProvider.registerGun(ModItems.COMBAT_SHOTGUN);
        ModelPredicateProvider.registerGun(ModItems.CLASSIC_SNIPER_RIFLE);
    }

    public static void registerGun(Item gun) {
        ItemProperties.register(gun, new ResourceLocation("pixel_guns", "load_tick"), (stack, world, entity, seed) -> entity != null && stack.getOrCreateTag().getBoolean("isReloading") ? (float) stack.getOrCreateTag().getInt("reloadTick") / 200.0f : 0.0f);
        ItemProperties.register(gun, new ResourceLocation("pixel_guns", "loading"), (stack, world, entity, seed) -> entity != null && stack.getOrCreateTag().getBoolean("isReloading") ? 1.0f : 0.0f);
        ItemProperties.register(gun, new ResourceLocation("pixel_guns", "aiming"), (stack, world, entity, seed) -> entity != null && entity.isShiftKeyDown() && GunItem.isLoaded(stack) ? 1.0f : 0.0f);
        ItemProperties.register(gun, new ResourceLocation("pixel_guns", "sprinting"), (stack, world, entity, seed) -> entity != null && entity.getItemInHand(InteractionHand.MAIN_HAND) == stack && entity.isSprinting() ? 1.0f : 0.0f);
    }
}

