package com.ultreon.mods.pixelguns.registry;

import com.ultreon.mods.pixelguns.screen.WorkshopScreenHandler;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class ScreenHandlerRegistry {
    public static final ScreenHandlerType<WorkshopScreenHandler> WORKSHOP_SCREEN_HANDLER = ScreenHandlerRegistry.register("workshop", WorkshopScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registry.SCREEN_HANDLER, ResourcePath.get(name), new ScreenHandlerType<>(factory));
    }
}
