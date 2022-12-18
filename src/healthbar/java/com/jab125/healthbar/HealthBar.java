package com.jab125.healthbar;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

public class HealthBar implements ClientModInitializer {
    public static final Identifier HEALTH_BAR = new Identifier("pixel_guns", "textures/gui/health_bar.png");
    private float percent;
    private float healthLatest;
    private long timeHealthSet;
    private float prevHealth = 0;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register((client -> {
            if (client.player != null) {
                if (prevHealth != client.player.getHealth()) {
                    prevHealth = client.player.getHealth();
                    setPercent(prevHealth / client.player.getMaxHealth());
                }
            }
        }));
        HudRenderCallback.EVENT.register(((matrixStack, tickDelta) -> {
            RenderSystem.setShaderTexture(0, HEALTH_BAR);
            var hud = MinecraftClient.getInstance().inGameHud;
            var bottom = 0;//hud.scaledHeight - 32 + 3;
            matrixStack.push();
            matrixStack.translate(0, hud.scaledHeight-16, 0);
            matrixStack.scale(2, 2, 0);
            RenderSystem.setShaderColor(1, 0, 0, 1);
            hud.drawTexture(matrixStack, 10, bottom, 0, 8, getHpAsPixels()+1 - 1, 8 /*last pixel makes up for precision*/); // 13 is pixels
            RenderSystem.setShaderColor(1, 0, 0, getHpRemainder());
            hud.drawTexture(matrixStack, 10 + getHpAsPixels(), bottom, 0, 62, 1, 8);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            hud.drawTexture(matrixStack, 10, bottom, 0, 0, 64, 8);
            matrixStack.pop();
        }));
    }

    private float getHpRemainder() { // 63 pixels to work with
        var health = MinecraftClient.getInstance().player.getHealth();
        var maxHealth = MinecraftClient.getInstance().player.getMaxHealth();
        if (health == maxHealth) return 1;
        float d = getHPPercentage() % 1;
        var e = d;
        return e;
    }

    private float getHPPercentage() {
        return getPercent();
    }

    private int getHpAsPixels() {
        return (int) (getHPPercentage() * 62);
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
