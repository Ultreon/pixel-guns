package com.ultreon.mods.pixelguns.item;

import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RocketLauncherItem extends AbstractGunItem implements IAnimatable {

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public RocketLauncherItem(Settings settings, float gunDamage, int rateOfFire, int magSize, Item ammoType,
            int reloadCooldown, float bulletSpread, float gunRecoil, int pelletCount, int loadingType,
            SoundEvent reload1, SoundEvent reload2, SoundEvent reload3, SoundEvent shootSound, int reloadCycles,
            boolean isScoped, int reloadStage1, int reloadStage2, int reloadStage3) {
        super(settings, gunDamage, rateOfFire, magSize, ammoType, reloadCooldown, bulletSpread, gunRecoil, pelletCount,
                loadingType, reload1, reload2, reload3, shootSound, reloadCycles, isScoped, reloadStage1, reloadStage2,
                reloadStage3);
    }

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}