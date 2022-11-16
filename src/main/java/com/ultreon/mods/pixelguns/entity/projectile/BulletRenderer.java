package com.ultreon.mods.pixelguns.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

@Environment(value = EnvType.CLIENT)
public class BulletRenderer extends EntityRenderer<Bullet> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("pixel_guns", "textures/entity/projectiles/bullet.png");

    public BulletRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    public void render(Bullet bullet, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        super.render(bullet, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public ResourceLocation getTextureLocation(Bullet bullet) {
        return TEXTURE;
    }
}

