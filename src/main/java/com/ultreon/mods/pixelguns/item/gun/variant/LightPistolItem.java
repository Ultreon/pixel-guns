package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class LightPistolItem extends GunItem {
    public LightPistolItem(Settings settings) {
        super(
                settings,
                AmmoLoadingType.SEMI_AUTOMATIC,
                5.5f,
                250,
                4,
                12,
                ItemRegistry.STANDARD_HANDGUN_BULLET,
                26,
                0.25f,
                2.5f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {ModSounds.RELOAD_GENERIC_PISTOL_P1, ModSounds.RELOAD_GENERIC_PISTOL_P2, ModSounds.RELOAD_GENERIC_PISTOL_P3},
                ModSounds.PISTOL_LIGHT,
                1,
                false,
                new int[] {6, 16, 20}
        );
    }
}
