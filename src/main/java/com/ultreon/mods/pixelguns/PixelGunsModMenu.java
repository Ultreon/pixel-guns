package com.ultreon.mods.pixelguns;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.ultreon.mods.lib.client.gui.screen.GenericMenuScreen;
import com.ultreon.mods.pixelguns.api.ConfigScreenPreferencesHook;
import com.ultreon.mods.pixelguns.client.gui.ConfigScreen;

public class PixelGunsModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> {
            if (!ConfigScreenPreferencesHook.customConfigScreenDisabled()) {
                return null;
            }
            return new ConfigScreen(new GenericMenuScreen.Properties().back(screen));
        };
    }
}
