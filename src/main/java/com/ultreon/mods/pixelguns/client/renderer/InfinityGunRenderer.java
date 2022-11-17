package com.ultreon.mods.pixelguns.client.renderer;

import com.ultreon.mods.pixelguns.client.model.InfinityGunModel;
import com.ultreon.mods.pixelguns.item.InfinityGunItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class InfinityGunRenderer extends GeoItemRenderer<InfinityGunItem> {
    public InfinityGunRenderer() {
        super(new InfinityGunModel());
    }
}
