package com.ultreon.mods.pixelguns.client.item.renderer;

import com.ultreon.mods.pixelguns.client.item.model.InfinityGunItemModel;
import com.ultreon.mods.pixelguns.item.gun.variant.InfinityGunItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class InfinityGunItemRenderer extends GeoItemRenderer<InfinityGunItem> {
    public InfinityGunItemRenderer() {
        super(new InfinityGunItemModel());
    }
}
