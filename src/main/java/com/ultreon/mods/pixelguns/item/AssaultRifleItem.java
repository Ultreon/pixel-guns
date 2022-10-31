package com.ultreon.mods.pixelguns.item;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AssaultRifleItem extends GunItem {
    private final SoundEvent shootSoundEasterEgg;

    public AssaultRifleItem(Properties settings, float gunDamage, int rateOfFire, int magSize, Item ammoType, int reloadCooldown, float bulletSpread, float gunRecoil, int pelletCount, int loadingType, SoundEvent reload1, SoundEvent reload2, SoundEvent reload3, SoundEvent shootSound, SoundEvent shootSoundEasterEgg, int reloadCycles, boolean isScoped, int reloadStage1, int reloadStage2, int reloadStage3) {
        super(settings, gunDamage, rateOfFire, magSize, ammoType, reloadCooldown, bulletSpread, gunRecoil, pelletCount, loadingType, reload1, reload2, reload3, shootSound, reloadCycles, isScoped, reloadStage1, reloadStage2, reloadStage3);
        this.shootSoundEasterEgg = shootSoundEasterEgg;
    }

    @Override
    public void playShootSound(Level world, Player user, ItemStack stack) {
        CompoundTag nbtElement = stack.getOrCreateTagElement(PixelGuns.NBT_NAME);
        boolean easterEgg;
        if (!nbtElement.contains("easterEgg", Tag.TAG_BYTE))
            nbtElement.putBoolean("easterEgg", (easterEgg = user.getRandom().nextInt(100) == 0));
        else
            easterEgg = nbtElement.getBoolean("easterEgg");

        world.playSound(null, user.getX(), user.getY(), user.getZ(), easterEgg ? this.shootSoundEasterEgg : this.shootSound, SoundSource.MASTER, 1.0f, 1.0f);
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Level level, Player player) {
        super.onCraftedBy(itemStack, level, player);
    }
}
