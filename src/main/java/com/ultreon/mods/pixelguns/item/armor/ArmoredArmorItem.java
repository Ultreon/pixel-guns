package com.ultreon.mods.pixelguns.item.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ArmoredArmorItem extends ArmorItem implements IAnimatable {
	
	private AnimationFactory factory = new AnimationFactory(this);

	public ArmoredArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Settings builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void registerControllers(AnimationData data) {}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}