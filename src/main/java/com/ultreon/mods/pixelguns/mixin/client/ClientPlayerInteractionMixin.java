package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionMixin {
    @Inject(method = "attackEntity", at = @At(value = "HEAD"))
    private void onAttackMiss(PlayerEntity player, Entity entity, CallbackInfo ci) {
        var world = MinecraftClient.getInstance().world;
        assert player != null;
        if (player.getMainHandStack().getItem() == ModItems.KATANA || player.getMainHandStack().getItem() == ModItems.CROWBAR) {
            assert world != null;
            world.playSound(player.getBlockPos(), ModSounds.KATANA_HIT, SoundCategory.PLAYERS, 1, 1, false);
        }
    }
}
