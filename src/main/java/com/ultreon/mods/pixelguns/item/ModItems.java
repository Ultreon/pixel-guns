package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.armor.ArmoredArmorItem;
import com.ultreon.mods.pixelguns.armor.HazardArmorItem;
import com.ultreon.mods.pixelguns.armor.ModArmorMaterials;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item HARDENED_IRON_INGOT = ModItems.registerItem("hardened_iron_ingot", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item HARDENED_IRON_NUGGET = ModItems.registerItem("hardened_iron_nugget", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item PLASTIC = ModItems.registerItem("plastic", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item PISTOL_GRIP = ModItems.registerItem("pistol_grip", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item GUN_SCOPE = ModItems.registerItem("gun_scope", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item LONG_BARREL = ModItems.registerItem("long_barrel", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item SHORT_BARREL = ModItems.registerItem("short_barrel", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item WOODEN_STOCK = ModItems.registerItem("wooden_stock", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item MODERN_STOCK = ModItems.registerItem("modern_stock", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item WOODEN_HANDGUARD = ModItems.registerItem("wooden_handguard", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item MODERN_HANDGUARD = ModItems.registerItem("modern_handguard", new Item(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item BLUEPRINT_BUNDLE = ModItems.registerItem("blueprint_bundle", new BlueprintBundleItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(1)));
    public static final Item PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_pistol_light", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item HEAVY_PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_pistol_heavy", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item MAGNUM_REVOLVER_BLUEPRINT = ModItems.registerItem("blueprint_revolver_magnum", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item MACHINE_PISTOL_BLUEPRINT = ModItems.registerItem("blueprint_smg_machinepistol", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item LIGHT_ASSAULT_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_assaultrifle_light", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item HEAVY_ASSAULT_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_assaultrifle_heavy", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item COMBAT_SHOTGUN_BLUEPRINT = ModItems.registerItem("blueprint_shotgun_combat", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item CLASSIC_SNIPER_RIFLE_BLUEPRINT = ModItems.registerItem("blueprint_sniper_classic", new BlueprintItem(new FabricItemSettings().group(PixelGuns.MISC).maxCount(64)));
    public static final Item STANDARD_HANDGUN_BULLET = ModItems.registerItem("standard_handgun_cartridge", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(64)));
    public static final Item HEAVY_HANDGUN_BULLET = ModItems.registerItem("heavy_handgun_cartridge", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(64)));
    public static final Item STANDARD_RIFLE_BULLET = ModItems.registerItem("standard_rifle_cartridge", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(64)));
    public static final Item HEAVY_RIFLE_BULLET = ModItems.registerItem("heavy_rifle_cartridge", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(64)));
    public static final Item SHOTGUN_SHELL = ModItems.registerItem("shotgun_shell", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(64)));
    public static final Item ENERGY_GUN_BATTERY = ModItems.registerItem("energy_gun_battery", new Item(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(12)));
    public static final Item PISTOL = ModItems.registerItem("pistol_light", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 5.5f, 4, 17, STANDARD_HANDGUN_BULLET, 26, 0.25f, 2.5f, 1, 1, ModSounds.RELOAD_GENERIC_PISTOL_P1, ModSounds.RELOAD_GENERIC_PISTOL_P2, ModSounds.RELOAD_GENERIC_PISTOL_P3, ModSounds.PISTOL_LIGHT, 1, false, 6, 16, 20) {
    });
    public static final Item HEAVY_PISTOL = ModItems.registerItem("pistol_heavy", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 11.0f, 5, 7, HEAVY_HANDGUN_BULLET, 26, 0.25f, 7.5f, 1, 1, ModSounds.RELOAD_GENERIC_PISTOL_P1, ModSounds.RELOAD_GENERIC_PISTOL_P2, ModSounds.RELOAD_GENERIC_PISTOL_P3, ModSounds.PISTOL_HEAVY, 1, false, 6, 16, 20) {
    });
    public static final Item MAGNUM_REVOLVER = ModItems.registerItem("revolver_magnum", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 11.0f, 10, 6, HEAVY_HANDGUN_BULLET, 40, 0.125f, 6.5f, 1, 1, ModSounds.RELOAD_GENERIC_REVOLVER_P1, ModSounds.RELOAD_GENERIC_REVOLVER_P2, ModSounds.RELOAD_GENERIC_REVOLVER_P3, ModSounds.REVOLVER_MAGNUM, 1, false, 1, 26, 34) {
    });
    public static final Item MACHINE_PISTOL = ModItems.registerItem("smg_machinepistol", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 5.0f, 1, 30, STANDARD_HANDGUN_BULLET, 44, 5.0f, 2.0f, 1, 1, ModSounds.RELOAD_GENERIC_SMG_P1, ModSounds.RELOAD_GENERIC_SMG_P2, ModSounds.RELOAD_GENERIC_SMG_P3, ModSounds.SMG_MACHINEPISTOL, 1, false, 5, 17, 30) {
    });
    public static final Item LIGHT_ASSAULT_RIFLE = ModItems.registerItem("assaultrifle_light", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 5.5f, 2, 30, STANDARD_RIFLE_BULLET, 44, 0.15f, 1.625f, 1, 1, ModSounds.RELOAD_GENERIC_AR_P1, ModSounds.RELOAD_GENERIC_AR_P2, ModSounds.RELOAD_GENERIC_AR_P3, ModSounds.ASSAULTRIFLE_LIGHT, 1, false, 6, 18, 37) {
    });
    public static final Item HEAVY_ASSAULT_RIFLE = ModItems.registerItem("assaultrifle_heavy", new AssaultRifleItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 8.0f, 3, 20, STANDARD_RIFLE_BULLET, 48, 0.125f, 2.5f, 1, 1, ModSounds.RELOAD_HEAVY_AR_P1, ModSounds.RELOAD_HEAVY_AR_P2, ModSounds.RELOAD_HEAVY_AR_P3, ModSounds.ASSAULTRIFLE_HEAVY, ModSounds.ASSAULTRIFLE_HEAVY_EG, 1, false, 6, 22, 40) {
    });
    public static final Item COMBAT_SHOTGUN = ModItems.registerItem("shotgun_combat", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 5.5f, 14, 6, SHOTGUN_SHELL, 26, 9.25f, 8.25f, 5, 2, ModSounds.RELOAD_COMBAT_SHOTGUN_P1, ModSounds.RELOAD_COMBAT_SHOTGUN_P2, ModSounds.RELOAD_COMBAT_SHOTGUN_P3, ModSounds.SHOTGUN_COMBAT, 6, false, 1, 4, 13) {
    });
    public static final Item CLASSIC_SNIPER_RIFLE = ModItems.registerItem("sniper_classic", new GunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 22.0f, 20, 5, HEAVY_RIFLE_BULLET, 26, 0.01f, 8.25f, 1, 2, ModSounds.RELOAD_GENERIC_SNIPER_P1, ModSounds.RELOAD_CLASSIC_SNIPER_P2, ModSounds.RELOAD_GENERIC_SNIPER_P3, ModSounds.SNIPER_CLASSIC, 5, true, 1, 8, 17) {
    });
    public static final Item INFINITY_GUN = ModItems.registerItem("infinity_gun", new InfinityGunItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1)) {
    });
    public static final Item ROCKET_LAUNCHER = ModItems.registerItem("rocket_launcher", new RocketLauncherItem(new FabricItemSettings().group(PixelGuns.GUNS).maxCount(1), 0, 0, 0, null, 0, 0, 0, 0, 0, null, null, null, null, 0, false, 0, 0, 0)
    );

    public static final Item ARMORED_VEST = ModItems.registerItem(
        "armored_vest",
        new ArmoredArmorItem(ModArmorMaterials.ARMORED, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT))
    );

    public static final Item GAS_MASK = ModItems.registerItem(
        "gas_mask",
        new HazardArmorItem(ModArmorMaterials.HAZARD, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT))
    );

    public static final Item KATANA = ModItems.registerItem(
        "katana",
        new KatanaItem(ToolMaterials.DIAMOND, 3, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT))
    );

    public static final Item CROWBAR = ModItems.registerItem(
        "crowbar",
        new CrowbarItem(ToolMaterials.IRON, 3, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT))
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("pixel_guns", name), item);
    }

    public static final Item GRENADE = ModItems.registerItem(
        "grenade",
        new GrenadeItem(new FabricItemSettings().maxCount(16).group(ItemGroup.COMBAT))
    );

    public static final Item POLICE_SHIELD = ModItems.registerItem(
        "police_shield",
        new ShieldItem(new FabricItemSettings().maxDamage(500).group(ItemGroup.COMBAT))
    );

    public static void registerModItems() {
        PixelGuns.LOGGER.info("Registering ModItems for pixel_guns");
    }
}