package com.ultreon.mods.pixelguns.client.entity.model;

import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeEntityModel extends AnimatedGeoModel<GrenadeEntity> {

    @Override
    public Identifier getAnimationResource(GrenadeEntity animatable) {
        return ResourcePath.get("animations/grenade.animation.json");
    }

    @Override
    public Identifier getModelResource(GrenadeEntity object) {
        return ResourcePath.get("geo/grenade.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeEntity object) {
        return ResourcePath.get("textures/entity/projectiles/thrown/grenade.png");
    }
}