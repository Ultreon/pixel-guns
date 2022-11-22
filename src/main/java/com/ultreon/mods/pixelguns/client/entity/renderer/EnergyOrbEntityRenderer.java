package com.ultreon.mods.pixelguns.client.entity.renderer;

import com.ultreon.mods.pixelguns.client.entity.model.EnergyOrbEntityModel;
import com.ultreon.mods.pixelguns.entity.projectile.EnergyOrb;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"deprecation", "unused"})
@Environment(value = EnvType.CLIENT)
public class EnergyOrbEntityRenderer extends EntityRenderer<EnergyOrb> {
    public static final Identifier TEXTURE = new Identifier("pixel_guns", "textures/entity/projectiles/energy_orb.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(TEXTURE);
    private final EnergyOrbEntityModel model;

    public EnergyOrbEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new EnergyOrbEntityModel(ctx.getPart(EnergyOrbEntityModel.LAYER_LOCATION));
    }

    public void render(@NotNull EnergyOrb energyOrb, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        float h = MathHelper.lerpAngle(energyOrb.prevYaw, energyOrb.getYaw(), g);
        float j = MathHelper.lerp(g, energyOrb.prevPitch, energyOrb.getPitch());
        float k = (float)energyOrb.age + g;
        matrixStack.translate(0.0, 0.15f, 0.0);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.sin(k * 0.1f) * 180.0f));
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.cos(k * 0.1f) * 180.0f));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.sin(k * 0.15f) * 360.0f));
        matrixStack.scale(-0.5f, -0.5f, 0.5f);
        this.model.setAngles(energyOrb, 0.0f, 0.0f, 0.0f, h, j);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.scale(1.5f, 1.5f, 1.5f);
        VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(LAYER);
        this.model.render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 0.15f);
        matrixStack.pop();
        super.render(energyOrb, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(@NotNull EnergyOrb energyOrb) {
        return TEXTURE;
    }
}