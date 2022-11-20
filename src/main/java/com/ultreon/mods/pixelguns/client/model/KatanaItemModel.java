package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.KatanaItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KatanaItemModel extends AnimatedGeoModel<KatanaItem> {

    @Override
    public Identifier getAnimationResource(KatanaItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(KatanaItem object) {
        return PixelGuns.res("geo/katana.geo.json");
    }

    @Override
    public Identifier getTextureResource(KatanaItem object) {
        return PixelGuns.res("textures/item/katana.png");
    }
}