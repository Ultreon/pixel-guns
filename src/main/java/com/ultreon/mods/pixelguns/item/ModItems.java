package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.armor.ArmoredArmor;
import com.ultreon.mods.pixelguns.armor.ModArmorMaterials;
import com.ultreon.mods.pixelguns.block.ModBlocks;
import com.ultreon.mods.pixelguns.item.gun.variant.*;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item HARDENED_IRON_INGOT = ModItems.registerItem("hardened_iron_ingot", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HARDENED_IRON_NUGGET = ModItems.registerItem("hardened_iron_nugget", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item PLASTIC = ModItems.registerItem("plastic", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item PISTOL_GRIP = ModItems.registerItem("pistol_grip", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item GUN_SCOPE = ModItems.registerItem("gun_scope", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item LONG_BARREL = ModItems.registerItem("long_barrel", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item SHORT_BARREL = ModItems.registerItem("short_barrel", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item WOODEN_STOCK = ModItems.registerItem("wooden_stock", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MODERN_STOCK = ModItems.registerItem("modern_stock", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item WOODEN_HANDGUARD = ModItems.registerItem("wooden_handguard", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MODERN_HANDGUARD = ModItems.registerItem("modern_handguard", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));

    // Blueprints
    public static final Item BLUEPRINT_BUNDLE = ModItems.registerItem("blueprint_bundle", new BlueprintBundleItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(1)));
    public static final Item PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_pistol_light", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HEAVY_PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_pistol_heavy", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MAGNUM_REVOLVER_BLUEPRINT = ModItems.registerItem("blueprint_revolver_magnum", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MACHINE_PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_smg_machinepistol", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item LIGHT_ASSAULT_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_assaultrifle_light", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HEAVY_ASSAULT_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_assaultrifle_heavy", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item COMBAT_SHOTGUN_BLUEPRINT = ModItems.registerItem("blueprint_shotgun_combat", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item CLASSIC_SNIPER_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_sniper_classic", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));

    // Ammunition
    public static final Item STANDARD_HANDGUN_BULLET = ModItems.registerItem("standard_handgun_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item HEAVY_HANDGUN_BULLET = ModItems.registerItem("heavy_handgun_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item STANDARD_RIFLE_BULLET = ModItems.registerItem("standard_rifle_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item HEAVY_RIFLE_BULLET = ModItems.registerItem("heavy_rifle_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item SHOTGUN_SHELL = ModItems.registerItem("shotgun_shell", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item ENERGY_GUN_BATTERY = ModItems.registerItem("energy_gun_battery", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(12)){
        @Override
        public boolean hasGlint(ItemStack itemStack) {
            return true;
        }
    });

    // Guns
    public static final Item PISTOL = ModItems.registerItem("pistol_light", new LightPistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item HEAVY_PISTOL = ModItems.registerItem("pistol_heavy", new HeavyPistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item MAGNUM_REVOLVER = ModItems.registerItem("revolver_magnum", new MagnumRevolverItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item MACHINE_PISTOL = ModItems.registerItem("smg_machinepistol", new MachinePistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item LIGHT_ASSAULT_RIFLE = ModItems.registerItem("assaultrifle_light", new AssaultRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item HEAVY_ASSAULT_RIFLE = ModItems.registerItem("assaultrifle_heavy", new HeavyAssaultRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item COMBAT_SHOTGUN = ModItems.registerItem("shotgun_combat", new CombatShotgunItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item CLASSIC_SNIPER_RIFLE = ModItems.registerItem("sniper_classic", new SniperRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item INFINITY_GUN = ModItems.registerItem("infinity_gun", new InfinityGunItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item ROCKET_LAUNCHER = ModItems.registerItem("rocket_launcher", new RocketLauncherItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));


    // Armor
    public static final Item ARMORED_VEST = ModItems.registerItem(
        "armored_vest",
        new ArmoredArmor(ModArmorMaterials.ARMORED, EquipmentSlot.CHEST, new FabricItemSettings().group(ModCreativeTab.MISC))
    );

    public static final Item GAS_MASK = ModItems.registerItem(
        "gas_mask",
        new GasMaskItem(new FabricItemSettings().group(ModCreativeTab.MISC))
    );

    public static final Item KATANA = ModItems.registerItem(
        "katana",
        new KatanaItem(ToolMaterials.DIAMOND, 3, -2.4f, new FabricItemSettings().group(ModCreativeTab.WEAPONS))
    );

    public static final Item CROWBAR = ModItems.registerItem(
        "crowbar",
        new CrowbarItem(ToolMaterials.IRON, 3, -2.4f, new FabricItemSettings().group(ModCreativeTab.WEAPONS))
    );

    public static final Item GRENADE = ModItems.registerItem(
        "grenade",
        new GrenadeItem(new FabricItemSettings().maxCount(16).group(ModCreativeTab.WEAPONS))
    );

    public static final Item POLICE_SHIELD = ModItems.registerItem(
        "police_shield",
        new ShieldItem(new FabricItemSettings().maxDamage(500).group(ModCreativeTab.MISC))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("pixel_guns", name), item);
    }

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(PixelGuns.MOD_ID, "ufo_interior"), new BlockItem(ModBlocks.UFO_INTERIOR, new FabricItemSettings()));
    }
}