package com.ultreon.mods.pixelguns.client.renderer;

import com.ultreon.mods.pixelguns.client.model.GrenadeItemModel;
import com.ultreon.mods.pixelguns.item.GrenadeItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GrenadeItemRenderer extends GeoItemRenderer<GrenadeItem> {

    public GrenadeItemRenderer() {
        super(new GrenadeItemModel());
    }
}