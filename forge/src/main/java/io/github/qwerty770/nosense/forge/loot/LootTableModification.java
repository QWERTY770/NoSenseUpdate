package io.github.qwerty770.nosense.forge.loot;

import io.github.qwerty770.nosense.NoSenseMod;
import io.github.qwerty770.nosense.forge.mixins.LootTableForgeMixin;
import io.github.qwerty770.nosense.loot.CustomLootTables;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

import static io.github.qwerty770.nosense.NoSenseMod.ModLogger;
import static io.github.qwerty770.nosense.loot.CustomLootTables.*;

@Mod.EventBusSubscriber(modid = NoSenseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableModification {
    @SubscribeEvent
    public static void register(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        LootTable lootTable = event.getTable();
        if (sheepLootTables.contains(name)) {
            event.setTable(modifyTable(lootTable, (pool -> CustomLootTables.removeItems(pool, ItemTags.WOOL))));
        }
        if (catLootTable.equals(name)){
            event.setTable(modifyTable(lootTable, (pool -> CustomLootTables.removeItem(pool, Items.STRING))));
        }
        ModLogger.info("No Sense Update: Loot tables are modified!");
    }

    public static LootTable modifyTable(LootTable lootTable, Function<LootPool, LootPool.Builder> function){
        LootTable.Builder newTable = LootTable.lootTable();
        for (LootPool pool : ((LootTableForgeMixin) lootTable).getPools()){
            newTable.withPool(function.apply(pool));
        }
        return newTable.build();
    }
}
