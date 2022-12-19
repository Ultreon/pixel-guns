package com.jab125.multipart.client.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface MultiPartHolder<T extends Entity & MultiPartHolder<T, R>, R extends MultiPartEntity<T, R>> {

    R[] getBodyParts();

    boolean damagePart(R trMultiPartEntity, DamageSource damageSource, float f);
}
