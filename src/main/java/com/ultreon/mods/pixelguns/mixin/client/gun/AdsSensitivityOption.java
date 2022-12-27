package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.ultreon.mods.pixelguns.client.option.AdsSensitivity;
import net.minecraft.client.gui.screen.option.MouseOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MouseOptionsScreen.class)
public class AdsSensitivityOption {
    /**
     * @author Skylandia
     * @reason add ADS sensitivity
     */
    @Overwrite
    private static SimpleOption<?>[] getOptions(GameOptions gameOptions) {
        return new SimpleOption[]{gameOptions.getMouseSensitivity(), AdsSensitivity.getOption(), gameOptions.getInvertYMouse(), gameOptions.getMouseWheelSensitivity(), gameOptions.getDiscreteMouseScroll(), gameOptions.getTouchscreen()};
    }
}
