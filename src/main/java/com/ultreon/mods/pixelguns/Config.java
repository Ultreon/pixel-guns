package com.ultreon.mods.pixelguns;

import com.ultreon.mods.pixelguns.api.ConfigScreenPreferencesHook;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.api.fml.event.config.ModConfigEvents;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER;
    public static final ForgeConfigSpec.BooleanValue DO_RECOIL;

    public static final ForgeConfigSpec.BooleanValue USE_CUSTOM_CONFIG_GUI;
    private static ModConfig config;

    static {
        CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        DO_RECOIL = CLIENT_BUILDER.comment("Do recoil when shooting.").define("do_recoil", true);
        USE_CUSTOM_CONFIG_GUI = CLIENT_BUILDER.comment("Use Ultreon's Standard Config GUI").define("use_custom_config_gui", true);
    }

    public static void init() {
        config = ModLoadingContext.registerConfig(PixelGuns.MOD_ID, ModConfig.Type.CLIENT, CLIENT_BUILDER.build());

        ModConfigEvents.loading(PixelGuns.MOD_ID).register(Config::reload);
        ModConfigEvents.reloading(PixelGuns.MOD_ID).register(Config::reload);
    }

    private static void reload(ModConfig modConfig) {
        boolean value = modConfig.getConfigData().get("use_custom_config_gui");
        ConfigScreenPreferencesHook.setCustomConfigScreen(value);
    }

    public static void save() {
        config.save();
    }
}
