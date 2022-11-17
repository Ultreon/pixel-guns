package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeEntityModel extends AnimatedGeoModel<GrenadeEntity> {

    @Override
    public ResourceLocation getAnimationResource(GrenadeEntity animatable) {
        return PixelGuns.res("animations/grenade.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(GrenadeEntity object) {
        return PixelGuns.res("geo/grenade.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrenadeEntity object) {
        return PixelGuns.res("textures/entity/projectiles/thrown/grenade.png");
    }
}