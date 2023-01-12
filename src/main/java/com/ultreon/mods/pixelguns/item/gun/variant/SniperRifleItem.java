package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.registry.SoundRegistry;
import net.minecraft.sound.SoundEvent;

public class SniperRifleItem extends GunItem {
    public SniperRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                22.0f,
                500,
                20,
                5,
                ItemRegistry.HEAVY_RIFLE_BULLET,
                26,
                0.01f,
                8.25f,
                1,
                LoadingType.INDIVIDUAL,
                new SoundEvent[] {SoundRegistry.RELOAD_GENERIC_SNIPER_P1, SoundRegistry.RELOAD_CLASSIC_SNIPER_P2, SoundRegistry.RELOAD_GENERIC_SNIPER_P3},
                SoundRegistry.SNIPER_CLASSIC,
                5,
                true,
                new int[] {1, 8, 17}
        );
    }
}
