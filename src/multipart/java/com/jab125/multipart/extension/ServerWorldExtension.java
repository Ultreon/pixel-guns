package com.jab125.multipart.extension;

import com.jab125.multipart.client.entity.MultiPartEntity;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

public interface ServerWorldExtension {
    public Int2ObjectMap<MultiPartEntity<?, ?>> getEntityParts();
}
