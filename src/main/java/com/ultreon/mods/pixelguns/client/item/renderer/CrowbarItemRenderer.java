package com.ultreon.mods.pixelguns.client.item.renderer;

import com.ultreon.mods.pixelguns.client.item.model.CrowbarItemModel;
import com.ultreon.mods.pixelguns.item.CrowbarItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CrowbarItemRenderer extends GeoItemRenderer<CrowbarItem> {

    public CrowbarItemRenderer() {
        super(new CrowbarItemModel());
    }
}