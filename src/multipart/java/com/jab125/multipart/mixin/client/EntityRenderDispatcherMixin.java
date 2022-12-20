package com.jab125.multipart.mixin.client;

import com.jab125.multipart.client.entity.MultiPartEntity;
import com.jab125.multipart.client.entity.MultiPartHolder;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Inject(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;drawBox(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/util/math/Box;FFFF)V", shift = At.Shift.AFTER))
    private static <T extends Entity & MultiPartHolder<T, R>, R extends MultiPartEntity<T, R>> void multipart$renderHitbox(MatrixStack matrixStack, VertexConsumer vertexConsumer, Entity entity, float f, CallbackInfo ci) {
        if (entity instanceof MultiPartHolder && entity instanceof Entity) {
            T multipartEntity = (T) entity;
            double d = -MathHelper.lerp((double)f, entity.lastRenderX, entity.getX());
            double e = -MathHelper.lerp((double)f, entity.lastRenderY, entity.getY());
            double g = -MathHelper.lerp((double)f, entity.lastRenderZ, entity.getZ());
            for (MultiPartEntity<T, R> entityPart : multipartEntity.getBodyParts()) {
                matrixStack.push();
                double h = d + MathHelper.lerp((double)f, entityPart.lastRenderX, entityPart.getX());
                double i = e + MathHelper.lerp((double)f, entityPart.lastRenderY, entityPart.getY());
                double j = g + MathHelper.lerp((double)f, entityPart.lastRenderZ, entityPart.getZ());
                matrixStack.translate(h, i, j);
                WorldRenderer.drawBox(matrixStack, vertexConsumer, entityPart.getBoundingBox().offset(-entityPart.getX(), -entityPart.getY(), -entityPart.getZ()), 0.25f, 1.0f, 0.0f, 1.0f);
                matrixStack.pop();
            }
        }
    }
}
