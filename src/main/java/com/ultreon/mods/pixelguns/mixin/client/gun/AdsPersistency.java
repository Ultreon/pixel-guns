package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.ultreon.mods.pixelguns.client.option.AdsSensitivity;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class AdsPersistency {
    @Inject(method = "accept", at = @At("HEAD"))
    public void addAdsSensitivityValue(GameOptions.Visitor visitor, CallbackInfo ci) {
        visitor.accept("adsSensitivity", AdsSensitivity.getOption());
    }
}