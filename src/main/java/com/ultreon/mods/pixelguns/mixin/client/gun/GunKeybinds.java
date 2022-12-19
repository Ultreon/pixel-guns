package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.ultreon.mods.pixelguns.item.gun.AbstractGunItem;

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

@Mixin(MinecraftClient.class)
public abstract class GunKeybinds {
    
    @Shadow @Nullable public ClientPlayerEntity player;
    @Shadow private int itemUseCooldown;
    @Shadow @Final public GameOptions options;
    @Shadow public abstract void doItemUse();

    // Allows the GunItem.use() to be called when holding a GunItem and using the attack keybind instead of the use keybind

    @Inject(method = "handleInputEvents", at = @At("TAIL"))
    public void handleGunKeybind(CallbackInfo info) {
        if (this.player.getMainHandStack().getItem() instanceof AbstractGunItem && this.options.attackKey.isPressed()) {
            this.doItemUse();
        }
    }

    // Prevents default item use cooldown when firing gun

    @Inject(method = "doItemUse", at = @At("RETURN"))
    public void preventGunUseCooldown(CallbackInfo ci) {
        if (this.player == null) {
            return;
        }
        ItemStack itemStack = this.player.getStackInHand(Hand.MAIN_HAND);
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof AbstractGunItem) {
            this.itemUseCooldown = 0;
        }
    }
}