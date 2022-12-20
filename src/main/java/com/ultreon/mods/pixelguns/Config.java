package com.ultreon.mods.pixelguns;

import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import static com.ultreon.mods.pixelguns.Config.GunAmmoDisplay.BLUE_BAR;

public class Config {
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER;
    public static final ForgeConfigSpec.BooleanValue DO_RECOIL;
    public static final ForgeConfigSpec.EnumValue<GunAmmoDisplay> GUN_AMMO_DISPLAY;

    public static final ForgeConfigSpec.BooleanValue USE_CUSTOM_CONFIG_GUI;
    private static ModConfig config;

    static {
        CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        DO_RECOIL = CLIENT_BUILDER.comment("Do recoil when shooting.").define("do_recoil", true);
        GUN_AMMO_DISPLAY = CLIENT_BUILDER.comment("How to display gun ammo").defineEnum("gun_ammo_display", BLUE_BAR);
        USE_CUSTOM_CONFIG_GUI = CLIENT_BUILDER.comment("Use Ultreon's Standard Config GUI").define("use_custom_config_gui", true);
    }

    public static void init() {
        config = ModLoadingContext.registerConfig(PixelGuns.MOD_ID, ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }

    public static void save() {
        config.save();
    }

    public static enum GunAmmoDisplay {
        BLUE_BAR,
        AMMO_COUNT,
        NONE
    }
}
