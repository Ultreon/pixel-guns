package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.mojang.authlib.GameProfile;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AdsZoom extends PlayerEntity {
    public AdsZoom(World level, BlockPos blockPos, float f, GameProfile gameProfile, @Nullable PlayerPublicKey profilePublicKey) {
        super(level, blockPos, f, gameProfile, profilePublicKey);
    }

    // Apply camera zoom on ADS
    @Inject(method = "getFovMultiplier", at = @At("TAIL"), cancellable = true)
    public void zoomLevel(CallbackInfoReturnable<Float> ci) {
        ItemStack gun = this.getStackInHand(Hand.MAIN_HAND);
        if (gun.getItem() instanceof GunItem && MinecraftClient.getInstance().mouse.wasRightButtonClicked() && GunItem.isLoaded(gun)) {
            NbtCompound nbtCompound = gun.getOrCreateNbt();
            if (nbtCompound.getBoolean("isScoped")) {
                ci.setReturnValue(0.2f);
            } else {
                ci.setReturnValue(0.8f);
            }
        }
    }
}

