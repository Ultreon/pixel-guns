package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.NuclearExplosionEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NuclearExplosionEntityModel extends AnimatedGeoModel<NuclearExplosionEntity> {

    @Override
    public Identifier getAnimationResource(NuclearExplosionEntity animatable) {
        return PixelGuns.res("animations/nuclear_explosion.animation.json");
    }

    @Override
    public Identifier getModelResource(NuclearExplosionEntity object) {
        return PixelGuns.res("geo/nuclear_explosion.geo.json");
    }

    @Override
    public Identifier getTextureResource(NuclearExplosionEntity object) {
        return PixelGuns.res("textures/entity/nuclear_explosion.png");
    }
}