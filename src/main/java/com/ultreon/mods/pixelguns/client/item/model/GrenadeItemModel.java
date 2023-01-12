package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.item.GrenadeItem;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeItemModel extends AnimatedGeoModel<GrenadeItem> {

    @Override
    public Identifier getAnimationResource(GrenadeItem animatable) {
        return ResourcePath.get("animations/grenade.animation.json");
    }

    @Override
    public Identifier getModelResource(GrenadeItem object) {
        return ResourcePath.get("geo/grenade.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeItem object) {
        return ResourcePath.get("textures/entity/projectiles/thrown/grenade.png");
    }
}