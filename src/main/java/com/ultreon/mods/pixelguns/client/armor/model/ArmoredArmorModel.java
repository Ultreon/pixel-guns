package com.ultreon.mods.pixelguns.client.armor.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.armor.ArmoredArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmoredArmorModel extends AnimatedGeoModel<ArmoredArmorItem> {
    
    @Override
    public Identifier getAnimationResource(ArmoredArmorItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(ArmoredArmorItem object) {
        return PixelGuns.res("geo/armored_vest.geo.json");
    }

    @Override
    public Identifier getTextureResource(ArmoredArmorItem object) {
        return PixelGuns.res("textures/armor/armored_armor.png");
    }
}