package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class PumpShotgunItem extends GunItem {
    public PumpShotgunItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                18.0f,
                250,
                5,
                5,
                ItemRegistry.SHOTGUN_SHELL,
                20,
                0.25f,
                0.0f,
                5,
                LoadingType.INDIVIDUAL,
                new SoundEvent[] {ModSounds.RELOAD_COMBAT_SHOTGUN_P1, ModSounds.RELOAD_COMBAT_SHOTGUN_P2, ModSounds.RELOAD_COMBAT_SHOTGUN_P3},
                ModSounds.SHOTGUN_COMBAT,
                5,
                false,
                new int[] {1, 4, 13}
        );
    }
}
