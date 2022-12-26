package com.ultreon.mods.pixelguns.registry;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.screen.WorkshopScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlerType {
    public static final ScreenHandlerType<WorkshopScreenHandler> WORKSHOP_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(PixelGuns.res("workshop"), WorkshopScreenHandler::new);
}
