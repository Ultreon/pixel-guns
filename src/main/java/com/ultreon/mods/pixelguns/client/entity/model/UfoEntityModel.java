package com.ultreon.mods.pixelguns.client.entity.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.ufo.UfoEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class UfoEntityModel extends AnimatedGeoModel<UfoEntity> {

    @Override
    public Identifier getAnimationResource(UfoEntity animatable) {
        return PixelGuns.res("animations/ufo.animation.json");
    }

    @Override
    public Identifier getModelResource(UfoEntity object) {
        return PixelGuns.res("geo/ufo.geo.json");
    }

    @Override
    public Identifier getTextureResource(UfoEntity object) {
        return PixelGuns.res("textures/entity/ufo.png");
    }
}