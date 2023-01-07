package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class AssaultRifleItem extends GunItem {
    public AssaultRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                5.5f,
                250,
                2,
                30,
                ModItems.STANDARD_RIFLE_BULLET,
                44,
                0.15f,
                1.625f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {ModSounds.RELOAD_GENERIC_AR_P1, ModSounds.RELOAD_GENERIC_AR_P2, ModSounds.RELOAD_GENERIC_AR_P3},
                ModSounds.ASSAULTRIFLE_LIGHT,
                1,
                false,
                new int[] {6, 18, 37}
        );
    }
}