package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.GunItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(value = EnvType.CLIENT)
@Mixin(value = Minecraft.class)
public abstract class MinecraftClientMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;
    @Shadow
    private int rightClickDelay;

    @Inject(method = {"startUseItem"}, at = {@At(value = "RETURN")})
    public void doItemUse(CallbackInfo ci) {
        if (this.player == null) {
            return;
        }
        ItemStack itemStack = this.player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof GunItem) {
            this.rightClickDelay = 0;
        }
    }

    @Shadow @Final public Options options;
    @Shadow public Screen screen;
    @Shadow @Final public MouseHandler mouseHandler;
    @Shadow public abstract void startUseItem();
    @Shadow public abstract void continueAttack(boolean bl);

    @Inject(method = "handleKeybinds", at = @At(value = "INVOKE", target = "continueAttack", shift = At.Shift.BEFORE))
    public void handleGunKeybind(CallbackInfo info) {
        if (this.options.keyAttack.isDown() && this.rightClickDelay == 0 && !this.player.isUsingItem() && this.player.getMainHandItem().getItem() instanceof GunItem) {
            this.startUseItem();
        }
    }

    // Prevent attack animation when firing gun
    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void cancelGunAttack(CallbackInfoReturnable<Boolean> info) {
        if (player.getMainHandItem().getItem() instanceof GunItem) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "continueAttack", at = @At("HEAD"), cancellable = true)
    private void continueAttack(boolean b, CallbackInfo info) {
        if (player.getMainHandItem().getItem() instanceof GunItem) {
            info.cancel();
        }
    }
}