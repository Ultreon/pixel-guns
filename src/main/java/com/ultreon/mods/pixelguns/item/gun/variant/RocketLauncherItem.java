package com.ultreon.mods.pixelguns.item.gun.variant;

import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.RocketEntity;
import com.ultreon.mods.pixelguns.item.gun.GunItem;
import com.ultreon.mods.pixelguns.registry.ModItems;
import com.ultreon.mods.pixelguns.sound.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class RocketLauncherItem extends GunItem implements IAnimatable, ISyncable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public RocketLauncherItem(Settings settings) {
        super(
                settings,
                GunItem.AmmoLoadingType.SEMI_AUTOMATIC,
                25.0f,
                250,
                30,
                1,
                ModItems.ROCKET,
                30,
                0,
                0,
                1,
                LoadingType.INDIVIDUAL,
                ModSounds.RELOAD_GENERIC_SNIPER_P1,
                ModSounds.RELOAD_GENERIC_SNIPER_P2,
                ModSounds.RELOAD_GENERIC_SNIPER_P3,
                ModSounds.SNIPER_CLASSIC,
                1,
                false,
                1,
                8,
                17
        );
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void shoot(World world, PlayerEntity player, ItemStack stack) {
        if (world instanceof ServerWorld serverWorld) {
            sendAnimationPacket(ANIM_FIRE, serverWorld, player, stack);
        }

        float kick = player.getPitch() - this.getRecoil();
        player.getItemCooldownManager().set(this, this.fireCooldown);
        if (!world.isClient()) {
            RocketEntity rocket = new RocketEntity(world);
            rocket.setPosition(player.getEyePos().subtract(0, 0.1, 0));

            rocket.setVelocity(player.getRotationVector().normalize().multiply(1.5));
            world.spawnEntity(rocket);


            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeFloat(kick);
            ServerPlayNetworking.send((ServerPlayerEntity) player, PixelGuns.RECOIL_PACKET_ID, buf);
        }
        if (!player.getAbilities().creativeMode) {
            this.useAmmo(stack);
        }
        playFireAudio(world, player);
    }

    @Override
    protected void doReloadTick(World world, NbtCompound nbtCompound, PlayerEntity player, ItemStack stack) {
        int reloadTick = nbtCompound.getInt("reloadTick");
        if (reloadTick == 0 && (world instanceof ServerWorld serverWorld)) {
            sendAnimationPacket(ANIM_RELOAD, serverWorld, player, stack);
        }
        super.doReloadTick(world, nbtCompound, player, stack);
    }

    /*
     * ANIMATION SIDE
     */

    protected void sendAnimationPacket(int animation, ServerWorld world, PlayerEntity player, ItemStack stack) {
        final int id = GeckoLibUtil.guaranteeIDForStack(stack, world);
        GeckoLibNetwork.syncAnimation(player, this, id, animation);
        for (ServerPlayerEntity otherPlayer : PlayerLookup.tracking(player)) {
            GeckoLibNetwork.syncAnimation(otherPlayer, this, id, animation);
        }
    }

    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 1, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static final int ANIM_RELOAD = 0;
    public static final int ANIM_FIRE = 1;
    @Override
    public void onAnimationSync(int id, int state) {
        switch (state) {
            case ANIM_RELOAD -> {
                final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controller");
                if (controller.getAnimationState() == AnimationState.Stopped) {
                    controller.markNeedsReload();
                    controller.setAnimation(new AnimationBuilder().addAnimation("reload", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }
            }
            case ANIM_FIRE -> {
                final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, "controller");
                if (controller.getAnimationState() == AnimationState.Stopped) {
                    controller.markNeedsReload();
                    controller.setAnimation(new AnimationBuilder().addAnimation("fire", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                }
            }
        }
    }
}