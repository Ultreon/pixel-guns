package com.ultreon.mods.pixelguns.client.armor.renderer;

import com.ultreon.mods.pixelguns.armor.ArmoredArmor;
import com.ultreon.mods.pixelguns.client.armor.model.ArmoredArmorModel;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmoredArmorRenderer extends GeoArmorRenderer<ArmoredArmor> {

    public ArmoredArmorRenderer() {
        super(new ArmoredArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}