package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.armor.ArmoredArmor;
import com.ultreon.mods.pixelguns.armor.ModArmorMaterials;
import com.ultreon.mods.pixelguns.block.ModBlocks;
import com.ultreon.mods.pixelguns.block.UfoInterior;
import com.ultreon.mods.pixelguns.item.gun.variant.*;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item HARDENED_IRON_INGOT = ModItems.register("hardened_iron_ingot", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HARDENED_IRON_NUGGET = ModItems.register("hardened_iron_nugget", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item PLASTIC = ModItems.register("plastic", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item PISTOL_GRIP = ModItems.register("pistol_grip", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item GUN_SCOPE = ModItems.register("gun_scope", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item LONG_BARREL = ModItems.register("long_barrel", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item SHORT_BARREL = ModItems.register("short_barrel", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item WOODEN_STOCK = ModItems.register("wooden_stock", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MODERN_STOCK = ModItems.register("modern_stock", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item WOODEN_HANDGUARD = ModItems.register("wooden_handguard", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MODERN_HANDGUARD = ModItems.register("modern_handguard", new Item(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));

    // Blueprints
    public static final Item BLUEPRINT_BUNDLE = ModItems.register("blueprint_bundle", new BlueprintBundleItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(1)));
    public static final Item PISTOL_BLUEPRINT = ModItems.register("blueprint_pistol_light", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HEAVY_PISTOL_BLUEPRINT = ModItems.register("blueprint_pistol_heavy", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MAGNUM_REVOLVER_BLUEPRINT = ModItems.register("blueprint_revolver_magnum", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item MACHINE_PISTOL_BLUEPRINT = ModItems.register("blueprint_smg_machinepistol", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item LIGHT_ASSAULT_RIFLE_BLUEPRINT = ModItems.register("blueprint_assaultrifle_light", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item HEAVY_ASSAULT_RIFLE_BLUEPRINT = ModItems.register("blueprint_assaultrifle_heavy", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item COMBAT_SHOTGUN_BLUEPRINT = ModItems.register("blueprint_shotgun_combat", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));
    public static final Item CLASSIC_SNIPER_RIFLE_BLUEPRINT = ModItems.register("blueprint_sniper_classic", new BlueprintItem(new FabricItemSettings().group(ModCreativeTab.MISC).maxCount(64)));

    // Ammunition
    public static final Item STANDARD_HANDGUN_BULLET = ModItems.register("standard_handgun_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item HEAVY_HANDGUN_BULLET = ModItems.register("heavy_handgun_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item STANDARD_RIFLE_BULLET = ModItems.register("standard_rifle_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item HEAVY_RIFLE_BULLET = ModItems.register("heavy_rifle_cartridge", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item SHOTGUN_SHELL = ModItems.register("shotgun_shell", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(64)));
    public static final Item ENERGY_GUN_BATTERY = ModItems.register("energy_gun_battery", new Item(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(12)){
        @Override
        public boolean hasGlint(ItemStack itemStack) {
            return true;
        }
    });

    // Guns
    public static final Item PISTOL = ModItems.register("pistol_light", new LightPistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item HEAVY_PISTOL = ModItems.register("pistol_heavy", new HeavyPistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item MAGNUM_REVOLVER = ModItems.register("revolver_magnum", new MagnumRevolverItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item MACHINE_PISTOL = ModItems.register("smg_machinepistol", new MachinePistolItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item LIGHT_ASSAULT_RIFLE = ModItems.register("assaultrifle_light", new AssaultRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item HEAVY_ASSAULT_RIFLE = ModItems.register("assaultrifle_heavy", new HeavyAssaultRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item COMBAT_SHOTGUN = ModItems.register("shotgun_combat", new CombatShotgunItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item CLASSIC_SNIPER_RIFLE = ModItems.register("sniper_classic", new SniperRifleItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item INFINITY_GUN = ModItems.register("infinity_gun", new InfinityGunItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));
    public static final Item ROCKET_LAUNCHER = ModItems.register("rocket_launcher", new RocketLauncherItem(new FabricItemSettings().group(ModCreativeTab.WEAPONS).maxCount(1)));


    // Armor
    public static final Item ARMORED_VEST = ModItems.register(
        "armored_vest",
        new ArmoredArmor(ModArmorMaterials.ARMORED, EquipmentSlot.CHEST, new FabricItemSettings().group(ModCreativeTab.MISC))
    );

    public static final Item GAS_MASK = ModItems.register(
        "gas_mask",
        new GasMaskItem(new FabricItemSettings().group(ModCreativeTab.MISC))
    );

    public static final Item KATANA = ModItems.register(
        "katana",
        new KatanaItem(ToolMaterials.DIAMOND, 3, -2.4f, new FabricItemSettings().group(ModCreativeTab.WEAPONS))
    );

    public static final Item CROWBAR = ModItems.register(
        "crowbar",
        new CrowbarItem(ToolMaterials.IRON, 3, -2.4f, new FabricItemSettings().group(ModCreativeTab.WEAPONS))
    );

    public static final Item GRENADE = ModItems.register(
        "grenade",
        new GrenadeItem(new FabricItemSettings().maxCount(16).group(ModCreativeTab.WEAPONS))
    );

    public static final Item POLICE_SHIELD = ModItems.register(
        "police_shield",
        new ShieldItem(new FabricItemSettings().maxDamage(500).group(ModCreativeTab.MISC))
    );

    // Blocks

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, PixelGuns.res(name), item);
    }

    private static Item register(Identifier id, Item item) {
        return Registry.register(Registry.ITEM, id, item);
    }

    private static Item register(Block block, ItemGroup itemGroup) {
        return ModItems.register(Registry.BLOCK.getId(ModBlocks.UFO_INTERIOR), ModBlocks.UFO_INTERIOR.asItem());
    }
}