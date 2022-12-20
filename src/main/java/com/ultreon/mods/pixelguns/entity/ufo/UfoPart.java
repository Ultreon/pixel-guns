package com.ultreon.mods.pixelguns.entity.ufo;

import com.jab125.multipart.client.entity.MultiPartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;

public class UfoPart
        extends MultiPartEntity<AbstractUfoEntity, UfoPart> {

    public UfoPart(AbstractUfoEntity entity, String string, float f, float g) {
        super(entity, string, f, g);
        //EnderDragonPart
    }

    @Override
    public boolean isCollidable() {
        System.out.println("UFO PART: CALLED IT + " + StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());
        return true;
    }
}

