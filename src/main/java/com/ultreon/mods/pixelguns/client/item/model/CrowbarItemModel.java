package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.item.CrowbarItem;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrowbarItemModel extends AnimatedGeoModel<CrowbarItem> {

    @Override
    public Identifier getAnimationResource(CrowbarItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(CrowbarItem object) {
        return ResourcePath.get("geo/crowbar.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrowbarItem object) {
        return ResourcePath.get("textures/item/crowbar.png");
    }
}