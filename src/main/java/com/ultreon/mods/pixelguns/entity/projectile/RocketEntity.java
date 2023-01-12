package com.ultreon.mods.pixelguns.entity.projectile;

import com.ultreon.mods.pixelguns.registry.EntityRegistry;
import com.ultreon.mods.pixelguns.registry.ModItems;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RocketEntity extends ThrownItemEntity implements IAnimatable {

    public RocketEntity(EntityType<? extends RocketEntity> entityType, World world) {
        super(entityType, world);
    }

    public RocketEntity(World world, LivingEntity owner) {
        super(EntityRegistry.ROCKET, owner, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 0.0f);
    }

    private void explode() {
        if (this.world.isClient) return;

        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 2.0f, false, Explosion.DestructionType.DESTROY);
        this.discard();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.explode();
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ROCKET;
    }

    @Override
    protected void initDataTracker() {

    }

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    @Override
    public void registerControllers(AnimationData animationData) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
