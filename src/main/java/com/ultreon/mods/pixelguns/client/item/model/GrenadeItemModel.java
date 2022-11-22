package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.GrenadeItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrenadeItemModel extends AnimatedGeoModel<GrenadeItem> {

    @Override
    public Identifier getAnimationResource(GrenadeItem animatable) {
        return PixelGuns.res("animations/grenade.animation.json");
    }

    @Override
    public Identifier getModelResource(GrenadeItem object) {
        return PixelGuns.res("geo/grenade.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeItem object) {
        return PixelGuns.res("textures/entity/projectiles/thrown/grenade.png");
    }
}