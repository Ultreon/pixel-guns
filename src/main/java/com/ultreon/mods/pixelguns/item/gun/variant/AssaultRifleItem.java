package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class AssaultRifleItem extends GunItem {
    public AssaultRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                5.5f,
                2,
                30,
                ModItems.STANDARD_RIFLE_BULLET,
                44,
                0.15f,
                1.625f,
                1,
                1,
                ModSounds.RELOAD_GENERIC_AR_P1,
                ModSounds.RELOAD_GENERIC_AR_P2,
                ModSounds.RELOAD_GENERIC_AR_P3,
                ModSounds.ASSAULTRIFLE_LIGHT,
                1,
                false,
                6,
                18,
                37
        );
    }
}