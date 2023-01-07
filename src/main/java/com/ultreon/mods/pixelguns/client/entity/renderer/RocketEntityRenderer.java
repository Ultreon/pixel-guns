package com.ultreon.mods.pixelguns.client.entity.renderer;

import com.ultreon.mods.pixelguns.client.entity.model.RocketEntityModel;
import com.ultreon.mods.pixelguns.entity.projectile.RocketEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class RocketEntityRenderer extends GeoProjectilesRenderer<RocketEntity> {

    public RocketEntityRenderer(Context ctx) {
        super(ctx, new RocketEntityModel());
    }
}