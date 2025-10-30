package com.hbmspace.main;

import com.hbm.render.item.BakedModelCustom;
import com.hbm.render.item.BakedModelNoFPV;
import com.hbm.render.item.TEISRBase;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.dim.WorldProviderCelestial;
import com.hbmspace.items.IDynamicModelsSpace;
import com.hbmspace.items.ModItemsSpace;
import com.hbmspace.render.tileentity.IItemRendererProviderSpace;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

@Mod.EventBusSubscriber(modid = RefStrings.MODID)
public class ModEventHandlerClient {

    @SubscribeEvent
    public static void modelBaking(ModelBakeEvent evt) {
        IRegistry<ModelResourceLocation, IBakedModel> reg = evt.getModelRegistry();
        IDynamicModelsSpace.bakeModels(evt);
        for (TileEntitySpecialRenderer<? extends TileEntity> renderer : TileEntityRendererDispatcher.instance.renderers.values()) {
            if (renderer instanceof IItemRendererProviderSpace prov) {
                for (Item item : prov.getItemsForRenderer()) {
                    swapModels(item, reg);
                }
            }
        }

        for (Item renderer : Item.REGISTRY) {
            if (renderer instanceof IItemRendererProviderSpace provider) {
                for (Item item : provider.getItemsForRenderer()) {
                    swapModels(item, reg);
                }
            }
        }

        SpaceMain.proxy.registerMissileItems(reg);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ModItemsSpace.ALL_ITEMS) {
            try {
                registerModel(item, 0);
            } catch (NullPointerException e) {
                e.printStackTrace();
                SpaceMain.logger.info("Failed to register model for " + item.getRegistryName());
            }
        }
        for (Block block : ModBlocksSpace.ALL_BLOCKS) {
            registerBlockModel(block, 0);
        }

        IDynamicModelsSpace.registerModels();
        IDynamicModelsSpace.registerCustomStateMappers();
    }

    @SubscribeEvent
    public static void textureStitch(TextureStitchEvent.Pre evt) {
        TextureMap map = evt.getMap();
        IDynamicModelsSpace.registerSprites(map);
    }

    private static void registerModel(Item item, int meta) {
        if(!(item instanceof IDynamicModelsSpace dyn && dyn.INSTANCES.contains(item))) {
            ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }

    private static void registerBlockModel(Block block, int meta) {
        registerModel(Item.getItemFromBlock(block), meta);
    }

    public static void swapModels(Item item, IRegistry<ModelResourceLocation, IBakedModel> reg) {
        ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
        IBakedModel model = reg.getObject(loc);
        TileEntityItemStackRenderer render = item.getTileEntityItemStackRenderer();
        if (render instanceof TEISRBase) {
            ((TEISRBase) render).itemModel = model;
            reg.putObject(loc, new BakedModelCustom((TEISRBase) render));
        }
    }

    public static void swapModelsNoFPV(Item item, IRegistry<ModelResourceLocation, IBakedModel> reg) {
        ModelResourceLocation loc = new ModelResourceLocation(item.getRegistryName(), "inventory");
        IBakedModel model = reg.getObject(loc);
        TileEntityItemStackRenderer render = item.getTileEntityItemStackRenderer();
        if (render instanceof TEISRBase) {
            ((TEISRBase) render).itemModel = model;
            reg.putObject(loc, new BakedModelNoFPV((TEISRBase) render, model));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void thickenFog(EntityViewRenderEvent.FogDensity event) {
        if(event.getEntity().world.provider instanceof WorldProviderCelestial provider) {
            float fogDensity = provider.fogDensity();

            if(fogDensity > 0) {
                if(GLContext.getCapabilities().GL_NV_fog_distance) {
                    GL11.glFogi(34138, 34139);
                }
                GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);

                event.setDensity(fogDensity);
                event.setCanceled(true);

            }
        }
    }

}
