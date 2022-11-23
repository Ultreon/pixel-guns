package com.ultreon.mods.pixelguns.client.entity.renderer;

import javax.annotation.Nullable;

import com.ultreon.mods.pixelguns.client.entity.model.UfoEntityModel;
import com.ultreon.mods.pixelguns.entity.ufo.UfoEntity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class UfoEntityRenderer extends GeoProjectilesRenderer<UfoEntity> {

	public UfoEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new UfoEntityModel());
	}

	protected int getBlockLight(UfoEntity entityIn, BlockPos partialTicks) {
		return 15;
	}

	@Override
	public RenderLayer getRenderType(UfoEntity ufo, float partialTicks, MatrixStack stack,
			@Nullable VertexConsumerProvider renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		stack.scale(ufo.getScale(), 0.4f * ufo.getScale(), ufo.getScale());
		return super.getRenderType(ufo, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
	}
}