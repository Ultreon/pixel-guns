package com.ultreon.mods.pixelguns.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;

@Mixin(MinecraftClient.class)
public class CustomSwordSounds {
    
    @Shadow @Nullable public ClientPlayerEntity player;
    @Shadow @Nullable public ClientWorld world;

    @Inject(method = "doAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;hasLimitedAttackSpeed()Z", shift = Shift.BEFORE))
    private void onAttackMiss(CallbackInfoReturnable<Boolean> info) {
        assert player != null;
        if (player.getMainHandStack().getItem() == ModItems.KATANA || player.getMainHandStack().getItem() == ModItems.CROWBAR) {
            assert world != null;
            world.playSound(this.player.getBlockPos(), ModSounds.KATANA_SWING, SoundCategory.PLAYERS, 1, 1, false);
        }
    }
}