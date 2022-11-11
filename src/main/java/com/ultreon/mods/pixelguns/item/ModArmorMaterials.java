package com.ultreon.mods.pixelguns.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {

    ARMORED(
        "armored",
        new DurabilityAmounts(0, 225, 0, 0),
        new ProtectionAmounts(0, 10, 0, 0),
        15,
        SoundEvents.ARMOR_EQUIP_LEATHER,
        0.0f,
        0.0f,
        Ingredient.of(Items.BEDROCK)
    );

    private final String name;
    private final DurabilityAmounts durabilityAmounts;
    private final ProtectionAmounts protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Ingredient ingredient;

    record DurabilityAmounts(int helmet, int chestplate, int leggings, int boots) {};
    record ProtectionAmounts(int helmet, int chestplate, int leggings, int boots) {};

    private ModArmorMaterials(String name, DurabilityAmounts durabilityAmounts, ProtectionAmounts protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Ingredient ingredient) {
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
    public int getDurabilityForSlot(EquipmentSlot slot) {
        switch (slot.getName()) {
            case "feet": return this.durabilityAmounts.boots;
            case "legs": return this.durabilityAmounts.leggings;
            case "chest": return this.durabilityAmounts.chestplate;
            case "head": return this.durabilityAmounts.helmet;
            default: return 0;
        }
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        switch (slot.getName()) {
            case "feet": return this.protectionAmounts.boots;
            case "legs": return this.protectionAmounts.leggings;
            case "chest": return this.protectionAmounts.chestplate;
            case "head": return this.protectionAmounts.helmet;
            default: return 0;
        }
    }

    @Override
    public int getEnchantmentValue() {
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