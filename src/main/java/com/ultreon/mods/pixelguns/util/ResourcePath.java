package com.ultreon.mods.pixelguns.util;

import com.ultreon.mods.pixelguns.PixelGuns;
import net.minecraft.util.Identifier;

public class ResourcePath {
    public static Identifier get(String name) {
        return new Identifier(PixelGuns.MOD_ID, name);
    }
}
