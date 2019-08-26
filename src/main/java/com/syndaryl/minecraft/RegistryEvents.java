package com.syndaryl.minecraft;

import com.syndaryl.minecraft.client.item.Baker;
import com.syndaryl.minecraft.common.item.ShearsWoodItem;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.client.event.ModelBakeEvent;
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
    	baker.bakeObjModel("item/shears.obj", "wood_shears");
    	baker.bakeObjModel("item/mug.obj", "mug");
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
        		new Item((new Item.Properties()).group(ItemGroup.TOOLS)).setRegistryName(SyndarylMod.DOMAIN, "mug")
        		);
    }
}