package com.ultreon.mods.pixelguns.mixin.client.gun;

import com.ultreon.mods.pixelguns.NbtNames;
import com.ultreon.mods.pixelguns.item.AbstractGunItem;
import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Quaternion;

@Mixin(PlayerEntityRenderer.class)
public class PlayerGunPose {
    @Inject(method = "getArmPose", at = @At("TAIL"), cancellable = true)
    private static void gunPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> ci) {
        if (player.getStackInHand(hand).getItem() instanceof AbstractGunItem && AbstractGunItem.isLoaded(player.getStackInHand(hand)) && player.getStackInHand(hand).getOrCreateNbt().getInt("reloadTick") <= 0) {
            ci.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
            return;
        }
        if (player.getStackInHand(hand).getItem() instanceof AbstractGunItem && player.getStackInHand(hand).getOrCreateNbt().getInt("reloadTick") > 0) {
            ci.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_CHARGE);
            return;
        }
        ci.setReturnValue(BipedEntityModel.ArmPose.ITEM);
    }
    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    private void render(AbstractClientPlayerEntity entity, float f, float g, MatrixStack poseStack, VertexConsumerProvider multiBufferSource, int i, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) entity;
        ItemStack itemInHand = player.getStackInHand(Hand.MAIN_HAND);
        if (itemInHand.getItem() instanceof InfinityGunItem) {
            boolean isShooting = itemInHand.getOrCreateSubNbt(NbtNames.INFINITY_GUN).getBoolean(NbtNames.IS_SHOOTING);
            if (isShooting) {
                itemInHand.getOrCreateSubNbt(NbtNames.INFINITY_GUN).getInt(NbtNames.SHOOT_TICKS);
                int tickCount = player.age;
                List<BeaconBlockEntity.BeamSegment> list = new ArrayList<>();
                list.add(new BeaconBlockEntity.BeamSegment(new float[]{1, 1, 1}));
                int distance = 0;
                poseStack.push();
                poseStack.translate(player.getX(), player.getY() + 1.0, player.getZ());
                for (int m = 0; m < list.size(); ++m) {
                    BeaconBlockEntity.BeamSegment beaconBeamSection = list.get(m);
                    BeaconBlockEntityRenderer.renderBeam(poseStack, multiBufferSource, f, tickCount, distance, m == list.size() - 1 ? 1024 : beaconBeamSection.getHeight(), beaconBeamSection.getColor());
                    distance += beaconBeamSection.getHeight();
                }
                poseStack.multiply(Quaternion.fromEulerXyz(player.getPitch(), player.getHeadYaw(), 0));
                poseStack.pop();
            }
        }
    }
}