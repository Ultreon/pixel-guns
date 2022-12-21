package com.ultreon.mods.pixelguns.entity.ufo;

import com.jab125.multipart.client.entity.MultiPartEntity;

public class UfoPart
        extends MultiPartEntity<AbstractUfoEntity, UfoPart> {

    public UfoPart(AbstractUfoEntity entity, String string, float f, float g) {
        super(entity, string, f, g);
        //EnderDragonPart
    }

    @Override
    public boolean isCollidable() {
        // System.out.println("UFO PART: CALLED IT + " + StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass());
        return true;
    }
}

