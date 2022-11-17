package com.ultreon.mods.pixelguns.entity;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<GrenadeEntity> GRENADE = ModEntities.register(
        "grenade", 
        FabricEntityTypeBuilder.create(
            MobCategory.MISC,
            GrenadeEntity::register
        )
        .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
        .trackRangeBlocks(4).trackedUpdateRate(20)
    );

    private static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> type) {
        return Registry.register(
            Registry.ENTITY_TYPE,
            new ResourceLocation(PixelGuns.MOD_ID, name),
            type.build()
        );
    }
}
