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

import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class UfoEntityRenderer extends GeoProjectilesRenderer<UfoEntity> {

	public UfoEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx, new UfoEntityModel());
	}

	@Override
	public RenderLayer getRenderType(UfoEntity ufo, float partialTicks, MatrixStack stack,
			@Nullable VertexConsumerProvider renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		stack.scale(ufo.getScale(), ufo.getScale(), ufo.getScale());
		return super.getRenderType(ufo, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
	}
}