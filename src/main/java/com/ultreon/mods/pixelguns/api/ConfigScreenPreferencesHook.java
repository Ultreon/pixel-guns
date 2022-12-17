package com.ultreon.mods.pixelguns.api;

import static com.ultreon.mods.pixelguns.api.ConfigScreenPreferencesHook.Companion.disabledCustomConfigScreen;

/**
 * Run with {@code FabricLoader.getInstance().getEntrypoints("pixelguns:configScreenPreferencesHook", ConfigScreenPreferencesHook.class).forEach(ConfigScreenPreferencesHook::onConfigPreferencesInit);}
 */
@FunctionalInterface
public interface ConfigScreenPreferencesHook {
    static class Companion {
        static boolean disabledCustomConfigScreen = false;
    }
    public void onConfigPreferencesInit();

    public static boolean customConfigScreenDisabled() {
        return disabledCustomConfigScreen;
    }

    public static void disableCustomConfigScren() {
        disabledCustomConfigScreen = true;
    }
}
