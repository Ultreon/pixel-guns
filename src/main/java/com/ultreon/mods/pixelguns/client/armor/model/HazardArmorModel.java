package com.ultreon.mods.pixelguns.client.armor.model;

import com.ultreon.mods.pixelguns.armor.HazardArmor;

import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HazardArmorModel extends AnimatedGeoModel<HazardArmor> {
    
    @Override
    public Identifier getAnimationResource(HazardArmor animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(HazardArmor object) {
        return ResourcePath.get("geo/hazard_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(HazardArmor object) {
        return ResourcePath.get("textures/armor/hazard_armor.png");
    }
}