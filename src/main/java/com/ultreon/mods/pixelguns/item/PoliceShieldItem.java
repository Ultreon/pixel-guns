package com.ultreon.mods.pixelguns.item;

import net.minecraft.item.ShieldItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PoliceShieldItem extends ShieldItem implements IAnimatable {

    public PoliceShieldItem(Settings settings) {
        super(settings);
    }

    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
    
}
