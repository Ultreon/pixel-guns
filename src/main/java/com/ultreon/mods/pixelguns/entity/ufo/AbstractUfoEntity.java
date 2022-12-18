package com.ultreon.mods.pixelguns.entity.ufo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jab125.multipart.client.entity.MultiPartEntity;
import com.jab125.multipart.client.entity.MultiPartHolder;
import com.ultreon.mods.pixelguns.entity.ModEntities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.command.TeleportCommand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public abstract class AbstractUfoEntity extends Entity implements IAnimatable, MultiPartHolder<AbstractUfoEntity, UfoPart> {
    private final UfoPart[] parts;
    // Movement
    private float velocityDecay;
    private int interpolationSteps;

    // Position
    private double x;
    private double y;
    private double z;

    // Direction
    private double pitch;
    private double yaw;

    @Override
    public UfoPart[] getBodyParts() {
        System.out.println(Arrays.toString(parts));
        return parts;
    }

    public AbstractUfoEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
        this.velocityDecay = 0.9f;
        ArrayList<UfoPart> parts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                parts.add(new UfoPart(this, i + "," + j, 4.0f, 1.6f));
            }
        }
        // 15.0
        this.parts = parts.toArray(new UfoPart[0]);
    }

    public AbstractUfoEntity(World world, double x, double y, double z) {
        this(ModEntities.UFO, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }


    @Override
    public void refreshPositionAndAngles(double d, double e, double f, float g, float h) {
        super.refreshPositionAndAngles(d, e, f, g, h);
    }

    public abstract float getThirdPersonCameraDistance();
    public abstract float getScale();
    public abstract Entity abduct();

    @Override
    public void tick() {
        super.tick();
        
        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateInterpolatedPose();
            this.decayVelocity();
            if (this.world.isClient) {
                this.applyInput();
            }
            this.move(MovementType.SELF, this.getVelocity());
        } else {
            this.setVelocity(Vec3d.ZERO);

//            // TODO radius
//            List<PlayerEntity> collisions = this.world.getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().withMinY(this.getBoundingBox().maxY - 0.1), entity -> {return true;});
//            for (PlayerEntity player : collisions) {
//                player.setPosition(player.getPos().getX(), this.getBoundingBox().getMax(Axis.Y), player.getPos().getZ());
//                player.setVelocity(player.getVelocity().getX(), 0, player.getVelocity().getZ());
//                player.setOnGround(true);
//            }
        }

        if (!this.isRemoved()) {
            this.tickMovement();
        }
        
    }

    // Verified
    private void updateInterpolatedPose() {
//        if (this.isLogicalSideForUpdatingMovement()) {
//            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
//            this.interpolationSteps = 0;
//        }
//        if (this.interpolationSteps <= 0) return;
//
//        // Calculate interpolated pose
//        double interpolatedX = this.getX() + (this.x - this.getX()) / this.interpolationSteps;
//        double interpolatedY = this.getY() + (this.y - this.getY()) / this.interpolationSteps;
//        double interpolatedZ = this.getZ() + (this.z - this.getZ()) / this.interpolationSteps;
//        float interpolatedPitch = (float) (this.getPitch() + (this.pitch - this.getPitch()) / this.interpolationSteps);
//        float interpolatedYaw = (float) (this.getYaw() + MathHelper.wrapDegrees(this.yaw - this.getYaw()) / this.interpolationSteps);
//
//        // Apply interpolated pose
//        this.setPosition(interpolatedX, interpolatedY, interpolatedZ);
//        this.setRotation(interpolatedYaw, interpolatedPitch);
//
//        this.interpolationSteps--;
    }

    // Verified
    private void decayVelocity() {
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.multiply(this.velocityDecay));
    }

    // Verified
    private void applyInput() {
        if (!this.hasPassengers()) return;

        this.setYaw(-this.getPrimaryPassenger().getHeadYaw());

        float fockwardVelocity = 0.0f; // FOrward and baCKward = fockward
        if (UfoInput.pressingForward()) fockwardVelocity += 0.05;
        if (UfoInput.pressingBackward()) fockwardVelocity -= 0.05;

        float sidewaysVelocity = 0.0f;
        if (UfoInput.pressingRight()) sidewaysVelocity += 0.05;
        if (UfoInput.pressingLeft()) sidewaysVelocity -= 0.05;

        float verticalVelocity = 0.0f;
        if (UfoInput.pressingUp()) verticalVelocity += 0.05;
        if (UfoInput.pressingDown()) verticalVelocity -= 0.05;

        this.setVelocity(
            this.getVelocity().add(
                MathHelper.sin(this.getYaw() * MathHelper.RADIANS_PER_DEGREE) * fockwardVelocity,
                verticalVelocity,
                MathHelper.cos(-this.getYaw() * MathHelper.RADIANS_PER_DEGREE) * fockwardVelocity
            ).add(
                MathHelper.sin((this.getYaw() - 90) * MathHelper.RADIANS_PER_DEGREE) * sidewaysVelocity,
                0,
                MathHelper.cos((-this.getYaw() + 90) * MathHelper.RADIANS_PER_DEGREE) * sidewaysVelocity
            )
        );
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        return new Vec3d(passenger.getX(), this.getBoundingBox().maxY + 0.1, passenger.getZ());
    }

    @Override
    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.yaw = yaw;
        this.pitch = pitch;

        this.interpolationSteps = 10;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        }
        if (!this.world.isClient) {
            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
    }


    @Override
    public boolean isCollidable() {
        System.out.println("UFO MAIN: CALLED IT + " + StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());
        return false;
    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public double getMountedHeightOffset() {
        return 1.0;
    }

    @Override
    public Entity getPrimaryPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound var1) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound var1) {}

    /*
     * ANIMATION SIDE
     */

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);;

    private PlayState predicate(AnimationEvent<AbstractUfoEntity> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("ufo.spin"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<AbstractUfoEntity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose entityPose) {
        var d = super.getDimensions(entityPose);
        System.out.println(d.toString());
        return d;
    }

    @Override
    public void calculateDimensions() {
        super.calculateDimensions();
    }

    @Override
    public boolean damagePart(UfoPart entityPart, DamageSource damageSource, float f) {
        return false;
    }

    public void tickMovement() {
        Vec3d[] vec3ds = new Vec3d[this.parts.length];
        for (int s = 0; s < this.parts.length; ++s) {
            vec3ds[s] = new Vec3d(this.parts[s].getX(), this.parts[s].getY(), this.parts[s].getZ());
        }
        for (UfoPart part : this.parts) {
            var d = part.name.split(",");
            var e = new int[]{Integer.parseInt(d[0]), Integer.parseInt(d[1])};
            movePart(part, 4-(e[0]*4), 0, 4-(e[1]*4));
        }
        for (var ac = 0; ac < this.parts.length; ++ac) {
            this.parts[ac].prevX = vec3ds[ac].x;
            this.parts[ac].prevY = vec3ds[ac].y;
            this.parts[ac].prevZ = vec3ds[ac].z;
            this.parts[ac].lastRenderX = vec3ds[ac].x;
            this.parts[ac].lastRenderY = vec3ds[ac].y;
            this.parts[ac].lastRenderZ = vec3ds[ac].z;
        }
    }

    private void movePart(UfoPart ufoPart, double x, double y, double z) {
        System.out.println("POS BEFORE: " + ufoPart.getPos());
        ufoPart.setPosition(this.getX() + x, this.getY() + y, this.getZ() + z);
        System.out.println("POS NOW: " + ufoPart.getPos());
    }
}