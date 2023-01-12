package com.ultreon.mods.pixelguns.registry;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.client.entity.renderer.*;
import com.ultreon.mods.pixelguns.entity.*;

import com.ultreon.mods.pixelguns.entity.projectile.thrown.GrenadeEntity;
import com.ultreon.mods.pixelguns.entity.projectile.RocketEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    public static final EntityType<GrenadeEntity> GRENADE = EntityRegistry.register(
        "grenade", 
        FabricEntityTypeBuilder.<GrenadeEntity>create(
            SpawnGroup.MISC,
            GrenadeEntity::new
        )
        .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
        .trackRangeBlocks(4).trackedUpdateRate(20)
    );

    public static final EntityType<RocketEntity> ROCKET = EntityRegistry.register(
            "rocket",
            FabricEntityTypeBuilder.<RocketEntity>create(
                            SpawnGroup.MISC,
                            RocketEntity::new
                    )
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4).trackedUpdateRate(20)
    );

    public static final EntityType<NuclearBombEntity> NUCLEAR_BOMB = EntityRegistry.register(
        "nuclear_bomb",
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, NuclearBombEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 0.5f))
    );

    public static final EntityType<NuclearExplosionEntity> NUCLEAR_EXPLOSION = EntityRegistry.register(
            "nuclear_explosion",
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, NuclearExplosionEntity::new)
                    .dimensions(EntityDimensions.fixed(15.0f, 100.0f))
    );

    public static final EntityType<GasEntity> GAS = EntityRegistry.register(
        "gas",
        FabricEntityTypeBuilder.create(SpawnGroup.MISC, GasEntity::new)
            .dimensions(EntityDimensions.fixed(1, 1))
    );

    private static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> type) {
        return Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(PixelGuns.MOD_ID, name),
            type.build()
        );
    }

    public static void registerRenderers() {
        EntityRendererRegistry.register(EntityRegistry.GRENADE, GrenadeEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.ROCKET, RocketEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.NUCLEAR_BOMB, NuclearBombEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.NUCLEAR_EXPLOSION, NuclearExplosionEntityRenderer::new);
        EntityRendererRegistry.register(EntityRegistry.GAS, GasRenderer::new);
    }
}