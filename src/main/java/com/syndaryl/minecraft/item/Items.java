package com.syndaryl.minecraft.item;

import org.apache.logging.log4j.Level;

import com.syndaryl.minecraft.SyndarylMod;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.BasicState;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;

@net.minecraftforge.registries.ObjectHolder("syndarylmod")
public class Items {
	public static final Item WOOD_SHEARS = null;
	public static final Item MUG = null;

	public static void bakeObjModel(ModelBakeEvent event, String modelLoc, String itemName) {
		try {
            // Try to load an OBJ model (placed in src/main/resources/assets/SyndarylMod.DOMAIN/models/)
            ResourceLocation location = new ResourceLocation(SyndarylMod.DOMAIN + ":" + modelLoc);
        	SyndarylMod.LOGGER.info("Loading from ResourceLocation " + location.getPath() + "; ");
			IUnbakedModel model = ModelLoaderRegistry.getModel(location);

            if (model instanceof OBJModel) {
            	SyndarylMod.LOGGER.info("Generation of OBJModel succeeded, baking.");
                // If loading OBJ model succeeds, bake the model and replace stick's model with the baked model
                IBakedModel bakedModel = model.bake(event.getModelLoader(), ModelLoader.defaultTextureGetter(), new BasicState(model.getDefaultState(), false), DefaultVertexFormats.ITEM);
                
                event.getModelRegistry().put(new ModelResourceLocation(SyndarylMod.DOMAIN + ":" + itemName, "inventory"), bakedModel);
            }
            else 
            {
            	SyndarylMod.LOGGER.error("Generation of OBJModel failed, produced a " + model.getClass().getCanonicalName() + " instead! No baking could be performed!");
            }
        } catch ( net.minecraftforge.client.model.ModelLoaderRegistry.LoaderException e) {
            e.printStackTrace();
            SyndarylMod.LOGGER.error("LoaderException while trying to load " + modelLoc + " onto item " + SyndarylMod.DOMAIN + ":" + itemName +" "+  e.getMessage());
            SyndarylMod.LOGGER.error("Cause: " + e.getCause().getMessage());
            SyndarylMod.LOGGER.catching(Level.FATAL, e);
        } catch (Exception e) {
            e.printStackTrace();
            SyndarylMod.LOGGER.error("Failure while trying to load " + modelLoc + " onto item " + SyndarylMod.DOMAIN + ":" + itemName +" "+  e.getMessage());
            if (e.getCause() != null)
            	SyndarylMod.LOGGER.error("Cause: " + e.getCause().getMessage());
            	
            SyndarylMod.LOGGER.catching(Level.FATAL, e);
        }
	}
}
