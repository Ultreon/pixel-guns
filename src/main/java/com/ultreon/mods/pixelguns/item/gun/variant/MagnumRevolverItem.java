package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.sound.SoundEvent;

public class MagnumRevolverItem extends GunItem {
    public MagnumRevolverItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                11.0f,
                250,
                10,
                6,
                ModItems.HEAVY_HANDGUN_BULLET,
                40,
                0.125f,
                6.5f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {ModSounds.RELOAD_GENERIC_REVOLVER_P1, ModSounds.RELOAD_GENERIC_REVOLVER_P2, ModSounds.RELOAD_GENERIC_REVOLVER_P3},
                ModSounds.REVOLVER_MAGNUM,
                1,
                false,
                new int[] {1, 26, 34}
        );
    }
}
