package io.github.qwerty770.nosense.forge.loot;

import io.github.qwerty770.nosense.NoSenseMod;
import io.github.qwerty770.nosense.forge.mixins.LootTableForgeMixin;
import io.github.qwerty770.nosense.loot.CustomLootTables;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;
import static io.github.qwerty770.nosense.loot.CustomLootTables.*;

@Mod.EventBusSubscriber(modid = NoSenseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModification {
    @SubscribeEvent
    public static void register(LootTableLoadEvent event) {
        if (sheepLootTables.contains(event.getName())) {
            ModLogger.info("Read loot table {}", event.getName());
            LootTable lootTable = event.getTable();
            LootTable.Builder newTable = LootTable.lootTable();
            for (LootPool pool : ((LootTableForgeMixin) lootTable).getPools()){
                newTable.withPool(CustomLootTables.removeItems(pool, ItemTags.WOOL));
            }
            event.setTable(newTable.build());
            ModLogger.info("No Sense Update: Loot tables are modified!");
        }
    }
}
