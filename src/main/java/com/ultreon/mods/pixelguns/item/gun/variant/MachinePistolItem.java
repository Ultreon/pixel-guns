package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;

public class MachinePistolItem extends GunItem {
    public MachinePistolItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                5.0f,
                250,
                1,
                30,
                ModItems.STANDARD_HANDGUN_BULLET,
                44,
                0.0f,
                2.0f,
                1,
                LoadingType.CLIP,
                ModSounds.RELOAD_GENERIC_SMG_P1,
                ModSounds.RELOAD_GENERIC_SMG_P2,
                ModSounds.RELOAD_GENERIC_SMG_P3,
                ModSounds.SMG_MACHINEPISTOL,
                1,
                false,
                5,
                17,
                30
        );
    }
}
