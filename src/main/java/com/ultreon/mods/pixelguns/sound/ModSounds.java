package com.ultreon.mods.pixelguns.sound;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static SoundEvent RELOAD_GENERIC_PISTOL_P1 = ModSounds.register("generic_pistol_p1");
    public static SoundEvent RELOAD_GENERIC_PISTOL_P2 = ModSounds.register("generic_pistol_p2");
    public static SoundEvent RELOAD_GENERIC_PISTOL_P3 = ModSounds.register("generic_pistol_p3");
    public static SoundEvent PISTOL_LIGHT = ModSounds.register("pistol_light");
    public static SoundEvent PISTOL_HEAVY = ModSounds.register("pistol_heavy");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P1 = ModSounds.register("generic_revolver_p1");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P2 = ModSounds.register("generic_revolver_p2");
    public static SoundEvent RELOAD_GENERIC_REVOLVER_P3 = ModSounds.register("generic_revolver_p3");
    public static SoundEvent REVOLVER_MAGNUM = ModSounds.register("revolver_magnum");
    public static SoundEvent RELOAD_GENERIC_SMG_P1 = ModSounds.register("generic_smg_p1");
    public static SoundEvent RELOAD_GENERIC_SMG_P2 = ModSounds.register("generic_smg_p2");
    public static SoundEvent RELOAD_GENERIC_SMG_P3 = ModSounds.register("generic_smg_p3");
    public static SoundEvent SMG_MACHINEPISTOL = ModSounds.register("smg_machinepistol");
    public static SoundEvent RELOAD_GENERIC_AR_P1 = ModSounds.register("generic_ar_p1");
    public static SoundEvent RELOAD_GENERIC_AR_P2 = ModSounds.register("generic_ar_p2");
    public static SoundEvent RELOAD_GENERIC_AR_P3 = ModSounds.register("generic_ar_p3");
    public static SoundEvent ASSAULTRIFLE_LIGHT = ModSounds.register("assaultrifle_light");
    public static SoundEvent RELOAD_HEAVY_AR_P1 = ModSounds.register("heavy_ar_p1");
    public static SoundEvent RELOAD_HEAVY_AR_P2 = ModSounds.register("heavy_ar_p2");
    public static SoundEvent RELOAD_HEAVY_AR_P3 = ModSounds.register("heavy_ar_p3");
    public static SoundEvent ASSAULTRIFLE_HEAVY = ModSounds.register("assaultrifle_heavy");
    public static SoundEvent ASSAULTRIFLE_HEAVY_EG = ModSounds.register("assaultrifle_heavy_eg");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P1 = ModSounds.register("combat_shotgun_p1");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P2 = ModSounds.register("combat_shotgun_p2");
    public static SoundEvent RELOAD_COMBAT_SHOTGUN_P3 = ModSounds.register("combat_shotgun_p3");
    public static SoundEvent SHOTGUN_COMBAT = ModSounds.register("shotgun_combat");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P1 = ModSounds.register("generic_sniper_p1");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P2 = ModSounds.register("generic_sniper_p2");
    public static SoundEvent RELOAD_GENERIC_SNIPER_P3 = ModSounds.register("generic_sniper_p3");
    public static SoundEvent RELOAD_CLASSIC_SNIPER_P2 = ModSounds.register("classic_sniper_p2");
    public static SoundEvent SNIPER_CLASSIC = ModSounds.register("sniper_classic");

    public static SoundEvent KATANA_SWING = ModSounds.register("katana_swoop");
    public static SoundEvent KATANA_HIT = ModSounds.register("katana_hit");
    public static SoundEvent GRENADE_EXPLODE = ModSounds.register("grenade_explode");
    public static SoundEvent CROWBAR_HIT = ModSounds.register("crowbar_hit");

    private static SoundEvent register(String name) {
        Identifier id = new Identifier("pixel_guns", name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}

