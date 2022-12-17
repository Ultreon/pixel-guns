package com.ultreon.mods.pixelguns.api;

import static com.ultreon.mods.pixelguns.api.ConfigScreenPreferencesHook.Companion.customConfigScreen;

/**
 * Run with {@code FabricLoader.getInstance().getEntrypoints("pixelguns:configScreenPreferencesHook", ConfigScreenPreferencesHook.class).forEach(ConfigScreenPreferencesHook::onConfigPreferencesInit);}
 */
@FunctionalInterface
public interface ConfigScreenPreferencesHook {
    class Companion {
        static boolean customConfigScreen = true;
    }
    void onConfigPreferencesInit();

    static boolean customConfigScreenDisabled() {
        return customConfigScreen;
    }

    static void setCustomConfigScreen(boolean use) {
        customConfigScreen = !use;
    }
}
