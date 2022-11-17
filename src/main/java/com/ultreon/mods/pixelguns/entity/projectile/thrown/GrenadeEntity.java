package com.ultreon.mods.pixelguns.entity.projectile.thrown;

import com.ultreon.mods.pixelguns.entity.ModEntities;
import com.ultreon.mods.pixelguns.item.ModItems;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion.BlockInteraction;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GrenadeEntity extends ThrowableItemProjectile implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, Level world) {
        super(entityType, world);
    }

    public GrenadeEntity(Level world, LivingEntity owner) {
        super(ModEntities.GRENADE, owner, world);
    }

    public GrenadeEntity(Level world, double x, double y, double z) {
        super(ModEntities.GRENADE, x, y, z, world);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), 0.0f);
    }

    private void explode() {
        if (this.level.isClientSide) return;

        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 1.0f, false, BlockInteraction.DESTROY);
        this.discard();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        explode();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE;
    }

    private PlayState predicate(AnimationEvent<GrenadeEntity> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("grenade.entity"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<GrenadeEntity>(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}