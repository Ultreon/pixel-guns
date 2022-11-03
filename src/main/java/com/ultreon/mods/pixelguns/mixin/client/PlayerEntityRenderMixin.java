package com.ultreon.mods.pixelguns.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.ultreon.mods.pixelguns.NbtNames;
import com.ultreon.mods.pixelguns.item.GunItem;
import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

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
    @Inject(method="render(Lnet/minecraft/world/entity/Entity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at=@At(value="TAIL"))
    private void render(Entity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        Player player = (Player) entity;
        ItemStack itemInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (itemInHand.getItem() instanceof InfinityGunItem) {
            boolean isShooting = itemInHand.getOrCreateTagElement(NbtNames.INFINITY_GUN).getBoolean(NbtNames.IS_SHOOTING);
            if (isShooting) {
                itemInHand.getOrCreateTagElement(NbtNames.INFINITY_GUN).getInt(NbtNames.SHOOT_TICKS);
                int tickCount = player.tickCount;
                List<BeaconBlockEntity.BeaconBeamSection> list = new ArrayList<>();
                list.add(new BeaconBlockEntity.BeaconBeamSection(new float[]{1, 1, 1}));
                int distance = 0;
                poseStack.pushPose();
                poseStack.translate(player.getX(), player.getY() + 1.0, player.getZ());
                for (int m = 0; m < list.size(); ++m) {
                    BeaconBlockEntity.BeaconBeamSection beaconBeamSection = list.get(m);
                    BeaconRenderer.renderBeaconBeam(poseStack, multiBufferSource, f, tickCount, distance, m == list.size() - 1 ? 1024 : beaconBeamSection.getHeight(), beaconBeamSection.getColor());
                    distance += beaconBeamSection.getHeight();
                }
                poseStack.mulPose(Quaternion.fromXYZ(player.getXRot(), player.getYHeadRot(), 0));
                poseStack.popPose();
            }
        }
    }
}

