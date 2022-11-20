package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Mouse.class)
public class MouseHandlerMixin {

    @ModifyArgs(method = "updateMouse", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
    private void injected(Args args) {
        double a0 = args.get(0);
        double a1 = args.get(1);

        ItemStack gun = MinecraftClient.getInstance().player.getStackInHand(Hand.MAIN_HAND);
        if (gun.getItem() instanceof GunItem && MinecraftClient.getInstance().mouse.wasRightButtonClicked() && GunItem.isLoaded(gun)) {
            NbtCompound nbtCompound = gun.getOrCreateNbt();
            if (nbtCompound.getBoolean("isScoped")) {
                args.set(0, a0 * 0.2);
                args.set(1, a1 * 0.2);
            } else {
                args.set(0, a0 * 0.8);
                args.set(1, a1 * 0.8);
            }
        }
    }
}