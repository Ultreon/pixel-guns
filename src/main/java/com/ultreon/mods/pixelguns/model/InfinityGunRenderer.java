package com.ultreon.mods.pixelguns.model;

import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class InfinityGunRenderer extends GeoItemRenderer<InfinityGunItem> {
    public InfinityGunRenderer() {
        super(new InfinityGunModel());
    }
}
