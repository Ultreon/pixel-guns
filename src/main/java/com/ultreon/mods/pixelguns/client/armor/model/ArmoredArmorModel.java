package com.ultreon.mods.pixelguns.client.armor.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.armor.ArmoredArmor;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmoredArmorModel extends AnimatedGeoModel<ArmoredArmor> {
    
    @Override
    public Identifier getAnimationResource(ArmoredArmor animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(ArmoredArmor object) {
        return PixelGuns.res("geo/armored_vest.geo.json");
    }

    @Override
    public Identifier getTextureResource(ArmoredArmor object) {
        return PixelGuns.res("textures/armor/armored_armor.png");
    }
}