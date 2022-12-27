package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class HeavyAssaultRifleItem extends GunItem {

    public HeavyAssaultRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                8.0f,
                3,
                50,
                ModItems.STANDARD_RIFLE_BULLET,
                48,
                0.125f,
                2.5f,
                1,
                LoadingType.CLIP,
                ModSounds.RELOAD_HEAVY_AR_P1,
                ModSounds.RELOAD_HEAVY_AR_P2,
                ModSounds.RELOAD_HEAVY_AR_P3,
                ModSounds.ASSAULTRIFLE_HEAVY,
                1,
                false,
                6,
                22,
                40
        );
    }
}
