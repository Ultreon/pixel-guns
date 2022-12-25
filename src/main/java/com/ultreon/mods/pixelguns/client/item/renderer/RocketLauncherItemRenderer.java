package com.ultreon.mods.pixelguns.client.item.renderer;

import com.ultreon.mods.pixelguns.client.item.model.RocketLauncherItemModel;
import com.ultreon.mods.pixelguns.item.gun.variant.RocketLauncherItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class RocketLauncherItemRenderer extends GeoItemRenderer<RocketLauncherItem> {
    
    public RocketLauncherItemRenderer() {
        super(new RocketLauncherItemModel());
    }
}