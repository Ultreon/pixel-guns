package com.jab125.multipart.mixin;

import com.jab125.multipart.client.entity.MultiPartEntity;
import com.jab125.multipart.client.entity.MultiPartHolder;
import com.jab125.multipart.extension.ServerWorldExtension;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public class ServerWorldMixin implements ServerWorldExtension {
    final Int2ObjectMap<MultiPartEntity<?, ?>> entityParts = new Int2ObjectOpenHashMap<>();

    @Override
    public Int2ObjectMap<MultiPartEntity<?, ?>> getEntityParts() {
        return entityParts;
    }

    @Inject(method = "getDragonPart", at = @At(value = "RETURN"), cancellable = true)
    public void multipart$getDragonPart(int i, CallbackInfoReturnable<Entity> cir) {
        if (cir.getReturnValue() == null) {
            cir.setReturnValue(entityParts.get(i));
        }
    }



    @Mixin(ServerWorld.ServerEntityHandler.class)
    public static class ServerEntityHandlerMixin {
        @Shadow
        @Final
        ServerWorld field_26936;

        @Inject(method = "startTracking(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;updateEventHandler(Ljava/util/function/BiConsumer;)V", shift = At.Shift.BEFORE))
        public void multipart$startTracking(Entity entity, CallbackInfo ci) {
            if (entity instanceof MultiPartHolder<?, ?> multiPartHolder) {
                for (MultiPartEntity<?, ?> enderDragonPart : multiPartHolder.getBodyParts()) {
                    var l = ((ServerWorld.ServerEntityHandler) (Object) this);
                    ((ServerWorldExtension) field_26936).getEntityParts().put(enderDragonPart.getId(), enderDragonPart);
                }
            }
        }
        @Inject(method = "stopTracking(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;updateEventHandler(Ljava/util/function/BiConsumer;)V", shift = At.Shift.BEFORE))
        public void multipart$stopTracking(Entity entity, CallbackInfo ci) {
            if (entity instanceof MultiPartHolder<?, ?> multiPartHolder) {
                for (MultiPartEntity<?, ?> enderDragonPart : multiPartHolder.getBodyParts()) {
                    var l = ((ServerWorld.ServerEntityHandler) (Object) this);
                    ((ServerWorldExtension) field_26936).getEntityParts().remove(enderDragonPart.getId(), enderDragonPart);
                }
            }
        }
    }
}
