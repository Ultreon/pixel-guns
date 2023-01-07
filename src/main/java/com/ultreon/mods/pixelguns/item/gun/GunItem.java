package com.ultreon.mods.pixelguns.item.gun;

import io.netty.buffer.Unpooled;
import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.PixelGunsClient;
import com.ultreon.mods.pixelguns.util.InventoryUtil;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

public abstract class GunItem extends Item {

    private final MinecraftClient client;

    private final AmmoLoadingType ammoLoadingType;
    protected final float damage;
    protected final int range;
    protected final int fireCooldown;
    private final int magazineSize;
    public final Item ammunition;
    private final int reloadCooldown;
    protected final float bulletSpread;
    protected final float recoil;
    protected final int pelletCount;
    private final LoadingType loadingType;
    private final SoundEvent[] reloadSounds;
    private final int[] reloadSoundStages;
    protected final SoundEvent fireAudio;
    private final int reloadCycles;
    private final boolean isScoped;

    public GunItem(Settings settings, AmmoLoadingType ammoLoadingType, float damage, int range, int fireCooldown, int magazineSize, Item ammunition, int reloadCooldown, float bulletSpread, float recoil, int pelletCount, LoadingType loadingType, SoundEvent[] reloadSounds, SoundEvent fireAudio, int reloadCycles, boolean isScoped, int[] reloadStages) {
        super(settings);
        this.ammoLoadingType = ammoLoadingType;
        this.damage = damage;
        this.range = range;
        this.fireCooldown = fireCooldown;
        this.magazineSize = magazineSize;
        this.ammunition = ammunition;
        this.reloadCooldown = reloadCooldown;
        this.bulletSpread = bulletSpread;
        this.recoil = recoil;
        this.pelletCount = pelletCount;
        this.loadingType = loadingType;
        this.reloadSounds = reloadSounds;
        this.fireAudio = fireAudio;
        this.reloadCycles = reloadCycles;
        this.isScoped = isScoped;
        this.reloadSoundStages = reloadStages;

        client = MinecraftClient.getInstance();
    }

    public static boolean isLoaded(ItemStack stack) {
        return GunItem.remainingAmmo(stack) > 0;
    }

