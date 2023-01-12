package com.ultreon.mods.pixelguns.client.item.model;

import com.ultreon.mods.pixelguns.item.KatanaItem;
import com.ultreon.mods.pixelguns.util.ResourcePath;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KatanaItemModel extends AnimatedGeoModel<KatanaItem> {

    @Override
    public Identifier getAnimationResource(KatanaItem animatable) {
        return null;
    }

    @Override
    public Identifier getModelResource(KatanaItem object) {
        return ResourcePath.get("geo/katana.geo.json");
    }

    @Override
    public Identifier getTextureResource(KatanaItem object) {
        return ResourcePath.get("textures/item/katana.png");
    }
}