package com.ultreon.mods.pixelguns;

import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER;
    public static final ForgeConfigSpec.BooleanValue DO_RECOIL;

    static {
        CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        DO_RECOIL = CLIENT_BUILDER.define("do_recoil", true);
    }

    public static void init() {
        ModLoadingContext.registerConfig(PixelGuns.MOD_ID, ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
    }
}
