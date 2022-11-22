package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.PoliceShieldItem;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PoliceShieldItemModel extends AnimatedGeoModel<PoliceShieldItem> {

    @Override
    public Identifier getAnimationResource(PoliceShieldItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(PoliceShieldItem object) {
        return PixelGuns.res("geo/police_shield.geo.json");
    }

    @Override
    public Identifier getTextureResource(PoliceShieldItem object) {
        return PixelGuns.res("textures/item/police_shield.png");
    }
}