package com.ultreon.mods.pixelguns.mixin;

import com.google.common.collect.ImmutableList;
import com.ultreon.mods.pixelguns.entity.ufo.AbstractUfoEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.EntityView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@Mixin(EntityView.class)
public interface EntityViewMixin {
    @Shadow List<Entity> getOtherEntities(@Nullable Entity entity, Box box);

    @Shadow List<Entity> getOtherEntities(@Nullable Entity var1, Box var2, Predicate<? super Entity> var3);

    @Inject(method = "getEntityCollisions", at = @At("HEAD"), cancellable = true)
    public default void getEntityCollisions(@Nullable Entity entity, Box box, CallbackInfoReturnable<List<VoxelShape>> cir) {
//        if (box.getAverageSideLength() < 1.0E-7) {
//            cir.setReturnValue(List.of());
//            return;
//        }
        if (entity instanceof AbstractUfoEntity) {
            //System.out.println(box.toString());
        }
        Predicate<Entity> predicate = entity == null ? EntityPredicates.CAN_COLLIDE : EntityPredicates.EXCEPT_SPECTATOR.and(entity::collidesWith);
        List<Entity> list = this.getOtherEntities(entity, box/*.expand(1.0E-7)*/, predicate);
        if (list.isEmpty()) {
            cir.setReturnValue(List.of());
            return;
        }
        ImmutableList.Builder builder = ImmutableList.builderWithExpectedSize(list.size());
        for (Entity entity2 : list) {
            builder.add(VoxelShapes.cuboid(entity2.getBoundingBox()));
        }
        cir.setReturnValue(builder.build());
        return;
    }
}
