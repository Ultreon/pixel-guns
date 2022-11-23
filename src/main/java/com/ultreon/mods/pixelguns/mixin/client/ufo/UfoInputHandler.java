package com.ultreon.mods.pixelguns.mixin.client.ufo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.authlib.GameProfile;
import com.ultreon.mods.pixelguns.entity.ufo.UfoEntity;
import com.ultreon.mods.pixelguns.entity.ufo.UfoInput;

import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.encryption.PlayerPublicKey;

@Mixin(ClientPlayerEntity.class)
public abstract class UfoInputHandler extends AbstractClientPlayerEntity {

    @Shadow private boolean riding;
    @Shadow public Input input;

    public UfoInputHandler(ClientWorld world, GameProfile profile, PlayerPublicKey publicKey) {
        super(world, profile, publicKey);
    }

    @Inject(method = "tickRiding", at = @At("TAIL"))
    public void tickRiding(CallbackInfo info) {
        if (getVehicle() instanceof UfoEntity) {
            riding = UfoInput.receivingInput();
        }
    }
}