package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.GrenadeItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeItemModel extends AnimatedGeoModel<GrenadeItem> {

    @Override
    public ResourceLocation getAnimationResource(GrenadeItem animatable) {
        return PixelGuns.res("animations/grenade.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(GrenadeItem object) {
        return PixelGuns.res("geo/grenade.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrenadeItem object) {
        return PixelGuns.res("textures/entity/projectiles/thrown/grenade.png");
    }
}