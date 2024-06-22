package io.github.qwerty770.nosense.fabric.loot;

import io.github.qwerty770.nosense.loot.CustomLootTables;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;
import static io.github.qwerty770.nosense.loot.CustomLootTables.catLootTable;
import static io.github.qwerty770.nosense.loot.CustomLootTables.sheepLootTables;

public class LootTableModification {
    public static void register(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (sheepLootTables.contains(id) && source.isBuiltin()){
                tableBuilder.modifyPools(pool -> CustomLootTables.removeItems(pool, ItemTags.WOOL));
            }
            if (catLootTable.equals(id) && source.isBuiltin()){
                tableBuilder.modifyPools(pool -> CustomLootTables.removeItem(pool, Items.STRING));
            }
        });
        ModLogger.info("No Sense Update: Loot tables are modified!");
    }
}
