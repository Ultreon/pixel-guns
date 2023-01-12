package com.ultreon.mods.pixelguns.entity;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.fabric.FabricAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.EllipsoidRegion;
import com.ultreon.mods.pixelguns.PixelGuns;

import com.ultreon.mods.pixelguns.registry.EntityRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class NuclearBombEntity extends Entity implements IAnimatable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public NuclearBombEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.setVelocity(0, -1.5, 0);
    }

    public float getScale() {
        return 4.0f;
    }

    @Override
    public void tick() {
        super.tick();
        super.move(MovementType.SELF, new Vec3d(0, -1.5, 0));
        if (this.isAlive() && this.isOnGround()) {
            this.explode();
            this.discard();
        }
    }

    private void spawnExplosion() {
        NuclearExplosionEntity explosionEntity = EntityRegistry.NUCLEAR_EXPLOSION.create(this.world);
        if (explosionEntity == null) return;
        explosionEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0f);
        this.world.spawnEntity(explosionEntity);
    }
    private void affectWorld() {
        com.sk89q.worldedit.world.World world = FabricAdapter.adapt(this.getWorld());
        EditSession session = WorldEdit.getInstance().newEditSession(world);
        BlockVector3 origin = FabricAdapter.adapt(this.getBlockPos());
        try {
            session.setBlocks(new EllipsoidRegion(world, origin, Vector3.at(50, 30, 50)), FabricAdapter.adapt(Blocks.AIR).getDefaultState().toBaseBlock());
            session.close();
        } catch (MaxChangedBlocksException e) {
            PixelGuns.LOGGER.error("EXPLOSION ERROR");
            e.printStackTrace();
        }
    }

    private void explode() {
        if (!this.world.isClient) {
            spawnExplosion();
            affectWorld();
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        //event.getController().setAnimation(new AnimationBuilder().addAnimation("nuclear_bomb.deploy").addAnimation("nuclear_bomb.spin"));
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

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound var1) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound var1) {}

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }
}