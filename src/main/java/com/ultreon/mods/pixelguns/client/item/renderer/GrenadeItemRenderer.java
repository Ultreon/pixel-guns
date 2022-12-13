package com.ultreon.mods.pixelguns.client.item.renderer;

import com.ultreon.mods.pixelguns.client.item.model.GrenadeItemModel;
import com.ultreon.mods.pixelguns.item.GrenadeItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GrenadeItemRenderer extends GeoItemRenderer<GrenadeItem> {

    public GrenadeItemRenderer() {
        super(new GrenadeItemModel());
    }
}