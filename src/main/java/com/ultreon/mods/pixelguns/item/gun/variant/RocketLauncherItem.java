package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.item.gun.GunItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RocketLauncherItem extends GunItem implements IAnimatable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public RocketLauncherItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                0,
                0,
                0,
                null,
                0,
                0,
                0,
                0,
                0,
                null,
                null,
                null,
                null,
                0,
                false,
                0,
                0,
                0
        );
    }


    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}