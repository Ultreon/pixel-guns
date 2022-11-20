package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeEntityModel extends AnimatedGeoModel<GrenadeEntity> {

    @Override
    public Identifier getAnimationResource(GrenadeEntity animatable) {
        return PixelGuns.res("animations/grenade.animation.json");
    }

    @Override
    public Identifier getModelResource(GrenadeEntity object) {
        return PixelGuns.res("geo/grenade.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeEntity object) {
        return PixelGuns.res("textures/entity/projectiles/thrown/grenade.png");
    }
}