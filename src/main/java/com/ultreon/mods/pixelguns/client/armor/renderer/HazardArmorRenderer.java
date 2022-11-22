package com.ultreon.mods.pixelguns.client.armor.renderer;

import com.ultreon.mods.pixelguns.client.armor.model.HazardArmorModel;
import com.ultreon.mods.pixelguns.item.armor.HazardArmorItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class HazardArmorRenderer extends GeoArmorRenderer<HazardArmorItem> {

    public HazardArmorRenderer() {
        super(new HazardArmorModel());

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