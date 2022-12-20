package com.ultreon.mods.pixelguns.client.gui;

import com.ultreon.mods.lib.client.gui.screen.GenericMenuScreen;
import com.ultreon.mods.pixelguns.Config;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ConfigScreen extends GenericMenuScreen {
    private final ButtonWidget doRecoil;
    private final ButtonWidget useCustomConfigGui;

    public ConfigScreen(Properties properties) {
        super(properties);

        doRecoil = addButtonRow(Text.translatable("config.pixel_guns.do_recoil." + Config.DO_RECOIL.get()), b -> {
            Config.DO_RECOIL.set(!Config.DO_RECOIL.get());
            b.setMessage(Text.translatable("config.pixel_guns.do_recoil." + Config.DO_RECOIL.get()));
            Config.save();
            reloadNames();
        });
        useCustomConfigGui = addButtonRow(Text.translatable("config.pixel_guns.use_custom_config_gui" + Config.USE_CUSTOM_CONFIG_GUI.get()), b -> {
            Config.USE_CUSTOM_CONFIG_GUI.set(!Config.USE_CUSTOM_CONFIG_GUI.get());
            b.setMessage(Text.translatable("config.pixel_guns.use_custom_config_gui" + Config.USE_CUSTOM_CONFIG_GUI.get()));
            Config.save();
            reloadNames();
        });
    }

    @Override
    protected void init() {
        super.init();
        reloadNames();
    }

    private void reloadNames() {
        doRecoil.setMessage(Text.translatable("config.pixel_guns.do_recoil", Config.DO_RECOIL.get() ? ScreenTexts.ON : ScreenTexts.OFF));
        useCustomConfigGui.setMessage(Text.translatable("config.pixel_guns.use_custom_config_gui", Config.USE_CUSTOM_CONFIG_GUI.get() ? ScreenTexts.ON : ScreenTexts.OFF));
    }
}
