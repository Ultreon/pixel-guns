package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.CrowbarItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrowbarItemModel extends AnimatedGeoModel<CrowbarItem> {

    @Override
    public ResourceLocation getAnimationResource(CrowbarItem animatable) {
        return null;
    }

    @Override
    public ResourceLocation getModelResource(CrowbarItem object) {
        return PixelGuns.res("geo/crowbar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrowbarItem object) {
        return PixelGuns.res("textures/item/crowbar.png");
    }
}