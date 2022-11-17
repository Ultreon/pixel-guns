package com.ultreon.mods.pixelguns.sound;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static SoundEvent RELOAD_GENERIC_PISTOL_P1 = ModSounds.registerSoundEvent("generic_pistol_p1");
    public static SoundEvent RELOAD_GENERIC_PISTOL_P2 = ModSounds.registerSoundEvent("generic_pistol_p2");
    public static SoundEvent RELOAD_GENERIC_PISTOL_P3 = ModSounds.registerSoundEvent("generic_pistol_p3");
    public static SoundEvent PISTOL_LIGHT = ModSounds.registerSoundEvent("pistol_light");
    public static SoundEvent PISTOL_HEAVY = ModSounds.registerSoundEvent("pistol_heavy");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P1 = ModSounds.registerSoundEvent("generic_revolver_p1");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P2 = ModSounds.registerSoundEvent("generic_revolver_p2");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P3 = ModSounds.registerSoundEvent("generic_revolver_p3");
    public static SoundEvent REVOLVER_MAGNUM = ModSounds.registerSoundEvent("revolver_magnum");
    public static SoundEvent RELOAD_GENERIC_SMG_P1 = ModSounds.registerSoundEvent("generic_smg_p1");
    public static SoundEvent RELOAD_GENERIC_SMG_P2 = ModSounds.registerSoundEvent("generic_smg_p2");
    public static SoundEvent RELOAD_GENERIC_SMG_P3 = ModSounds.registerSoundEvent("generic_smg_p3");
    public static SoundEvent SMG_MACHINEPISTOL = ModSounds.registerSoundEvent("smg_machinepistol");
    public static SoundEvent RELOAD_GENERIC_AR_P1 = ModSounds.registerSoundEvent("generic_ar_p1");
    public static SoundEvent RELOAD_GENERIC_AR_P2 = ModSounds.registerSoundEvent("generic_ar_p2");
    public static SoundEvent RELOAD_GENERIC_AR_P3 = ModSounds.registerSoundEvent("generic_ar_p3");
    public static SoundEvent ASSAULTRIFLE_LIGHT = ModSounds.registerSoundEvent("assaultrifle_light");
    public static SoundEvent RELOAD_HEAVY_AR_P1 = ModSounds.registerSoundEvent("heavy_ar_p1");
    public static SoundEvent RELOAD_HEAVY_AR_P2 = ModSounds.registerSoundEvent("heavy_ar_p2");
    public static SoundEvent RELOAD_HEAVY_AR_P3 = ModSounds.registerSoundEvent("heavy_ar_p3");
    public static SoundEvent ASSAULTRIFLE_HEAVY = ModSounds.registerSoundEvent("assaultrifle_heavy");
    public static SoundEvent ASSAULTRIFLE_HEAVY_EG = ModSounds.registerSoundEvent("assaultrifle_heavy_eg");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P1 = ModSounds.registerSoundEvent("combat_shotgun_p1");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P2 = ModSounds.registerSoundEvent("combat_shotgun_p2");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P3 = ModSounds.registerSoundEvent("combat_shotgun_p3");
    public static SoundEvent SHOTGUN_COMBAT = ModSounds.registerSoundEvent("shotgun_combat");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P1 = ModSounds.registerSoundEvent("generic_sniper_p1");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P2 = ModSounds.registerSoundEvent("generic_sniper_p2");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P3 = ModSounds.registerSoundEvent("generic_sniper_p3");
    public static SoundEvent RELOAD_CLASSIC_SNIPER_P2 = ModSounds.registerSoundEvent("classic_sniper_p2");
    public static SoundEvent SNIPER_CLASSIC = ModSounds.registerSoundEvent("sniper_classic");

    public static SoundEvent KATANA_SWING = ModSounds.registerSoundEvent("katana_swoop");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation("pixel_guns", name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        PixelGuns.LOGGER.info("Registering ModSounds for pixel_guns");
    }
}

