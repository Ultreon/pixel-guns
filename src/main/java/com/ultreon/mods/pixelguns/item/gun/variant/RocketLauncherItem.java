package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.registry.ModItems;
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
                25.0f,
                250,
                160,
                1,
                ModItems.ROCKET,
                160,
                0,
                0,
                1,
                LoadingType.INDIVIDUAL,
                null,
                null,
                null,
                null,
                1,
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