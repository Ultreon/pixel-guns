package com.ultreon.mods.pixelguns.util;

import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.registry.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

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
        ModelPredicateProviderRegistry.register(gun, new Identifier("pixel_guns", "load_tick"), (stack, world, entity, seed) -> entity != null && stack.getOrCreateNbt().getBoolean("isReloading") ? (float) stack.getOrCreateNbt().getInt("reloadTick") / 200.0f : 0.0f);
        ModelPredicateProviderRegistry.register(gun, new Identifier("pixel_guns", "loading"), (stack, world, entity, seed) -> entity != null && stack.getOrCreateNbt().getBoolean("isReloading") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(gun, new Identifier("pixel_guns", "aiming"), (stack, world, entity, seed) -> entity != null && MinecraftClient.getInstance().options.useKey.isPressed() && GunItem.isLoaded(stack) ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(gun, new Identifier("pixel_guns", "sprinting"), (stack, world, entity, seed) -> entity != null && entity.getStackInHand(Hand.MAIN_HAND) == stack && entity.isSprinting() ? 1.0f : 0.0f);
    }
}

