package com.ultreon.mods.pixelguns.entity.projectile.thrown;

import com.ultreon.mods.pixelguns.entity.ModEntities;
import com.ultreon.mods.pixelguns.item.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class GrenadeEntity extends ThrownItemEntity implements IAnimatable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, World world) {
        super(entityType, world);
    }

    public GrenadeEntity(World world, LivingEntity owner) {
        super(ModEntities.GRENADE, owner, world);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(DamageSource.thrownProjectile(this, this.getOwner()), 0.0f);
    }

    private void explode() {
        if (this.world.isClient) return;

        this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1.0f, false, DestructionType.DESTROY);
        this.discard();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        explode();
        world.playSound(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z, ModSounds.GRENADE_EXPLODE, SoundCategory.MASTER, 0.8f, 0.8f, false);
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
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}