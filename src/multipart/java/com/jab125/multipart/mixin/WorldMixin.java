package com.jab125.multipart.mixin;

import com.jab125.multipart.client.entity.MultiPartEntity;
import com.jab125.multipart.client.entity.MultiPartHolder;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.util.TypeFilter;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Predicate;

@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "method_31596", at=@At("RETURN"))
    private static void multipart$method_31596(Predicate predicate, List list, TypeFilter typeFilter, Entity entity, CallbackInfo ci) {
        if (entity instanceof MultiPartHolder<?,?> multiPartHolder) {
            for (MultiPartEntity<?, ?> enderDragonPart : multiPartHolder.getBodyParts()) {
                Entity entity2 = (Entity)typeFilter.downcast(enderDragonPart);
                if (entity2 == null || !predicate.test(entity2)) continue;
                list.add(entity2);
            }
        }
    }

    @Inject(method = "method_31593", at=@At("RETURN"))
    private static void multipart$method_31593(Entity entity, Predicate predicate, List list, Entity entity2, CallbackInfo ci) {
        if (entity2 instanceof MultiPartHolder<?,?> multiPartHolder) {
            for (MultiPartEntity<?, ?> enderDragonPart : multiPartHolder.getBodyParts()) {
                if (entity2 == entity || !predicate.test(enderDragonPart)) continue;
                list.add(enderDragonPart);
            }
        }
    }
}
