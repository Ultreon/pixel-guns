package com.ultreon.mods.pixelguns.item;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class BlueprintItem extends Item {
    public static final List<BlueprintItem> BLUEPRINT_ITEM_LIST = new ArrayList<>();

    public BlueprintItem(Properties settings) {
        super(settings);
        BLUEPRINT_ITEM_LIST.add(this);
    }
}

