package com.ultreon.mods.pixelguns.client.item.renderer;

import com.ultreon.mods.pixelguns.client.item.model.PoliceShieldItemModel;
import com.ultreon.mods.pixelguns.item.PoliceShieldItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class PoliceShieldItemRenderer extends GeoItemRenderer<PoliceShieldItem> {

    public PoliceShieldItemRenderer() {
        super(new PoliceShieldItemModel());
    }
}