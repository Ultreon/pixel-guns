package com.ultreon.mods.pixelguns.client.renderer;

import com.ultreon.mods.pixelguns.client.model.ArmoredArmorModel;
import com.ultreon.mods.pixelguns.item.armor.ArmoredArmorItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmoredArmorRenderer extends GeoArmorRenderer<ArmoredArmorItem> {

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