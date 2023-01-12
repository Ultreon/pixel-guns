package com.ultreon.mods.pixelguns.client.entity.model;

import com.ultreon.mods.pixelguns.entity.NuclearBombEntity;

import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NuclearBombEntityModel extends AnimatedGeoModel<NuclearBombEntity> {

    @Override
    public Identifier getAnimationResource(NuclearBombEntity animatable) {
        return ResourcePath.get("animations/nuclear_bomb.animation.json");
    }

    @Override
    public Identifier getModelResource(NuclearBombEntity object) {
        return ResourcePath.get("geo/nuclear_bomb.geo.json");
    }

    @Override
    public Identifier getTextureResource(NuclearBombEntity object) {
        return ResourcePath.get("textures/entity/nuclear_bomb.png");
    }
}