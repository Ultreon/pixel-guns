package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.ultreon.mods.pixelguns.item.gun.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ScopedCrosshairRemover {
    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    public void removeCrosshair(MatrixStack stack, CallbackInfo info) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player.getMainHandStack().getItem() instanceof GunItem gunItem) {
            if (gunItem.isScoped && GunItem.isLoaded(player.getMainHandStack()) && MinecraftClient.getInstance().options.useKey.isPressed()) {
                info.cancel();
            }
        }
    }
}
