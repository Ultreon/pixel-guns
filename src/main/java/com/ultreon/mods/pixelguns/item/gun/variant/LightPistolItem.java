package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class LightPistolItem extends GunItem {
    public LightPistolItem(Settings settings) {
        super(
                settings,
                AmmoLoadingType.SEMI_AUTOMATIC,
                5.5f,
                4,
                17,
                ModItems.STANDARD_HANDGUN_BULLET,
                26,
                0.25f,
                2.5f,
                1,
                1,
                ModSounds.RELOAD_GENERIC_PISTOL_P1,
                ModSounds.RELOAD_GENERIC_PISTOL_P2,
                ModSounds.RELOAD_GENERIC_PISTOL_P3,
                ModSounds.PISTOL_LIGHT,
                1,
                false,
                6,
                16,
                20
        );
    }
}
