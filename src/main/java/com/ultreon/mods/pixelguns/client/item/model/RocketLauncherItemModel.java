package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.item.gun.variant.RocketLauncherItem;

import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketLauncherItemModel extends AnimatedGeoModel<RocketLauncherItem> {

    @Override
    public Identifier getAnimationResource(RocketLauncherItem animatable) {
        return ResourcePath.get("animations/rocket_launcher.animation.json");
    }

    @Override
    public Identifier getModelResource(RocketLauncherItem object) {
        return ResourcePath.get("geo/rocket_launcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(RocketLauncherItem object) {
        return ResourcePath.get("textures/gun/rocket_launcher.png");
    }
}