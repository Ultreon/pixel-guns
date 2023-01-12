package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class HeavyAssaultRifleItem extends GunItem {

    public HeavyAssaultRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                8.0f,
                250,
                3,
                50,
                ItemRegistry.STANDARD_RIFLE_BULLET,
                48,
                0.125f,
                2.5f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {ModSounds.RELOAD_HEAVY_AR_P1, ModSounds.RELOAD_HEAVY_AR_P2, ModSounds.RELOAD_HEAVY_AR_P3},
                ModSounds.ASSAULTRIFLE_HEAVY,
                1,
                false,
                new int[] {6, 22, 40}
        );
    }
}
