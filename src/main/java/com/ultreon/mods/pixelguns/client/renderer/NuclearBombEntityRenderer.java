package com.ultreon.mods.pixelguns.client.renderer;

import org.jetbrains.annotations.Nullable;

import com.ultreon.mods.pixelguns.client.model.NuclearBombEntityModel;
import com.ultreon.mods.pixelguns.entity.NuclearBombEntity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class NuclearBombEntityRenderer extends GeoProjectilesRenderer<NuclearBombEntity> {

    public NuclearBombEntityRenderer(Context ctx) {
        super(ctx, new NuclearBombEntityModel());
    }
    
    @Override
    public RenderLayer getRenderType(
            NuclearBombEntity entity,
            float partialTicks, 
            MatrixStack stack,
            @Nullable VertexConsumerProvider renderTypeBuffer, 
            @Nullable VertexConsumer vertexBuilder, 
            int packedLightIn, 
            Identifier textureLocation)
    {
        stack.scale(entity.getScale(), entity.getScale(), entity.getScale());
        return super.getRenderType(entity, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}