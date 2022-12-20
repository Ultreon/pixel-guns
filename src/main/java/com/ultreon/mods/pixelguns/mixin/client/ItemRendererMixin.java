package com.ultreon.mods.pixelguns.mixin.client;

import com.ultreon.mods.pixelguns.Config;
import com.ultreon.mods.pixelguns.item.gun.AbstractGunItem;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.lang.Math.abs;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Shadow public float zOffset;
    @Unique
    private TextRenderer textRenderer;
    @Unique
    private ItemStack itemStack;
    @Unique
    private String string;
    @Unique
    private int i;
    @Unique
    private int j;

    @Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"))
    public void args(TextRenderer textRenderer, ItemStack itemStack, int i, int j, String string, CallbackInfo ci) {
        this.textRenderer = textRenderer;
        this.itemStack = itemStack;
        this.string = string;
        this.i = i;
        this.j = j;
    }
    @ModifyVariable(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("STORE"), ordinal = 0)
    public MatrixStack inject(MatrixStack matrixStack) {
        if (Config.GUN_AMMO_DISPLAY.get() == Config.GunAmmoDisplay.AMMO_COUNT && itemStack.getCount() == 1 && itemStack.getItem() instanceof AbstractGunItem gunItem) {
            String string2 = toString(AbstractGunItem.remainingAmmo(itemStack));
            matrixStack.translate(0.0, 0.0, this.zOffset + 200.0f);
            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
            textRenderer.draw(string2, (float)(i + 19 - 2 - textRenderer.getWidth(string2)), (float)(j + 6 + 3), 0xFFFFFF, true, matrixStack.peek().getPositionMatrix(), (VertexConsumerProvider)immediate, false, 0, 0xF000F0);
            immediate.draw();
        }
        return matrixStack;
    }

    @Unique
    private String toString(int a) {
        var d = a < 0 ? "-" : "";
        a = abs(a);
        if (a < 1000) {
            return d + a;
        }
        a = a / 1000;
        if (a < 1000) {
            return d + a + "K";
        }
        a = a / 1000;
        if (a < 1000) {
            return d + a + "M";
        }
        a = a / 1000;
        return d + a + "B";
    }
}
