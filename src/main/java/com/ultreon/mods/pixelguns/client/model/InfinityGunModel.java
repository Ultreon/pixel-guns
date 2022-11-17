package com.ultreon.mods.pixelguns.client.model;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InfinityGunModel extends AnimatedGeoModel<InfinityGunItem> {
    private static final ResourceLocation modelResource = PixelGuns.res("geo/infinity_gun.geo.json");
    private static final ResourceLocation textureResource = PixelGuns.res("textures/gun/infinity_gun.png");

    @Override
    public ResourceLocation getModelResource(InfinityGunItem item) {
        return modelResource;
    }

    @Override
    public ResourceLocation getTextureResource(InfinityGunItem item) {
        return textureResource;
    }

    @Override
    public ResourceLocation getAnimationResource(InfinityGunItem item) {
        return null;
    }
}
