package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.ArmoredArmorItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmoredArmorModel extends AnimatedGeoModel<ArmoredArmorItem> {
    
    @Override
    public ResourceLocation getAnimationResource(ArmoredArmorItem animatable) {
        return null;
    }

    @Override
    public ResourceLocation getModelResource(ArmoredArmorItem object) {
        return PixelGuns.res("geo/armored_vest.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArmoredArmorItem object) {
        return PixelGuns.res("textures/armor/armored_armor.png");
    }
}