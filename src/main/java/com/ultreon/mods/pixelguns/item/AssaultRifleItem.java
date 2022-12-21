package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.item.gun.AbstractGunItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class AssaultRifleItem extends AbstractGunItem {
    private final SoundEvent shootSoundEasterEgg;

    public AssaultRifleItem(Settings settings, AmmoLoadingType ammoLoadingType, float gunDamage, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float bulletSpread, float gunRecoil, int pelletCount, int loadingType, SoundEvent reload1, SoundEvent reload2, SoundEvent reload3, SoundEvent shootSound, int reloadCycles, boolean isScoped, int reloadStage1, int reloadStage2, int reloadStage3, SoundEvent shootSoundEasterEgg) {
        super(settings, ammoLoadingType, gunDamage, rateOfFire, magSize, ammoType, reloadCooldown, bulletSpread, gunRecoil, pelletCount, loadingType, reload1, reload2, reload3, shootSound, reloadCycles, isScoped, reloadStage1, reloadStage2, reloadStage3);
        this.shootSoundEasterEgg = shootSoundEasterEgg;
    }


    @Override
    public void playShootSound(World world, PlayerEntity user, ItemStack stack) {
        NbtCompound nbtElement = stack.getOrCreateSubNbt(PixelGuns.NBT_NAME);
        boolean easterEgg;
        if (!nbtElement.contains("easterEgg", NbtElement.BYTE_TYPE))
            nbtElement.putBoolean("easterEgg", (easterEgg = user.getRandom().nextInt(100) == 0));
        else
            easterEgg = nbtElement.getBoolean("easterEgg");

        world.playSound(null, user.getX(), user.getY(), user.getZ(), easterEgg ? this.shootSoundEasterEgg : this.shootSound, SoundCategory.MASTER, 1.0f, 1.0f);
    }

    @Override
    public void onCraft(@NotNull ItemStack itemStack, @NotNull World level, @NotNull PlayerEntity player) {
        super.onCraft(itemStack, level, player);
    }
}
