package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.item.gun.variant.InfinityGunItem;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InfinityGunItemModel extends AnimatedGeoModel<InfinityGunItem> {
    private static final Identifier modelResource = ResourcePath.get("geo/infinity_gun.geo.json");
    private static final Identifier textureResource = ResourcePath.get("textures/gun/infinity_gun.png");

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
