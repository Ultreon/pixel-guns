package com.jab125.healthbar;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ultreon.mods.pixelguns.item.GunItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class HealthBar implements ClientModInitializer {
    public static final Identifier HEALTH_BAR = new Identifier("pixel_guns", "textures/gui/health_bar.png");
    public static final Bar health = new Bar(56);
    public static final Bar gunAmount = new Bar(61);
    public static class Bar {
        private Bar(int a) {
            this.a = a;
        }
        private int a;
        private float percent;
        private float healthLatest;
        private long timeHealthSet;
        private float prevHealth = 0;

        private float getHPPercentage() {
            return getPercent();
        }

        private int getHpAsPixels() {
            return (int) (getHPPercentage() * a);
        }

        public void setPercent(float f) {
            this.percent = this.getPercent();
            this.healthLatest = f;
            this.timeHealthSet = Util.getMeasuringTimeMs();
        }

        public float getPercent() {
            long l = Util.getMeasuringTimeMs() - this.timeHealthSet;
            float f = MathHelper.clamp((float)l / 100.0f, 0.0f, 1.0f);
            return MathHelper.lerp(f, this.percent, this.healthLatest);
        }
    }

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((client -> {
            if (client.player != null) {
                if (health.prevHealth != client.player.getHealth()) {
                    health.prevHealth = client.player.getHealth();
                    health.setPercent(health.prevHealth / client.player.getMaxHealth());
                }
                if (client.player.getMainHandStack().getItem() instanceof GunItem gunItem) {
                    if (gunItem.getMaxDamage() - client.player.getHealth() <= 1 && gunAmount.prevHealth != 0) {
                        gunAmount.prevHealth = 0;
                        gunAmount.setPercent(0);
                    } else if (gunAmount.prevHealth != client.player.getMainHandStack().getMaxDamage() - client.player.getMainHandStack().getDamage()) {
                        gunAmount.prevHealth = client.player.getMainHandStack().getMaxDamage() - client.player.getMainHandStack().getDamage();
                        gunAmount.setPercent(gunAmount.prevHealth / client.player.getMainHandStack().getMaxDamage());
                    }
                } else {
                    gunAmount.prevHealth = 0;
                    gunAmount.setPercent(0);
                }
            }
        }));
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> { // 72, 38 -> 143, 61
            RenderSystem.setShaderTexture(0, HEALTH_BAR);
            var hud = MinecraftClient.getInstance().inGameHud;
            var bottom = 0;//hud.scaledHeight - 32 + 3;
            matrixStack.push();
            matrixStack.translate(-10+2, 25, 0);
            //matrixStack.scale(2, 2, 0);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            hud.drawTexture(matrixStack, 10, bottom - 23, 72, 38, 72, 24);
            var ht = InGameHud.HeartType.fromPlayerState(MinecraftClient.getInstance().player);
            var l = ht == InGameHud.HeartType.WITHERED ? Color.DARK_GRAY.darker() : Color.RED.darker();
            RenderSystem.setShaderColor(l.getRed() / 255f, l.getBlue() / 255f, l.getGreen() / 255f, 1);//14
            hud.drawTexture(matrixStack, 10+5, bottom - 14+1, 0, 94, health.getHpAsPixels(), 7); // 71 is pixels
            l = Color.CYAN.darker();
            RenderSystem.setShaderColor(l.getRed() / 255f, l.getBlue() / 255f, l.getGreen() / 255f, 1);//14
            hud.drawTexture(matrixStack, 10+5, bottom - 14+1-5, 0, 92, gunAmount.getHpAsPixels(), 2); // 71 is pixels
            matrixStack.pop();
        }));
    }
}
