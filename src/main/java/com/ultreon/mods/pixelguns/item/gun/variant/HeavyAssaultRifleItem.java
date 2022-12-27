package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class HeavyAssaultRifleItem extends GunItem {
    private final SoundEvent shootSoundEasterEgg;

    public HeavyAssaultRifleItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.AUTOMATIC,
                8.0f,
                3,
                50,
                ModItems.STANDARD_RIFLE_BULLET,
                48,
                0.125f,
                2.5f,
                1,
                LoadingType.CLIP,
                ModSounds.RELOAD_HEAVY_AR_P1,
                ModSounds.RELOAD_HEAVY_AR_P2,
                ModSounds.RELOAD_HEAVY_AR_P3,
                ModSounds.ASSAULTRIFLE_HEAVY,
                1,
                false,
                6,
                22,
                40
        );
        this.shootSoundEasterEgg =  ModSounds.ASSAULTRIFLE_HEAVY_EG;
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
