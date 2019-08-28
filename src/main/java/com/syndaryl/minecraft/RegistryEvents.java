package com.syndaryl.minecraft;

import com.syndaryl.minecraft.client.Baker;
import com.syndaryl.minecraft.items.Hammer;
import com.syndaryl.minecraft.items.ShearsWoodItem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
// Event bus for receiving Registry Events)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    public static void onModelBakeEvent(ModelBakeEvent event) {  
    	SyndarylMod.LOGGER.info("HELLO from onModelBakeEvent()");
    	Baker baker = new Baker(event);
    	baker.bakeObjModel("block/shears.obj", "wood_shears");
    	
    	baker.bakeObjModel("block/mug.obj", "mug");
    }

    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	SyndarylMod.LOGGER.info("HELLO from registerBlocks()"); 
        //TODO: event.getRegistry().registerAll(block1, block2, ...);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
    	SyndarylMod.LOGGER.info("HELLO from registerItems()");         
        event.getRegistry().registerAll(
        		new ShearsWoodItem((new Item.Properties()).maxDamage(60).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "wood_shears"),
        		new Item((new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "mug"),
        		new Hammer(ItemTier.STONE, 4, 2.7f, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "stone_hammer"),
        		new Hammer(ItemTier.IRON, 5, 2.7f, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "iron_hammer"),
        		new Hammer(ItemTier.GOLD, 3, 2.1f, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "gold_hammer"),
        		new Hammer(ItemTier.DIAMOND, 6, 2.7f, (new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "diamond_hammer")
        		);
    }
}