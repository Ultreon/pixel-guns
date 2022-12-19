package com.ultreon.mods.pixelguns.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class KatanaItem extends SwordItem implements IAnimatable {

    public KatanaItem(ToolMaterial tier, int i, float f, Settings properties) {
        super(tier, i, f, properties);
    }
    
    /*
     * Animation side
     */

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}