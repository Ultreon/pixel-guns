package com.ultreon.mods.pixelguns.item;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class KatanaItem extends SwordItem implements IAnimatable {

    public KatanaItem(Tier tier, int i, float f, Properties properties) {
        super(tier, i, f, properties);
    }
    
    /*
     * Animation side
     */

    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}