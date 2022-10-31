package com.ultreon.mods.pixelguns.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

@Environment(value = EnvType.CLIENT)
public class BulletEntityRenderer extends EntityRenderer<BulletEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("pixel_guns", "textures/entity/projectiles/bullet.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);

    public BulletEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    private static void produceVertex(VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, int light, float x, int y, int textureU, int textureV) {
        vertexConsumer.vertex(positionMatrix, x - 0.5f, (float) y - 0.25f, 0.0f).color(255, 255, 255, 255).uv((float) textureU, (float) textureV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(normalMatrix, 0.0f, 1.0f, 0.0f).endVertex();
    }

    public void render(BulletEntity bulletEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();
        matrixStack.scale(0.125f, 0.125f, 0.125f);
        matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        PoseStack.Pose entry = matrixStack.last();
        Matrix4f matrix4f = entry.pose();
        Matrix3f matrix3f = entry.normal();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
        BulletEntityRenderer.produceVertex(vertexConsumer, matrix4f, matrix3f, i, 0.0f, 0, 0, 1);
        BulletEntityRenderer.produceVertex(vertexConsumer, matrix4f, matrix3f, i, 1.0f, 0, 1, 1);
        BulletEntityRenderer.produceVertex(vertexConsumer, matrix4f, matrix3f, i, 1.0f, 1, 1, 0);
        BulletEntityRenderer.produceVertex(vertexConsumer, matrix4f, matrix3f, i, 0.0f, 1, 0, 0);
        matrixStack.popPose();
        super.render(bulletEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(BulletEntity bulletEntity) {
        return TEXTURE;
    }
}

