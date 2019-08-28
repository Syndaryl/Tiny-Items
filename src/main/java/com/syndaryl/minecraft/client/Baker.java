/**
 * 
 */
package com.syndaryl.minecraft.client;

import java.util.function.Function;

import org.apache.logging.log4j.Level;

import com.syndaryl.minecraft.SyndarylMod;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.BasicState;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;

/**
 * @author Owner
 *
 */
public class Baker {
	final ModelBakeEvent BAKE_EVENT;
	public Baker(ModelBakeEvent event) {
		BAKE_EVENT = event;
	}
	public void bakeObjModel(String modelLoc, String itemName) {
		String domain = SyndarylMod.DOMAIN;
		bakeObjModel(domain, modelLoc, itemName);
	}
	/**
	 * Try to load an OBJ model (placed in src/main/resources/assets/{domain}/models/)
	 * Does not throw exceptions, just logs errors.
	 * @param event  -  The bake event that this obj model is being constructed in
	 * @param domain -  generally the modid
	 * @param modelLoc - name of the obj file, including .obj extension
	 * @param itemName - name of the item to be registered to
	 */
	public void bakeObjModel(String domain, String modelLoc, String itemName) {
		ModelResourceLocation modelResourceLocationKey = new ModelResourceLocation(domain + ":" + itemName, "inventory");
		ResourceLocation location = new ResourceLocation(domain + ":" + modelLoc);
		SyndarylMod.LOGGER.info("bakeObjModel(): Loading from ResourceLocation " + location.toString() + "; ");
		try {
			bakeOBJ(modelResourceLocationKey, location);
			
		} catch ( net.minecraftforge.client.model.ModelLoaderRegistry.LoaderException e) {
			SyndarylMod.LOGGER.error("LoaderException while trying to load " + modelLoc + " onto item " + domain + ":" + itemName +" "+  e.getMessage());
			SyndarylMod.LOGGER.error("Cause: " + e.getCause().getMessage());
			SyndarylMod.LOGGER.catching(Level.FATAL, e);
		} catch (Exception e) {
			SyndarylMod.LOGGER.error("Failure while trying to load " + modelLoc + " onto item " + domain + ":" + itemName +" "+  e.getMessage());
			if (e.getCause() != null)
				SyndarylMod.LOGGER.error("Cause: " + e.getCause().getMessage());

			SyndarylMod.LOGGER.catching(Level.FATAL, e);
		}
		
		
	}
	/**
	 * @param event  -  The bake event that this obj model is being constructed in
	 * @param modelResourceLocationKey - Inventory itemname
	 * @param location - the obj file, including .obj extension
	 * @throws Exception - passed on from ModelLoaderRegistry.getModel()
	 */
	private  IBakedModel bakeOBJ(ModelResourceLocation modelResourceLocationKey,
			ResourceLocation location) throws Exception {
		IBakedModel bakedModel = null;
		try {
			IUnbakedModel model = ModelLoaderRegistry.getModel(location);

			if (model instanceof OBJModel) {
				SyndarylMod.LOGGER.info("Generation of OBJModel succeeded, baking.");
				// If loading OBJ model succeeds, bake the model and replace stick's model with the baked model
				Function<ResourceLocation, TextureAtlasSprite> spriteGetter = ModelLoader.defaultTextureGetter();
				
				OBJModel objModel = (OBJModel)model;
				bakedModel = objModel.bake(BAKE_EVENT.getModelLoader(), spriteGetter, new BasicState(objModel.getDefaultState(), false), DefaultVertexFormats.ITEM);
				bakedModel = BAKE_EVENT.getModelRegistry().put(modelResourceLocationKey, bakedModel);
			}
			else 
			{
				SyndarylMod.LOGGER.error("Generation of OBJModel failed, produced a " + model.getClass().getCanonicalName() + " instead! No baking could be performed!");
			}
		} catch (Exception e) {
			throw e;
		}
		return bakedModel;
	}
}
