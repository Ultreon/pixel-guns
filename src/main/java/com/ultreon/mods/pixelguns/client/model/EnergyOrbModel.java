package com.ultreon.mods.pixelguns.client.model;// Made with Blockbench 4.4.3
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.ultreon.mods.pixelguns.PixelGuns;
import com.ultreon.mods.pixelguns.entity.projectile.EnergyOrb;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class EnergyOrbModel extends EntityModel<EnergyOrb> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(PixelGuns.MOD_ID, "textures/entity/projectiles/energy_orb.png"), "main");
	private final ModelPart bb_main;

	public EnergyOrbModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static TexturedModelData createBodyLayer() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		partdefinition.addChild("bb_main", ModelPartBuilder.create().uv(0, 16).cuboid(-3.0F, -11.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -12.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(EnergyOrb var1, float var2, float var3, float var4, float var5, float var6) {
	}
}