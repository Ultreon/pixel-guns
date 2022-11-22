package com.ultreon.mods.pixelguns.client.armor.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.armor.HazardArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HazardArmorModel extends AnimatedGeoModel<HazardArmorItem> {
    
    @Override
    public Identifier getAnimationResource(HazardArmorItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(HazardArmorItem object) {
        return PixelGuns.res("geo/hazard_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(HazardArmorItem object) {
        return PixelGuns.res("textures/armor/hazard_armor.png");
    }
}