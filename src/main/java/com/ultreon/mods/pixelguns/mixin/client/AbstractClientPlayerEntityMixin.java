package com.ultreon.mods.pixelguns.mixin.client;

import com.mojang.authlib.GameProfile;
import com.ultreon.mods.pixelguns.item.GunItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.ProfilePublicKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin extends Player {
    public AbstractClientPlayerEntityMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile, @Nullable ProfilePublicKey profilePublicKey) {
        super(level, blockPos, f, gameProfile, profilePublicKey);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
    public void zoomLevel(CallbackInfoReturnable<Float> ci) {
        ItemStack gun = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (gun.getItem() instanceof GunItem && this.isShiftKeyDown() && GunItem.isLoaded(gun)) {
            CompoundTag nbtCompound = gun.getOrCreateTag();
            if (nbtCompound.getBoolean("isScoped")) {
                ci.setReturnValue(0.2f);
            } else {
                ci.setReturnValue(0.8f);
            }
        }
    }
}

