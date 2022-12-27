package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class SniperRifleItem extends GunItem {
    public SniperRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                22.0f,
                500,
                20,
                5,
                ModItems.HEAVY_RIFLE_BULLET,
                26,
                0.01f,
                8.25f,
                1,
                LoadingType.INDIVIDUAL,
                ModSounds.RELOAD_GENERIC_SNIPER_P1,
                ModSounds.RELOAD_CLASSIC_SNIPER_P2,
                ModSounds.RELOAD_GENERIC_SNIPER_P3,
                ModSounds.SNIPER_CLASSIC,
                5,
                true,
                1,
                8,
                17
        );
    }
}
