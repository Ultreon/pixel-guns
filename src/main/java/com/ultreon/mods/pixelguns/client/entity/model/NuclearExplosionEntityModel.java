package com.ultreon.mods.pixelguns.client.entity.model;

import com.ultreon.mods.pixelguns.entity.NuclearExplosionEntity;

import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NuclearExplosionEntityModel extends AnimatedGeoModel<NuclearExplosionEntity> {

    @Override
    public Identifier getAnimationResource(NuclearExplosionEntity animatable) {
        return ResourcePath.get("animations/nuclear_explosion.animation.json");
    }

    @Override
    public Identifier getModelResource(NuclearExplosionEntity object) {
        return ResourcePath.get("geo/nuclear_explosion.geo.json");
    }

    @Override
    public Identifier getTextureResource(NuclearExplosionEntity object) {
        return ResourcePath.get("textures/entity/nuclear_explosion.png");
    }
}