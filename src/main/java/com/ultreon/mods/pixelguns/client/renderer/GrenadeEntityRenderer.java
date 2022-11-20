package com.ultreon.mods.pixelguns.client.renderer;

import com.ultreon.mods.pixelguns.client.model.GrenadeEntityModel;
import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class GrenadeEntityRenderer extends GeoProjectilesRenderer<GrenadeEntity> {

    public GrenadeEntityRenderer(Context ctx) {
        super(ctx, new GrenadeEntityModel());
    }
}