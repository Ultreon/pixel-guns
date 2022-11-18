package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.armor.HazardArmorItem;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HazardArmorModel extends AnimatedGeoModel<HazardArmorItem> {
    
    @Override
    public ResourceLocation getAnimationResource(HazardArmorItem animatable) {
        return null;
    }

    @Override
    public ResourceLocation getModelResource(HazardArmorItem object) {
        return PixelGuns.res("geo/hazard_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HazardArmorItem object) {
        return PixelGuns.res("textures/armor/hazard_armor.png");
    }
}