package com.ultreon.mods.pixelguns.entity.projectile;

import com.ultreon.mods.pixelguns.entity.ModEntities;
import com.ultreon.mods.pixelguns.entity.damagesource.EnergyOrbDamageSource;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

public class EnergyOrb extends AbstractBulletEntity {
    private Vec3d accel;
    private float damage;
    private int lifeTick;

    public EnergyOrb(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EnergyOrb(LivingEntity livingEntity, World world, float dmg) {
        super(ModEntities.ENERGY_ORB_ENTITY_TYPE, livingEntity, world);
        this.damage = dmg;
        this.lifeTick = 0;
        this.setNoGravity(true);
    }

    protected ItemStack getItem() {
        return new ItemStack(Items.BLACKSTONE);
    }

    protected Item getDefaultItem() {
        return Items.BLACKSTONE;
    }

    public void tick() {
        super.tick();
        ++this.lifeTick;
        if (this.accel != null) {
            this.setVelocity(this.accel);
        }
        if (this.lifeTick >= 32) {
            this.discard();
        }
    }

    protected void onEntityHit(@NotNull EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.world.isClient || entityHitResult.getEntity() instanceof WitherEntity && ((WitherEntity) entityHitResult.getEntity()).shouldRenderOverlay()) {
            return;
        }
        this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.MASTER, 1.0f, 1.0f);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), this.damage);
        entity.timeUntilRegen = 0;
    }

    protected void onCollision(@NotNull HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient()) {
            this.discard();
        }

        this.world.createExplosion(this, new EnergyOrbDamageSource(), new ExplosionBehavior() {
            @Override
            public boolean canDestroyBlock(Explosion explosion, BlockView blockGetter, BlockPos blockPos, BlockState blockState, float f) {
                return true;
            }

            @Override
            public Optional<Float> getBlastResistance(Explosion explosion, BlockView blockGetter, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
                return Optional.of(0f);
            }
        }, getX(), getY(), getZ(), 6f, true, Explosion.DestructionType.DESTROY);
    }

    @Override
    public void setAccel(Vec3d velocity) {
        this.accel = velocity;
    }
}

