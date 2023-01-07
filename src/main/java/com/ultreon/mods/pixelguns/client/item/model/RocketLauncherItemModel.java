package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.gun.variant.RocketLauncherItem;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketLauncherItemModel extends AnimatedGeoModel<RocketLauncherItem> {

    @Override
    public Identifier getAnimationResource(RocketLauncherItem animatable) {
        return PixelGuns.res("animations/rocket_launcher.animation.json");
    }

    @Override
    public Identifier getModelResource(RocketLauncherItem object) {
        return PixelGuns.res("geo/rocket_launcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(RocketLauncherItem object) {
        return PixelGuns.res("textures/gun/rocket_launcher.png");
    }
}