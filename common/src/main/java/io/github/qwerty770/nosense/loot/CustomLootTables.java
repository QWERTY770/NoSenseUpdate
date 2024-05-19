package io.github.qwerty770.nosense.loot;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;

public abstract class CustomLootTables {
    public static List<ResourceLocation> sheepLootTables = Lists.newArrayList(BuiltInLootTables.SHEEP_WHITE,
            BuiltInLootTables.SHEEP_ORANGE, BuiltInLootTables.SHEEP_MAGENTA, BuiltInLootTables.SHEEP_LIGHT_BLUE,
            BuiltInLootTables.SHEEP_YELLOW, BuiltInLootTables.SHEEP_LIME, BuiltInLootTables.SHEEP_PINK,
            BuiltInLootTables.SHEEP_GRAY, BuiltInLootTables.SHEEP_LIGHT_GRAY, BuiltInLootTables.SHEEP_CYAN,
            BuiltInLootTables.SHEEP_PURPLE, BuiltInLootTables.SHEEP_BLUE, BuiltInLootTables.SHEEP_BROWN,
            BuiltInLootTables.SHEEP_GREEN, BuiltInLootTables.SHEEP_RED, BuiltInLootTables.SHEEP_BLACK);
}
