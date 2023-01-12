package com.ultreon.mods.pixelguns.client.armor.model;

import com.ultreon.mods.pixelguns.armor.ArmoredArmor;

import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmoredArmorModel extends AnimatedGeoModel<ArmoredArmor> {
    
    @Override
    public Identifier getAnimationResource(ArmoredArmor animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(ArmoredArmor object) {
        return ResourcePath.get("geo/armored_vest.geo.json");
    }

    @Override
    public Identifier getTextureResource(ArmoredArmor object) {
        return ResourcePath.get("textures/armor/armored_armor.png");
    }
}