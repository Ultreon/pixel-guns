package com.ultreon.mods.pixelguns.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class CrowbarItem extends SwordItem implements IAnimatable {

    public CrowbarItem(ToolMaterial tier, int i, float f, Settings properties) {
        super(tier, i, f, properties);
    }

    /*
     * ANIMATION
     */

    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}

