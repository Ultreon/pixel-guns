package com.ultreon.mods.pixelguns.api;

import static com.ultreon.mods.pixelguns.api.ConfigScreenPreferencesHook.Companion.cfgScreenDisabled;

/**
 * Run with {@code FabricLoader.getInstance().getEntrypoints("pixelguns:configScreenPreferencesHook", ConfigScreenPreferencesHook.class).forEach(ConfigScreenPreferencesHook::onConfigPreferencesInit);}
 */
@FunctionalInterface
public interface ConfigScreenPreferencesHook {
    class Companion {
        static boolean cfgScreenDisabled = true;
    }
    void onConfigPreferencesInit();

    static boolean customConfigScreenDisabled() {
        return cfgScreenDisabled;
    }

    static void disableCustomConfigScreen() {
        cfgScreenDisabled = true;
    }
}
