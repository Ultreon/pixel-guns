package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.item.GunItem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value = EnvType.CLIENT)
@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {
    
    @Shadow @Nullable public ClientPlayerEntity player;
    @Shadow private int itemUseCooldown;

    @Inject(method = {"doItemUse"}, at = {@At(value = "RETURN")})
    public void doGunUse(CallbackInfo ci) {
        if (this.player == null) {
            return;
        }
        ItemStack itemStack = this.player.getStackInHand(Hand.MAIN_HAND);
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof GunItem) {
            this.itemUseCooldown = 0;
        }
    }

    @Shadow @Final public GameOptions options;
    @Shadow public abstract void doItemUse();
    @Shadow public abstract void handleBlockBreaking(boolean bl);

    @Inject(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleBlockBreaking(Z)V", shift = At.Shift.BEFORE))
    public void handleGunKeybind(CallbackInfo info) {
        if (this.options.attackKey.isPressed() && this.itemUseCooldown == 0 && !this.player.isUsingItem() && this.player.getMainHandStack().getItem() instanceof GunItem) {
            this.doItemUse();
        }
    }

    // Prevent attack animation when firing gun
    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    private void cancelGunAttack(CallbackInfoReturnable<Boolean> info) {
        if (player.getMainHandStack().getItem() instanceof GunItem) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At("HEAD"), cancellable = true)
    private void handleBlockBreaking(boolean b, CallbackInfo info) {
        if (player.getMainHandStack().getItem() instanceof GunItem) {
            info.cancel();
        }
    }
}