package com.ultreon.mods.pixelguns.entity.projectile;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BulletEntity extends ThrowableItemProjectile {
    private Vec3 accel;
    private float damage;
    private int lifeTick;

    public BulletEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public BulletEntity(LivingEntity livingEntity, Level world, float dmg) {
        super(PixelGuns.BulletEntityType, livingEntity, world);
        this.damage = dmg;
        this.lifeTick = 0;
        this.setNoGravity(true);
    }

    protected ItemStack getItemRaw() {
        return new ItemStack(Items.BLACKSTONE);
    }

    protected Item getDefaultItem() {
        return Items.BLACKSTONE;
    }

    public void tick() {
        super.tick();
        ++this.lifeTick;
        if (this.accel != null) {
            this.setDeltaMovement(this.accel);
        }
        if (this.lifeTick >= 32) {
            this.discard();
        }
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level.isClientSide || entityHitResult.getEntity() instanceof WitherBoss && ((WitherBoss) entityHitResult.getEntity()).isPowered()) {
            return;
        }
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.MASTER, 1.0f, 1.0f);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), this.damage);
        entity.invulnerableTime = 0;
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level.isClientSide()) {
            this.discard();
        }
    }

    public void setAccel(Vec3 velocity) {
        this.accel = velocity;
    }
}

