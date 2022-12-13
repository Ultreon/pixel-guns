package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.armor.HazardArmor;
import com.ultreon.mods.pixelguns.armor.ModArmorMaterials;
import com.ultreon.mods.pixelguns.entity.GasEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class GasMaskItem extends HazardArmor {

    public GasMaskItem(Settings builder) {
        super(ModArmorMaterials.HAZARD, EquipmentSlot.HEAD, builder);
    }
    
    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int i, boolean bl) {
        if (entity instanceof ServerPlayerEntity player) {
            if (player.getInventory().armor.get(EquipmentSlot.HEAD.getEntitySlotId()) == itemStack && !world.getEntitiesByClass(GasEntity.class, entity.getBoundingBox(), a -> true).isEmpty()) {
                if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                    itemStack.decrement(1);
                } else {
                    itemStack.setDamage(itemStack.getDamage() + 1);
                }
            }
        }
    }
}