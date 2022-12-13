package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.RocketLauncherItem;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketLauncherItemModel extends AnimatedGeoModel<RocketLauncherItem> {

    @Override
    public Identifier getAnimationResource(RocketLauncherItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(RocketLauncherItem object) {
        return PixelGuns.res("geo/rocket_launcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(RocketLauncherItem object) {
        return PixelGuns.res("textures/item/rocket_launcher.png");
    }
}