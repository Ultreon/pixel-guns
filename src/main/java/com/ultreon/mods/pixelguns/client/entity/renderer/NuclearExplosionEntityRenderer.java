package com.ultreon.mods.pixelguns.client.entity.renderer;

import javax.annotation.Nullable;

import com.ultreon.mods.pixelguns.client.entity.model.NuclearExplosionEntityModel;
import com.ultreon.mods.pixelguns.entity.NuclearExplosionEntity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class NuclearExplosionEntityRenderer extends GeoProjectilesRenderer<NuclearExplosionEntity> {

    public NuclearExplosionEntityRenderer(Context ctx) {
        super(ctx, new NuclearExplosionEntityModel());
    }
    
    @Override
    public RenderLayer getRenderType(
            NuclearExplosionEntity entity,
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