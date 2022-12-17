package com.ultreon.mods.pixelguns.api;

/**
 * Run with {@code FabricLoader.getInstance().getEntrypoints("pixelguns:configScreenPreferencesHook", ConfigScreenPreferencesHook.class).forEach(ConfigScreenPreferencesHook::onConfigPreferencesInit);}
 */
@FunctionalInterface
public interface ConfigScreenPreferencesHook {
    public static boolean disabledCustomConfigScreen = false;
    public void onConfigPreferencesInit();

    public static boolean customConfigScreenDisabled() {
        return disabledCustomConfigScreen;
    }
}
