package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.NbtNames;
import com.ultreon.mods.pixelguns.entity.damagesource.EnergyOrbDamageSource;
import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InfinityGunItem extends GunItem implements IAnimatable {

    //TODO use for something
    public static final List<InfinityGunItem> BLUEPRINT_ITEM_LIST = new ArrayList<>();


    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public InfinityGunItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                80f,
                250,
                30,
                5,
                ModItems.ENERGY_GUN_BATTERY,
                40,
                0,
                20,
                1,
                LoadingType.CLIP,
                SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
                SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN,
                SoundEvents.BLOCK_IRON_DOOR_OPEN,
                SoundEvents.BLOCK_BEACON_DEACTIVATE,
                1,
                false,
                5,
                -1,
                -1
        );
    }

    @Override
    public void registerControllers(AnimationData data) {}

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void shoot(World world, PlayerEntity user, ItemStack stack) {
        super.shoot(world, user, stack);
        NbtCompound infinityGun = stack.getOrCreateSubNbt(NbtNames.INFINITY_GUN);
        infinityGun.putBoolean(NbtNames.IS_SHOOTING, true);
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        NbtCompound infinityGun = stack.getOrCreateSubNbt(NbtNames.INFINITY_GUN);
        if (infinityGun.getBoolean(NbtNames.IS_SHOOTING)) {
            infinityGun.putBoolean(NbtNames.IS_SHOOTING, false);
            return true;
        }

        return super.isUsedOnRelease(stack);
    }

    public void hit(HitResult result, World world, PlayerEntity user, ItemStack stack) {
        Vec3d look = user.getEyePos().relativize(result.getPos()).normalize();
        Vec3d iter = look.multiply(8);
        Vec3d curPos = result.getPos();
        for (int i = 0; i < 3; i++) {
            world.createExplosion(null, new EnergyOrbDamageSource(), new ExplosionBehavior() {
                @Override
                public boolean canDestroyBlock(Explosion explosion, BlockView blockGetter, BlockPos blockPos, BlockState blockState, float f) {
                    return true;
                }

                @Override
                public Optional<Float> getBlastResistance(Explosion explosion, BlockView blockGetter, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
                    return Optional.of(0f);
                }
            }, curPos.x, curPos.y, curPos.z, 7f, true, Explosion.DestructionType.DESTROY);

            curPos = curPos.add(iter);
        }

        Vec3d userPos = user.getEyePos();
        Vec3d hitPosition = result.getPos().subtract(userPos);
        Vec3d normalizedHitPosition = hitPosition.normalize();
        if (world instanceof ServerWorld serverWorld) {
            for (int i = 1; i < MathHelper.floor(hitPosition.length()); ++i) {
                Vec3d lerpedLocation = userPos.add(normalizedHitPosition.multiply(i));
                serverWorld.spawnParticles(ParticleTypes.SONIC_BOOM, lerpedLocation.x, lerpedLocation.y, lerpedLocation.z, 1, 0, 0, 0, 0);
            }
        }
    }
}