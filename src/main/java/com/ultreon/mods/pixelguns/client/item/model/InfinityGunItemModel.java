package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InfinityGunItemModel extends AnimatedGeoModel<InfinityGunItem> {
    private static final Identifier modelResource = PixelGuns.res("geo/infinity_gun.geo.json");
    private static final Identifier textureResource = PixelGuns.res("textures/gun/infinity_gun.png");

    @Override
    public Identifier getModelResource(InfinityGunItem item) {
        return modelResource;
    }

    @Override
    public Identifier getTextureResource(InfinityGunItem item) {
        return textureResource;
    }

    @Override
    public Identifier getAnimationResource(InfinityGunItem item) {
        return null;
    }
}
