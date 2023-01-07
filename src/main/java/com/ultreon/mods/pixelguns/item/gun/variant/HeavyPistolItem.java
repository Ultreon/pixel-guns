package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class HeavyPistolItem extends GunItem {
    public HeavyPistolItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                11.0f,
                250,
                5,
                7,
                ModItems.HEAVY_HANDGUN_BULLET,
                26,
                0.25f,
                7.5f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {ModSounds.RELOAD_GENERIC_PISTOL_P1, ModSounds.RELOAD_GENERIC_PISTOL_P2, ModSounds.RELOAD_GENERIC_PISTOL_P3},
                ModSounds.PISTOL_HEAVY,
                1,
                false,
                new int[] {6, 16, 20}
        );
    }
}
