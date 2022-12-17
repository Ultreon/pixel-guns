package com.ultreon.mods.pixelguns.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ModArmorMaterials implements ArmorMaterial {

    ARMORED(
        "armored",
        new EquipmentAmounts(0, 225, 0, 0),
        new EquipmentAmounts(0, 10, 0, 0),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
        0.0f,
        0.0f,
        Ingredient.empty()
    ),

    HAZARD(
        "hazard",
        new EquipmentAmounts(2400, 0, 0, 0),
        new EquipmentAmounts(0, 0, 0, 0),
        15,
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
        0.0f,
        0.0f,
        Ingredient.empty()
    );

    private final String name;
    private final EquipmentAmounts durabilityAmounts;
    private final EquipmentAmounts protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient ingredient;

    record EquipmentAmounts(int helmet, int chestplate, int leggings, int boots) {};

    private ModArmorMaterials(String name, EquipmentAmounts durabilityAmounts, EquipmentAmounts protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient ingredient) {
        this.name = name;
        this.durabilityAmounts = durabilityAmounts;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.ingredient = ingredient;
    }

    @Override
    public int getDurability(EquipmentSlot slot) {
        return switch (slot) {
            case FEET -> this.durabilityAmounts.boots;
            case LEGS -> this.durabilityAmounts.leggings;
            case CHEST -> this.durabilityAmounts.chestplate;
            case HEAD -> this.durabilityAmounts.helmet;
            default -> 0;
        };
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return switch (slot) {
            case FEET -> this.protectionAmounts.boots;
            case LEGS -> this.protectionAmounts.leggings;
            case CHEST -> this.protectionAmounts.chestplate;
            case HEAD -> this.protectionAmounts.helmet;
            default -> 0;
        };
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.ingredient;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}