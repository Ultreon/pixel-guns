package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.item.GunItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value=PlayerRenderer.class)
public class PlayerEntityRenderMixin {
    @Inject(method="getArmPose", at=@At(value="TAIL"), cancellable = true)
    private static void gunPose(AbstractClientPlayer player, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> ci) {
        if (player.getItemInHand(hand).getItem() instanceof GunItem && GunItem.isLoaded(player.getItemInHand(hand)) && player.getItemInHand(hand).getOrCreateTag().getInt("reloadTick") <= 0) {
            ci.setReturnValue(HumanoidModel.ArmPose.BOW_AND_ARROW);
            return;
        }
        if (player.getItemInHand(hand).getItem() instanceof GunItem && player.getItemInHand(hand).getOrCreateTag().getInt("reloadTick") > 0) {
            ci.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
            return;
        }
        ci.setReturnValue(HumanoidModel.ArmPose.ITEM);
    }
}

