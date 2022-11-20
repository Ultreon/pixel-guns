package com.ultreon.mods.pixelguns.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;

public class BlueprintItem extends Item {
    public static final List<BlueprintItem> BLUEPRINT_ITEM_LIST = new ArrayList<>();

    public BlueprintItem(Settings settings) {
        super(settings);
        BLUEPRINT_ITEM_LIST.add(this);
    }
}

