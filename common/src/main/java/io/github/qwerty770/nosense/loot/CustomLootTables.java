package io.github.qwerty770.nosense.loot;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;

public abstract class CustomLootTables {
    public static ResourceLocation getId(Item item){
        return BuiltInRegistries.ITEM.getKey(item);
    }

    public static List<Item> getItemsFromTagKey(TagKey<Item> tagKey){
        // ItemStack[] itemStacks = Ingredient.of(tagKey).getItems();
        // List<Item> items = Arrays.stream(itemStacks).map(ItemStack::getItem).toList();
        List<Item> items = new ArrayList<>();
        Optional<HolderSet.Named<Item>> itemHolders = BuiltInRegistries.ITEM.getTag(tagKey);
        if (itemHolders.isEmpty()) {
            ModLogger.warn("Item tag does not exist: {}", tagKey.location());
            return new ArrayList<>();
        }
        for (Holder<Item> itemHolder : itemHolders.get()){
            // ModLogger.info("Added item {}", itemHolder.value());
            items.add(itemHolder.value());
        }
        return items;
    }

    public static void removeItem(LootPool.Builder pool, Item item){
        // Removes one item from a loot pool builder. For the Fabric platform.
        for (LootPoolEntryContainer entryContainer : pool.build().entries){
            if (entryContainer.getType().equals(LootPoolEntries.ITEM)){
                LootItem lootItem = (LootItem) entryContainer;
                if (getId(lootItem.item) == getId(item)){
                    pool.entries.remove(entryContainer);
                }
            }
        }
    }

    public static LootPool.Builder removeItem(LootPool pool, Item item){
        // Removes one item from a loot pool. For the Forge platform.
        LootPool.Builder newPool = LootPool.lootPool();
        for (LootPoolEntryContainer entryContainer : pool.entries){
            if (entryContainer.getType().equals(LootPoolEntries.ITEM)){
                LootItem lootItem = (LootItem) entryContainer;
                if (getId(lootItem.item) != getId(item)){
                    newPool.entries.add(entryContainer);
                }
            }
            else {
                newPool.entries.add(entryContainer);
            }
        }
        return newPool;
    }

    public static void removeItems(LootPool.Builder pool, TagKey<Item> tagKey){
        // Removes the specific items from a loot pool builder. For the Fabric platform.
        List<Item> items = getItemsFromTagKey(tagKey);
        for (LootPoolEntryContainer entryContainer : pool.build().entries){
            if (entryContainer.getType().equals(LootPoolEntries.ITEM)){
                LootItem lootItem = (LootItem) entryContainer;
                if (items.contains(lootItem.item)){
                    pool.entries.remove(entryContainer);
                }
            }
        }
    }

    public static LootPool.Builder removeItems(LootPool lootPool, TagKey<Item> tagKey){
        // Removes the specific items from a loot pool. For the Forge platform.
        LootPool.Builder newPool = LootPool.lootPool();
        List<Item> items = getItemsFromTagKey(tagKey);
        for (LootPoolEntryContainer entryContainer : lootPool.entries){
            if (entryContainer.getType().equals(LootPoolEntries.ITEM)){
                LootItem lootItem = (LootItem) entryContainer;
                if (!items.contains(lootItem.item)){
                    newPool.entries.add(entryContainer);
                }
            }
            else {
                newPool.entries.add(entryContainer);
            }
        }
        return newPool;
    }

    public static List<ResourceLocation> sheepLootTables = Lists.newArrayList(BuiltInLootTables.SHEEP_WHITE,
            BuiltInLootTables.SHEEP_ORANGE, BuiltInLootTables.SHEEP_MAGENTA, BuiltInLootTables.SHEEP_LIGHT_BLUE,
            BuiltInLootTables.SHEEP_YELLOW, BuiltInLootTables.SHEEP_LIME, BuiltInLootTables.SHEEP_PINK,
            BuiltInLootTables.SHEEP_GRAY, BuiltInLootTables.SHEEP_LIGHT_GRAY, BuiltInLootTables.SHEEP_CYAN,
            BuiltInLootTables.SHEEP_PURPLE, BuiltInLootTables.SHEEP_BLUE, BuiltInLootTables.SHEEP_BROWN,
            BuiltInLootTables.SHEEP_GREEN, BuiltInLootTables.SHEEP_RED, BuiltInLootTables.SHEEP_BLACK);
    public static ResourceLocation catLootTable = new ResourceLocation("entities/cat");
}
