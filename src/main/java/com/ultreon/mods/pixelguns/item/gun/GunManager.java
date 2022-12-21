package com.ultreon.mods.pixelguns.item.gun;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.slf4j.Logger;

import java.util.Map;

public class GunManager extends JsonDataLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogUtils.getLogger();
    private Map<RecipeType<?>, Map<Identifier, Recipe<?>>> gunProperties = ImmutableMap.of();
    private Map<Identifier, Recipe<?>> gunPropertiesById = ImmutableMap.of();

    public GunManager() {
        super(GSON, "gun_properties");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> object, ResourceManager resourceManager, Profiler profiler) {

    }
}
