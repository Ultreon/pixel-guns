package com.ultreon.mods.pixelguns.client.entity.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.RocketEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketEntityModel extends AnimatedGeoModel<RocketEntity> {

    @Override
    public Identifier getAnimationResource(RocketEntity object) {
        return null;
    }

    @Override
    public Identifier getModelResource(RocketEntity object) {
        return PixelGuns.res("geo/rocket.geo.json");
    }

    @Override
    public Identifier getTextureResource(RocketEntity object) {
        return PixelGuns.res("textures/entity/projectiles/rocket.png");
    }
}