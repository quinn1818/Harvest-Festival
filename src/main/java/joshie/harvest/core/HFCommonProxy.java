package joshie.harvest.core;

import joshie.harvest.animals.HFAnimals;
import joshie.harvest.blocks.HFBlocks;
import joshie.harvest.buildings.HFBuildings;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.HFIngredients;
import joshie.harvest.cooking.HFRecipes;
import joshie.harvest.core.config.General;
import joshie.harvest.core.config.HFConfig;
import joshie.harvest.core.config.HFVanilla;
import joshie.harvest.crops.HFCrops;
import joshie.harvest.debug.HFDebug;
import joshie.harvest.items.HFItems;
import joshie.harvest.mining.HFMining;
import joshie.harvest.npc.HFNPCs;
import joshie.harvest.npc.gift.init.HFGifts;
import joshie.harvest.plugins.HFPlugins;
import joshie.harvest.quests.HFQuests;
import joshie.harvest.shops.HFShops;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class HFCommonProxy {
    protected static final List<Class> LIST = new ArrayList<Class>();

    static {
        if (General.DEBUG_MODE) {
            LIST.add(HFDebug.class);
        }

        LIST.add(HFCore.class);
        LIST.add(HFVanilla.class);
        LIST.add(HFConfig.class);
        LIST.add(HFPlugins.class);
        LIST.add(HFCrops.class);
        LIST.add(HFNPCs.class);
        LIST.add(HFBuildings.class);
        LIST.add(HFBlocks.class);
        LIST.add(HFItems.class);
        LIST.add(HFCooking.class);
        LIST.add(HFIngredients.class);
        LIST.add(HFRecipes.class);
        LIST.add(HFQuests.class);
        LIST.add(HFShops.class);
        LIST.add(HFMining.class);
        LIST.add(HFGifts.class);
        LIST.add(HFAnimals.class);
        LIST.add(HFTab.class);
        LIST.add(HFRecipeFixes.class);
    }

    public void load(String stage) {
        //Continue
        for (Class c : LIST) {
            try { //Attempt to load default
                c.getMethod(stage).invoke(null);
            } catch (Exception e) { }

            //Attempt to load client side only
            if (isClient()) {
                try { //Attempt to load default
                    c.getMethod(stage + "Client").invoke(null);
                } catch (Exception e) {}
            }
        }
    }

    public boolean isClient() {
        return false;
    }

    public void setBlockModelResourceLocation(Item item, String name) {}
}
