package com.ultreon.mods.pixelguns.item.gun;

import com.ultreon.mods.pixelguns.item.ModItems;
import io.netty.buffer.Unpooled;
import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.PixelGunsClient;
import com.ultreon.mods.pixelguns.util.InventoryUtil;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import java.util.Random;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractGunItem extends Item {

    private final MinecraftClient client;

    protected final float gunDamage;
    private final int rateOfFire;
    private final int magSize;
    private final Item ammoType;
    private final int reloadCooldown;
    private final float bulletSpread;
    private final float gunRecoil;
    private final int pelletCount;
    private final int loadingType;
    private final SoundEvent reload1;
    private final SoundEvent reload2;
    private final SoundEvent reload3;
    protected final SoundEvent shootSound;
    private final int reloadCycles;
    private final boolean isScoped;
    private final int reloadStage1;
    private final int reloadStage2;
    private final int reloadStage3;

    public AbstractGunItem(Settings settings, float gunDamage, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float bulletSpread, float gunRecoil, int pelletCount, int loadingType, SoundEvent reload1, SoundEvent reload2, SoundEvent reload3, SoundEvent shootSound, int reloadCycles, boolean isScoped, int reloadStage1, int reloadStage2, int reloadStage3) {
        super(settings.maxDamage(magSize * 10 + 1));
        this.gunDamage = gunDamage;
        this.rateOfFire = rateOfFire;
        this.magSize = magSize;
        this.ammoType = ammoType;
        this.reloadCooldown = reloadCooldown;
        this.bulletSpread = bulletSpread;
        this.gunRecoil = gunRecoil;
        this.pelletCount = pelletCount;
        this.loadingType = loadingType;
        this.reload1 = reload1;
        this.reload2 = reload2;
        this.reload3 = reload3;
        this.shootSound = shootSound;
        this.reloadCycles = reloadCycles;
        this.isScoped = isScoped;
        this.reloadStage1 = reloadStage1;
        this.reloadStage2 = reloadStage2;
        this.reloadStage3 = reloadStage3;

        client = MinecraftClient.getInstance();
    }

    public static boolean isLoaded(ItemStack stack) {
        return AbstractGunItem.remainingAmmo(stack) > 0;
    }

    private static int remainingAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        return nbtCompound.getInt("Clip");
    }

    public static int reserveAmmoCount(PlayerEntity player, Item item) {
        return InventoryUtil.itemCountInInventory(player, item);
    }

    public void setDefaultNBT(NbtCompound nbtCompound) {
        nbtCompound.putInt("reloadTick", 0);
        nbtCompound.putInt("currentCycle", 1);
        nbtCompound.putInt("Clip", 0);
        nbtCompound.putBoolean("isScoped", this.isScoped);
        nbtCompound.putBoolean("isReloading", false);
    }

    public void inventoryTick(ItemStack stack, @NotNull World world, @NotNull Entity entity, int slot, boolean selected) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (!(nbtCompound.contains("reloadTick") && nbtCompound.contains("Clip") && nbtCompound.contains("isScoped") && nbtCompound.contains("isReloading"))) {
            this.setDefaultNBT(nbtCompound);
        }
        if (world.isClient() && ((PlayerEntity) entity).getStackInHand(Hand.MAIN_HAND) == stack && PixelGunsClient.reloadToggle.isPressed() && AbstractGunItem.remainingAmmo(stack) < this.magSize && AbstractGunItem.reserveAmmoCount((PlayerEntity) entity, this.ammoType) > 0 && !nbtCompound.getBoolean("isReloading")) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeBoolean(true);
            ClientPlayNetworking.send(PixelGuns.res("reload"), buf);
        }
        if (nbtCompound.getBoolean("isReloading") && (((PlayerEntity) entity).getStackInHand(Hand.MAIN_HAND) != stack || AbstractGunItem.reserveAmmoCount((PlayerEntity) entity, this.ammoType) <= 0 && this.reloadCycles <= 1 || nbtCompound.getInt("reloadTick") >= this.reloadCooldown || AbstractGunItem.remainingAmmo(stack) >= this.magSize && this.reloadCycles <= 1)) {
            nbtCompound.putBoolean("isReloading", false);
        }
        if (nbtCompound.getBoolean("isReloading")) {
            this.doReloadTick(world, nbtCompound, (PlayerEntity) entity, stack);
        } else {
            if (nbtCompound.getInt("reloadTick") > this.reloadStage3 && nbtCompound.getInt("reloadTick") <= this.reloadCooldown) {
                this.finishReload((PlayerEntity) entity, stack);
            }
            nbtCompound.putInt("reloadTick", 0);
        }
    }

    private void doReloadTick(World world, NbtCompound nbtCompound, PlayerEntity player, ItemStack stack) {
        int rTick = nbtCompound.getInt("reloadTick");
        nbtCompound.putInt("reloadTick", nbtCompound.getInt("reloadTick") + 1);
        if (!world.isClient()) {
            if (rTick == this.reloadStage1) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload1, SoundCategory.MASTER, 1.0f, 1.0f);
            } else if (rTick == this.reloadStage2) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload2, SoundCategory.MASTER, 1.0f, 1.0f);
            } else if (rTick == this.reloadStage3 + 1) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload3, SoundCategory.MASTER, 1.0f, 1.0f);
            }
        }
        switch (this.loadingType) {
            case 1 -> {
                if (rTick < this.reloadCooldown || AbstractGunItem.reserveAmmoCount(player, this.ammoType) <= 0) break;
                nbtCompound.putInt("currentCycle", 1);
                this.finishReload(player, stack);
                nbtCompound.putInt("reloadTick", 0);
            }
            case 2 -> {
                if (rTick < this.reloadStage3 || nbtCompound.getInt("currentCycle") >= this.reloadCycles || AbstractGunItem.reserveAmmoCount(player, this.ammoType) <= 0)
                    break;
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + 1);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, 1);
                if (AbstractGunItem.remainingAmmo(stack) < this.magSize && AbstractGunItem.reserveAmmoCount(player, this.ammoType) > 0) {
                    nbtCompound.putInt("reloadTick", this.reloadStage2);
                }
                nbtCompound.putInt("currentCycle", nbtCompound.getInt("Clip"));
                stack.setDamage(this.getMaxDamage() - (nbtCompound.getInt("Clip") * 10 + 1));
            }
        }
    }

    public TypedActionResult<ItemStack> use(@NotNull World world, PlayerEntity user, @NotNull Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!client.options.attackKey.isPressed()) {
            return TypedActionResult.fail(itemStack);
        }
        if (hand == Hand.MAIN_HAND && !user.isSprinting() && AbstractGunItem.isLoaded(itemStack)) {
            this.shoot(world, user, itemStack);
            if (this.reloadCycles > 1) {
                itemStack.getOrCreateNbt().putInt("currentCycle", itemStack.getOrCreateNbt().getInt("Clip"));
            }
            itemStack.getOrCreateNbt().putInt("reloadTick", 0);
            itemStack.getOrCreateNbt().putBoolean("isReloading", false);
        }
        return TypedActionResult.fail(itemStack);
    }

    public HitResult getHitResult(World world, PlayerEntity player, Vec3d origin, Vec3d direction, double maxDistance) {
        Vec3d destination = origin.add(direction.multiply(maxDistance));
        HitResult hitResult = world.raycast(new RaycastContext(origin, destination, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        if (hitResult.getType() != HitResult.Type.MISS) {
            destination = hitResult.getPos();
        }
        EntityHitResult entityHitResult = ProjectileUtil.getEntityCollision(world, player, origin, destination, player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(1.0), (entity) -> true);
        if (entityHitResult != null) {
            hitResult = entityHitResult;
        }
        return hitResult;
    }

    public void shoot(World world, PlayerEntity user, ItemStack stack) {
        float kick = user.getPitch() - this.getRecoil();
        user.getItemCooldownManager().set(this, this.rateOfFire);
        if (!world.isClient()) {
            for (int i = 0; i < this.pelletCount; ++i) {
                int maxDistance;
                if (this == ModItems.CLASSIC_SNIPER_RIFLE) maxDistance = 500;
                else maxDistance = 250;

                Random r = new Random();
                Vec3d bulletVector = user.getRotationVector().add(new Vec3d(r.nextGaussian(), r.nextGaussian(), r.nextGaussian()).multiply(this.bulletSpread / 10));

                HitResult result = getHitResult(world, user, user.getEyePos(), bulletVector, maxDistance);
                if (result instanceof EntityHitResult entityHitResult) {
                    float damage = this.gunDamage;
                    entityHitResult.getEntity().damage(DamageSource.player(user), damage);
                
                    PixelGuns.LOGGER.info(user.distanceTo(entityHitResult.getEntity()) + " " + damage + " " + entityHitResult.getEntity().getType().getUntranslatedName());
                } else {
                    BlockHitResult blockHitResult = (BlockHitResult) result;
                    ((ServerWorld) world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, world.getBlockState(blockHitResult.getBlockPos())), blockHitResult.getPos().x, blockHitResult.getPos().y, blockHitResult.getPos().z, 1, 0, 0, 0, 1);
                }
            }
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(kick);
            ServerPlayNetworking.send((ServerPlayerEntity) user, PixelGuns.RECOIL_PACKET_ID, buf);
//            user.setXRot(kick);
        }
        if (!user.getAbilities().creativeMode) {
            this.useAmmo(stack);
            stack.damage(10, (LivingEntity) user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        playShootSound(world, user, stack);
    }

    public void playShootSound(World world, PlayerEntity user, ItemStack stack) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), this.shootSound, SoundCategory.MASTER, 1.0f, 1.0f);
    }

    private float getRecoil() {
        return client.options.useKey.isPressed() ? this.gunRecoil / 2.0f : this.gunRecoil;
    }

    private void useAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") - 1);
    }

    public void finishReload(PlayerEntity player, ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (nbtCompound.getInt("Clip") <= 0) {
            if (AbstractGunItem.reserveAmmoCount(player, this.ammoType) > this.magSize) {
                nbtCompound.putInt("Clip", this.magSize);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, this.magSize);
            } else {
                nbtCompound.putInt("Clip", AbstractGunItem.reserveAmmoCount(player, this.ammoType));
                InventoryUtil.removeItemFromInventory(player, this.ammoType, AbstractGunItem.reserveAmmoCount(player, this.ammoType));
            }
        } else {
            int ammoToLoad = this.magSize - nbtCompound.getInt("Clip");
            if (AbstractGunItem.reserveAmmoCount(player, this.ammoType) >= ammoToLoad) {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + ammoToLoad);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, ammoToLoad);
            } else {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + AbstractGunItem.reserveAmmoCount(player, this.ammoType));
                InventoryUtil.removeItemFromInventory(player, this.ammoType, AbstractGunItem.reserveAmmoCount(player, this.ammoType));
            }
        }
        stack.setDamage(this.getMaxDamage() - (nbtCompound.getInt("Clip") * 10 + 1));
    }

    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}

