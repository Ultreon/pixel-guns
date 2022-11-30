package com.ultreon.mods.pixelguns.client.entity.renderer;

import com.ultreon.mods.pixelguns.entity.GasEntity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.random.Random;

public class GasRenderer extends EntityRenderer<GasEntity> {

    public GasRenderer(Context context) {
        super(context);
    }

    @Override
    public void render(GasEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float[] fs = new float[8];
        float[] gs = new float[8];
        float h = 0.0f;
        float j = 0.0f;
        Random random = Random.create(0);
        for (int k = 7; k >= 0; --k) {
            fs[k] = h;
            gs[k] = j;
            h += (float)(random.nextInt(11) - 5);
            j += (float)(random.nextInt(11) - 5);
        }
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        for (int l = 0; l < 4; ++l) {
            Random random2 = Random.create(0);
            for (int m = 0; m < 3; m++) {
                int n = 7;
                int o = 0;
                if (m > 0) {
                    n = 7 - m;
                }
                if (m > 0) {
                    o = n - 2;
                }
                float p = fs[n] - h;
                float q = gs[n] - j;
                for (int r = n; r >= o; --r) {
                    float s = p;
                    float t = q;
                    if (m == 0) {
                        p += (float)(random2.nextInt(11) - 5);
                        q += (float)(random2.nextInt(11) - 5);
                    } else {
                        p += (float)(random2.nextInt(31) - 15);
                        q += (float)(random2.nextInt(31) - 15);
                    }
                    float y = 0.1f + (float)l * 0.2f;
                    if (m == 0) {
                        y *= (float)r * 0.1f + 1.0f;
                    }
                    float z = 0.1f + (float)l * 0.2f;
                    if (m == 0) {
                        z *= ((float)r - 1.0f) * 0.1f + 1.0f;
                    }
                    drawBranch(matrix4f, vertexConsumer, p, q, r, s, t, 0.45f, 0.45f, 0.5f, y, z, false, false, true, false);
                    drawBranch(matrix4f, vertexConsumer, p, q, r, s, t, 0.45f, 0.45f, 0.5f, y, z, true, false, true, true);
                    drawBranch(matrix4f, vertexConsumer, p, q, r, s, t, 0.45f, 0.45f, 0.5f, y, z, true, true, false, true);
                    drawBranch(matrix4f, vertexConsumer, p, q, r, s, t, 0.45f, 0.45f, 0.5f, y, z, false, true, false, false);
                }
            }
        }
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void drawBranch(Matrix4f matrix4f, VertexConsumer vertexConsumer, float f, float g, int i, float h, float j, float red, float green, float blue, float n, float o, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        vertexConsumer.vertex(matrix4f, f + (bl ? o : -o), i * 16, g + (bl2 ? o : -o)).color(red, green, blue, 0.3f).next();
        vertexConsumer.vertex(matrix4f, h + (bl ? n : -n), (i + 1) * 16, j + (bl2 ? n : -n)).color(red, green, blue, 0.3f).next();
        vertexConsumer.vertex(matrix4f, h + (bl3 ? n : -n), (i + 1) * 16, j + (bl4 ? n : -n)).color(red, green, blue, 0.3f).next();
        vertexConsumer.vertex(matrix4f, f + (bl3 ? o : -o), i * 16, g + (bl4 ? o : -o)).color(red, green, blue, 0.3f).next();
    }

    @Override
    public Identifier getTexture(GasEntity var1) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}