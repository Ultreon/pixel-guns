package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.KatanaItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KatanaItemModel extends AnimatedGeoModel<KatanaItem> {

    @Override
    public ResourceLocation getAnimationResource(KatanaItem animatable) {
        return null;
    }

    @Override
    public ResourceLocation getModelResource(KatanaItem object) {
        return PixelGuns.res("geo/katana.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KatanaItem object) {
        return PixelGuns.res("textures/item/katana.png");
    }
}