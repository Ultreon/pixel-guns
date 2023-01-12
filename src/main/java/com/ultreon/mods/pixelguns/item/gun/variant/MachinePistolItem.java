package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.registry.ItemRegistry;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.registry.SoundRegistry;
import net.minecraft.sound.SoundEvent;

public class MachinePistolItem extends GunItem {
    public MachinePistolItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                5.0f,
                250,
                1,
                30,
                ItemRegistry.STANDARD_HANDGUN_BULLET,
                44,
                0.0f,
                2.0f,
                1,
                LoadingType.CLIP,
                new SoundEvent[] {SoundRegistry.RELOAD_GENERIC_SMG_P1, SoundRegistry.RELOAD_GENERIC_SMG_P2, SoundRegistry.RELOAD_GENERIC_SMG_P3},
                SoundRegistry.SMG_MACHINEPISTOL,
                1,
                false,
                new int[] {5, 17, 30}
        );
    }
}
