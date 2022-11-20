package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.item.GunItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @ModifyArgs(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private void injected(Args args) {
        double a0 = args.get(0);
        double a1 = args.get(1);

        ItemStack gun = Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND);
        if (gun.getItem() instanceof GunItem && Minecraft.getInstance().mouseHandler.isRightPressed() && GunItem.isLoaded(gun)) {
            CompoundTag nbtCompound = gun.getOrCreateTag();
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
