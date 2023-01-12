package com.ultreon.mods.pixelguns.registry;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeybindRegistry {
    public static KeyBinding fire = new KeyBinding("key.pixel_guns.fire", InputUtil.Type.KEYSYM, 82, "category.pixel_guns.binds");
    public static KeyBinding aim = new KeyBinding("key.pixel_guns.aim", InputUtil.Type.KEYSYM, 82, "category.pixel_guns.binds");
    public static KeyBinding reload = new KeyBinding("key.pixel_guns.reload", InputUtil.Type.KEYSYM, 82, "category.pixel_guns.binds");

    public static void registerKeybinds() {
        KeyBindingHelper.registerKeyBinding(fire);
        KeyBindingHelper.registerKeyBinding(aim);
        KeyBindingHelper.registerKeyBinding(reload);
    }
}
