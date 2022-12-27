package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class PumpShotgunItem extends GunItem {
    public PumpShotgunItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                18.0f,
                250,
                14,
                5,
                ModItems.SHOTGUN_SHELL,
                20,
                9.25f,
                8.25f,
                5,
                LoadingType.INDIVIDUAL,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P1,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P2,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P3,
                ModSounds.SHOTGUN_COMBAT,
                5,
                false,
                1,
                4,
                13
        );
    }
}
