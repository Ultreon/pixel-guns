package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class CombatShotgunItem extends GunItem {
    public CombatShotgunItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                5.5f,
                14,
                6,
                ModItems.SHOTGUN_SHELL,
                26,
                9.25f,
                8.25f,
                5,
                2,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P1,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P2,
                ModSounds.RELOAD_COMBAT_SHOTGUN_P3,
                ModSounds.SHOTGUN_COMBAT,
                6,
                false,
                1,
                4,
                13
        );
    }
}