    public static int remainingAmmo(ItemStack stack) {
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

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.literal(String.format("%s/%s", GunItem.remainingAmmo(stack), this.magazineSize)).formatted(Formatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, @NotNull World world, @NotNull Entity entity, int slot, boolean selected) {

        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (!(nbtCompound.contains("reloadTick") && nbtCompound.contains("Clip") && nbtCompound.contains("isScoped") && nbtCompound.contains("isReloading"))) {
            this.setDefaultNBT(nbtCompound);
        }
        if (world.isClient() && ((PlayerEntity) entity).getStackInHand(Hand.MAIN_HAND) == stack && PixelGunsClient.reloadToggle.isPressed() && GunItem.remainingAmmo(stack) < this.magazineSize && GunItem.reserveAmmoCount((PlayerEntity) entity, this.ammunition) > 0 && !nbtCompound.getBoolean("isReloading")) {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
            buf.writeBoolean(true);
            ClientPlayNetworking.send(PixelGuns.res("reload"), buf);
        }
        if (nbtCompound.getBoolean("isReloading") && (((PlayerEntity) entity).getStackInHand(Hand.MAIN_HAND) != stack || GunItem.reserveAmmoCount((PlayerEntity) entity, this.ammunition) <= 0 && this.reloadCycles <= 1 || nbtCompound.getInt("reloadTick") >= this.reloadCooldown || GunItem.remainingAmmo(stack) >= this.magazineSize && this.reloadCycles <= 1)) {
            nbtCompound.putBoolean("isReloading", false);
        }
        if (nbtCompound.getBoolean("isReloading")) {
            this.doReloadTick(world, nbtCompound, (PlayerEntity) entity, stack);
        } else {
            if (nbtCompound.getInt("reloadTick") > this.reloadSoundStages[2] && nbtCompound.getInt("reloadTick") <= this.reloadCooldown) {
                this.finishReload((PlayerEntity) entity, stack);
            }
            nbtCompound.putInt("reloadTick", 0);
        }
    }

    protected void doReloadTick(World world, NbtCompound nbtCompound, PlayerEntity player, ItemStack stack) {
        int reloadTick = nbtCompound.getInt("reloadTick");
        nbtCompound.putInt("reloadTick", nbtCompound.getInt("reloadTick") + 1);
        if (!world.isClient()) {
            if (reloadTick == this.reloadSoundStages[0]) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reloadSounds[0], SoundCategory.MASTER, 1.0f, 1.0f);
            } else if (reloadTick == this.reloadSoundStages[1]) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reloadSounds[1], SoundCategory.MASTER, 1.0f, 1.0f);
            } else if (reloadTick == this.reloadSoundStages[2]) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reloadSounds[2], SoundCategory.MASTER, 1.0f, 1.0f);
            }
        }
        switch (this.loadingType) {
            case CLIP -> {
                if (reloadTick < this.reloadCooldown || GunItem.reserveAmmoCount(player, this.ammunition) <= 0) break;
                nbtCompound.putInt("currentCycle", 1);
                this.finishReload(player, stack);
                nbtCompound.putInt("reloadTick", 0);
            }
            case INDIVIDUAL -> {
                if (reloadTick < this.reloadSoundStages[2] || nbtCompound.getInt("currentCycle") >= this.reloadCycles || GunItem.reserveAmmoCount(player, this.ammunition) <= 0)
                    break;
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + 1);
                InventoryUtil.removeItemFromInventory(player, this.ammunition, 1);
                if (GunItem.remainingAmmo(stack) < this.magazineSize && GunItem.reserveAmmoCount(player, this.ammunition) > 0) {
                    nbtCompound.putInt("reloadTick", this.reloadSoundStages[1]);
                }
                nbtCompound.putInt("currentCycle", nbtCompound.getInt("Clip"));
            }
        }
    }

    public TypedActionResult<ItemStack> use(@NotNull World world, PlayerEntity user, @NotNull Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!client.options.attackKey.isPressed()) {
            return TypedActionResult.fail(itemStack);
        }
        if (hand == Hand.MAIN_HAND && !user.isSprinting() && GunItem.isLoaded(itemStack)) {
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
        // The following line of code needs optimizing
        HitResult hitResult = world.raycast(new RaycastContext(origin, destination, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player));
        if (hitResult.getType() != HitResult.Type.MISS) {
            destination = hitResult.getPos();
        }
        // Maybe this line too, run a test to find out
        EntityHitResult entityHitResult = ProjectileUtil.getEntityCollision(world, player, origin, destination, player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(1.0), (entity) -> true);
        if (entityHitResult != null) {
            hitResult = entityHitResult;
        }


        return hitResult;
    }

    protected void handleHit(HitResult result, ServerWorld world, PlayerEntity damageSource) {
        if (result instanceof EntityHitResult entityHitResult) {
            entityHitResult.getEntity().damage(DamageSource.player(damageSource), this.damage);

//            PixelGuns.LOGGER.info(damageSource.distanceTo(entityHitResult.getEntity()) + " " + damage + " " + entityHitResult.getEntity().getType().getUntranslatedName());
        } else {
            BlockHitResult blockHitResult = (BlockHitResult) result;
            ParticleEffect particleEffect = new BlockStateParticleEffect(ParticleTypes.BLOCK, world.getBlockState(blockHitResult.getBlockPos()));
            world.spawnParticles(particleEffect, blockHitResult.getPos().x, blockHitResult.getPos().y, blockHitResult.getPos().z, 1, 0, 0, 0, 1);
        }
    }



    public void shoot(World world, PlayerEntity user, ItemStack stack) {
        float kick = user.getPitch() - this.getRecoil();
        user.getItemCooldownManager().set(this, this.fireCooldown);
        if (!world.isClient()) {
            for (int i = 0; i < this.pelletCount; ++i) {

                Random r = new Random();
                Vec3d bulletVector = user.getRotationVector().add(new Vec3d(r.nextGaussian(), r.nextGaussian(), r.nextGaussian()).multiply(this.bulletSpread / 10));

                int range = this.range;

                // The following code handles the hit detection within 2ms, otherwise it silently fails
                final Runnable stuffToDo = new Thread(() -> {
                    handleHit(getHitResult(world, user, user.getEyePos(), bulletVector, range), (ServerWorld) world, user);
                });
                final ExecutorService executor = Executors.newSingleThreadExecutor();
                final Future future = executor.submit(stuffToDo);
                executor.shutdown(); // This does not cancel the already-scheduled task.

                try {
                    future.get(2, TimeUnit.MILLISECONDS);
                }
                catch (Exception ie) {
                    /* Handle the interruption. Or ignore it. */
                }
                if (!executor.isTerminated())
                    executor.shutdownNow(); // If you want to stop the code that hasn't finished.
                // end of "the following code"

            }
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(kick);
            ServerPlayNetworking.send((ServerPlayerEntity) user, PixelGuns.RECOIL_PACKET_ID, buf);
//            user.setXRot(kick);
        }
        if (!user.getAbilities().creativeMode) {
            this.useAmmo(stack);
//            stack.damage(10, (LivingEntity) user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        playFireAudio(world, user);
    }

    public void playFireAudio(World world, PlayerEntity user) {
        world.playSound(null, user.getX(), user.getY(), user.getZ(), this.fireAudio, SoundCategory.MASTER, 1.0f, 1.0f);
    }

    protected float getRecoil() {
        return client.options.useKey.isPressed() ? this.recoil / 2.0f : this.recoil;
    }

    protected void useAmmo(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") - 1);
    }

    public void finishReload(PlayerEntity player, ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        if (nbtCompound.getInt("Clip") <= 0) {
            if (GunItem.reserveAmmoCount(player, this.ammunition) > this.magazineSize) {
                nbtCompound.putInt("Clip", this.magazineSize);
                InventoryUtil.removeItemFromInventory(player, this.ammunition, this.magazineSize);
            } else {
                nbtCompound.putInt("Clip", GunItem.reserveAmmoCount(player, this.ammunition));
                InventoryUtil.removeItemFromInventory(player, this.ammunition, GunItem.reserveAmmoCount(player, this.ammunition));
            }
        } else {
            int ammoToLoad = this.magazineSize - nbtCompound.getInt("Clip");
            if (GunItem.reserveAmmoCount(player, this.ammunition) >= ammoToLoad) {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + ammoToLoad);
                InventoryUtil.removeItemFromInventory(player, this.ammunition, ammoToLoad);
            } else {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + GunItem.reserveAmmoCount(player, this.ammunition));
                InventoryUtil.removeItemFromInventory(player, this.ammunition, GunItem.reserveAmmoCount(player, this.ammunition));
            }
        }
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    public enum AmmoLoadingType {
        SEMI_AUTOMATIC,
        BURST,
        AUTOMATIC
    }

    public enum LoadingType {
        INDIVIDUAL,
        CLIP
    }
}

