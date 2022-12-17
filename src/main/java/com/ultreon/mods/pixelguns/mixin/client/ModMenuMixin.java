package com.ultreon.mods.pixelguns.mixin.client;

import com.terraformersmc.modmenu.ModMenu;
import com.ultreon.mods.lib.client.gui.screen.GenericMenuScreen;
import com.ultreon.mods.pixelguns.Config;
import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.client.gui.ConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ModMenu.class)
public class ModMenuMixin {
    @Inject(at = @At("HEAD"), method = "getConfigScreen", cancellable = true)
    private static void pixelGuns$getConfigScreen(String modid, Screen menuScreen, CallbackInfoReturnable<Screen> cir) {
        if (Objects.equals(modid, PixelGuns.MOD_ID) && Config.USE_CUSTOM_CONFIG_GUI.get()) {
            cir.setReturnValue(new ConfigScreen(new GenericMenuScreen.Properties().titleLang("config.pixel_guns").back(menuScreen)));
        }
    }
}
