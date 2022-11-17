package com.ultreon.mods.pixelguns.client.renderer;

import com.ultreon.mods.pixelguns.client.model.KatanaItemModel;
import com.ultreon.mods.pixelguns.item.KatanaItem;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class KatanaItemRenderer extends GeoItemRenderer<KatanaItem> {
    
    public KatanaItemRenderer() {
        super(new KatanaItemModel());
    }
}