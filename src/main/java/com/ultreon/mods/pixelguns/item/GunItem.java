package com.ultreon.mods.pixelguns.item;

import io.netty.buffer.Unpooled;
import com.ultreon.mods.pixelguns.AnimatedGuns;
import com.ultreon.mods.pixelguns.AnimatedGunsClient;
import com.ultreon.mods.pixelguns.entity.projectile.BulletEntity;
import com.ultreon.mods.pixelguns.util.InventoryUtil;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class GunItem extends Item implements FabricItem {
    private final float gunDamage;
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
    private final SoundEvent shootSound;
    private final int reloadCycles;
    private final boolean isScoped;
    private final int reloadStage1;
    private final int reloadStage2;
    private final int reloadStage3;

    public GunItem(Properties settings, float gunDamage, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float bulletSpread, float gunRecoil, int pelletCount, int loadingType, SoundEvent reload1, SoundEvent reload2, SoundEvent reload3, SoundEvent shootSound, int reloadCycles, boolean isScoped, int reloadStage1, int reloadStage2, int reloadStage3) {
        super(settings.durability(magSize * 10 + 1));
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
    }

    public static boolean isLoaded(ItemStack stack) {
        return GunItem.remainingAmmo(stack) > 0;
    }

    private static int remainingAmmo(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        return nbtCompound.getInt("Clip");
    }

    public static int reserveAmmoCount(Player player, Item item) {
        return InventoryUtil.itemCountInInventory(player, item);
    }

    public void setDefaultNBT(CompoundTag nbtCompound) {
        nbtCompound.putInt("reloadTick", 0);
        nbtCompound.putInt("currentCycle", 1);
        nbtCompound.putInt("Clip", 0);
        nbtCompound.putBoolean("isScoped", this.isScoped);
        nbtCompound.putBoolean("isReloading", false);
    }

    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (!(nbtCompound.contains("reloadTick") && nbtCompound.contains("Clip") && nbtCompound.contains("isScoped") && nbtCompound.contains("isReloading"))) {
            this.setDefaultNBT(nbtCompound);
        }
        if (world.isClientSide() && ((Player) entity).getItemInHand(InteractionHand.MAIN_HAND) == stack && AnimatedGunsClient.reloadToggle.isDown() && GunItem.remainingAmmo(stack) < this.magSize && GunItem.reserveAmmoCount((Player) entity, this.ammoType) > 0 && !nbtCompound.getBoolean("isReloading")) {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
            buf.writeBoolean(true);
            ClientPlayNetworking.send(new ResourceLocation("anim_guns", "reload"), buf);
        }
        if (nbtCompound.getBoolean("isReloading") && (((Player) entity).getItemInHand(InteractionHand.MAIN_HAND) != stack || GunItem.reserveAmmoCount((Player) entity, this.ammoType) <= 0 && this.reloadCycles <= 1 || nbtCompound.getInt("reloadTick") >= this.reloadCooldown || GunItem.remainingAmmo(stack) >= this.magSize && this.reloadCycles <= 1)) {
            nbtCompound.putBoolean("isReloading", false);
        }
        if (nbtCompound.getBoolean("isReloading")) {
            this.doReloadTick(world, nbtCompound, (Player) entity, stack);
        } else {
            if (nbtCompound.getInt("reloadTick") > this.reloadStage3 && nbtCompound.getInt("reloadTick") <= this.reloadCooldown) {
                this.finishReload((Player) entity, stack);
            }
            nbtCompound.putInt("reloadTick", 0);
        }
    }

    private void doReloadTick(Level world, CompoundTag nbtCompound, Player player, ItemStack stack) {
        int rTick = nbtCompound.getInt("reloadTick");
        nbtCompound.putInt("reloadTick", nbtCompound.getInt("reloadTick") + 1);
        if (!world.isClientSide()) {
            if (rTick == this.reloadStage1) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload1, SoundSource.MASTER, 1.0f, 1.0f);
            } else if (rTick == this.reloadStage2) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload2, SoundSource.MASTER, 1.0f, 1.0f);
            } else if (rTick == this.reloadStage3 + 1) {
                world.playSound(null, player.getX(), player.getY(), player.getZ(), this.reload3, SoundSource.MASTER, 1.0f, 1.0f);
            }
        }
        switch (this.loadingType) {
            case 1 -> {
                if (rTick < this.reloadCooldown || GunItem.reserveAmmoCount(player, this.ammoType) <= 0) break;
                nbtCompound.putInt("currentCycle", 1);
                this.finishReload(player, stack);
                nbtCompound.putInt("reloadTick", 0);
            }
            case 2 -> {
                if (rTick < this.reloadStage3 || nbtCompound.getInt("currentCycle") >= this.reloadCycles || GunItem.reserveAmmoCount(player, this.ammoType) <= 0)
                    break;
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + 1);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, 1);
                if (GunItem.remainingAmmo(stack) < this.magSize && GunItem.reserveAmmoCount(player, this.ammoType) > 0) {
                    nbtCompound.putInt("reloadTick", this.reloadStage2);
                }
                nbtCompound.putInt("currentCycle", nbtCompound.getInt("Clip"));
                stack.setDamageValue(this.getMaxDamage() - (nbtCompound.getInt("Clip") * 10 + 1));
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND && !user.isSprinting() && GunItem.isLoaded(itemStack)) {
            this.shoot(world, user, itemStack);
            if (this.reloadCycles > 1) {
                itemStack.getOrCreateTag().putInt("currentCycle", itemStack.getOrCreateTag().getInt("Clip"));
            }
            itemStack.getOrCreateTag().putInt("reloadTick", 0);
            itemStack.getOrCreateTag().putBoolean("isReloading", false);
        }
        return InteractionResultHolder.fail(itemStack);
    }

    public void shoot(Level world, Player user, ItemStack itemStack) {
        float kick = user.getXRot() - this.getRecoil(user);
        user.getCooldowns().addCooldown(this, this.rateOfFire);
        if (!world.isClientSide()) {
            for (int i = 0; i < this.pelletCount; ++i) {
                BulletEntity bullet = new BulletEntity(user, world, this.gunDamage);
                bullet.setPosRaw(user.getX(), user.getEyeY(), user.getZ());
                bullet.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0f, 4.0f, this.bulletSpread);
                bullet.setAccel(bullet.getDeltaMovement());
                world.addFreshEntity(bullet);
            }
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(kick);
            ServerPlayNetworking.send((ServerPlayer) user, AnimatedGuns.RECOIL_PACKET_ID, buf);
        }
        if (!user.getAbilities().instabuild) {
            this.useAmmo(itemStack);
            itemStack.hurtAndBreak(10, (LivingEntity) user, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        world.playSound(null, user.getX(), user.getY(), user.getZ(), this.shootSound, SoundSource.MASTER, 1.0f, 1.0f);
    }

    private float getRecoil(Player user) {
        return user.isShiftKeyDown() ? this.gunRecoil / 2.0f : this.gunRecoil;
    }

    private void useAmmo(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") - 1);
    }

    public void finishReload(Player player, ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        if (nbtCompound.getInt("Clip") <= 0) {
            if (GunItem.reserveAmmoCount(player, this.ammoType) > this.magSize) {
                nbtCompound.putInt("Clip", this.magSize);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, this.magSize);
            } else {
                nbtCompound.putInt("Clip", GunItem.reserveAmmoCount(player, this.ammoType));
                InventoryUtil.removeItemFromInventory(player, this.ammoType, GunItem.reserveAmmoCount(player, this.ammoType));
            }
        } else {
            int ammoToLoad = this.magSize - nbtCompound.getInt("Clip");
            if (GunItem.reserveAmmoCount(player, this.ammoType) >= ammoToLoad) {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + ammoToLoad);
                InventoryUtil.removeItemFromInventory(player, this.ammoType, ammoToLoad);
            } else {
                nbtCompound.putInt("Clip", nbtCompound.getInt("Clip") + GunItem.reserveAmmoCount(player, this.ammoType));
                InventoryUtil.removeItemFromInventory(player, this.ammoType, GunItem.reserveAmmoCount(player, this.ammoType));
            }
        }
        stack.setDamageValue(this.getMaxDamage() - (nbtCompound.getInt("Clip") * 10 + 1));
    }

    public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}

