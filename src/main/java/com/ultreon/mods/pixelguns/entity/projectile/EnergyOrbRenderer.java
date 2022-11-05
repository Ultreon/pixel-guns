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
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"deprecation", "unused"})
@Environment(value = EnvType.CLIENT)
public class EnergyOrbRenderer extends EntityRenderer<EnergyOrb> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("pixel_guns", "textures/entity/projectiles/energy_orb.png");
    private static final RenderType LAYER = RenderType.entityCutoutNoCull(TEXTURE);
    private final EnergyOrbModel model;

    public EnergyOrbRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new EnergyOrbModel(ctx.bakeLayer(EnergyOrbModel.LAYER_LOCATION));
    }

    public void render(@NotNull EnergyOrb energyOrb, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();
        float h = Mth.rotlerp(energyOrb.yRotO, energyOrb.getYRot(), g);
        float j = Mth.lerp(g, energyOrb.xRotO, energyOrb.getXRot());
        float k = (float)energyOrb.tickCount + g;
        matrixStack.translate(0.0, 0.15f, 0.0);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(Mth.sin(k * 0.1f) * 180.0f));
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.cos(k * 0.1f) * 180.0f));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.sin(k * 0.15f) * 360.0f));
        matrixStack.scale(-0.5f, -0.5f, 0.5f);
        this.model.setupAnim(energyOrb, 0.0f, 0.0f, 0.0f, h, j);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE));
        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.scale(1.5f, 1.5f, 1.5f);
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(LAYER);
        this.model.renderToBuffer(matrixStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 0.15f);
        matrixStack.popPose();
        super.render(energyOrb, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(@NotNull EnergyOrb energyOrb) {
        return TEXTURE;
    }
}

