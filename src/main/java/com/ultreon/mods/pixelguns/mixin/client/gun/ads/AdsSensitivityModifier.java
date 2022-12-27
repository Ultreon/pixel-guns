package com.ultreon.mods.pixelguns.mixin.client.gun.ads;

import com.ultreon.mods.pixelguns.client.option.AdsSensitivity;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Mouse.class)
public class AdsSensitivityModifier {

    @ModifyArgs(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
    private void injected(Args args) {
        double deltaX = args.get(0);
        double deltaY = args.get(1);

        MinecraftClient client = MinecraftClient.getInstance();
        ItemStack gun = client.player.getStackInHand(Hand.MAIN_HAND);
        if (gun.getItem() instanceof GunItem && client.mouse.wasRightButtonClicked() && GunItem.isLoaded(gun)) {
            args.set(0, deltaX * AdsSensitivity.getValue());
            args.set(1, deltaY * AdsSensitivity.getValue());
        }
    }
}