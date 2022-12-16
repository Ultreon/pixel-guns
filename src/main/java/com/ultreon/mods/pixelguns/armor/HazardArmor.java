package com.ultreon.mods.pixelguns.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class HazardArmor extends ArmorItem implements IAnimatable {
	
	private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public HazardArmor(ArmorMaterial materialIn, EquipmentSlot slot, Settings builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}