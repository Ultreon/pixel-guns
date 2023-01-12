package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class CombatShotgunItem extends GunItem {
    public CombatShotgunItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                5.5f,
                250,
                14,
                6,
                ItemRegistry.SHOTGUN_SHELL,
                26,
                9.25f,
                8.25f,
                5,
                LoadingType.INDIVIDUAL,
                new SoundEvent[] {ModSounds.RELOAD_COMBAT_SHOTGUN_P1, ModSounds.RELOAD_COMBAT_SHOTGUN_P2, ModSounds.RELOAD_COMBAT_SHOTGUN_P3},
                ModSounds.SHOTGUN_COMBAT,
                6,
                false,
                new int[] {1, 4, 13}
        );
    }
}
