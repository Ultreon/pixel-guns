package com.ultreon.mods.pixelguns.client.gui;

import com.ultreon.mods.lib.client.gui.screen.GenericMenuScreen;
import com.ultreon.mods.pixelguns.Config;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import java.util.Locale;

import static com.ultreon.mods.pixelguns.Config.GunAmmoDisplay.*;

public class ConfigScreen extends GenericMenuScreen {
    private final ButtonWidget doRecoil;
    private final ButtonWidget gunAmmoDisplay;
    private final ButtonWidget useCustomConfigGui;

    public ConfigScreen(Properties properties) {
        super(properties);

        doRecoil = addButtonRow(Text.translatable("config.pixel_guns.do_recoil"), () -> {
            Config.DO_RECOIL.set(!Config.DO_RECOIL.get());
            Config.save();
            reloadNames();
        });
        gunAmmoDisplay = addButtonRow(Text.translatable("config.pixel_guns.gun_ammo_display." + Config.GUN_AMMO_DISPLAY.get().toString().toLowerCase(Locale.ROOT)), btn -> {
            Config.GUN_AMMO_DISPLAY.set(switch (Config.GUN_AMMO_DISPLAY.get()) {
                case NONE -> BLUE_BAR;
                case BLUE_BAR -> AMMO_COUNT;
                case AMMO_COUNT -> NONE;
            });
            btn.setMessage(Text.translatable("config.pixel_guns.gun_ammo_display." + Config.GUN_AMMO_DISPLAY.get().toString().toLowerCase(Locale.ROOT)));
            Config.save();
            reloadNames();
        });
        useCustomConfigGui = addButtonRow(Text.translatable("config.pixel_guns.use_custom_config_gui"), () -> {
            Config.USE_CUSTOM_CONFIG_GUI.set(!Config.USE_CUSTOM_CONFIG_GUI.get());
            Config.save();
            reloadNames();
        });
        reloadNames();
    }

    @Override
    protected void init() {
        super.init();
    }

    private void reloadNames() {
        doRecoil.setMessage(Text.translatable("config.pixel_guns.do_recoil", Config.DO_RECOIL.get() ? ScreenTexts.ON : ScreenTexts.OFF));
        useCustomConfigGui.setMessage(Text.translatable("config.pixel_guns.use_custom_config_gui", Config.USE_CUSTOM_CONFIG_GUI.get() ? ScreenTexts.ON : ScreenTexts.OFF));
    }
}
