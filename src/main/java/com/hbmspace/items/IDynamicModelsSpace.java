package com.hbmspace.items;

import com.hbm.items.IDynamicModels;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Used in items that require model baking;
 * Will automatically bake once correct methods are supplied (space version just to separate this shit)
 */
public interface IDynamicModelsSpace extends IDynamicModels {

    /**
     * Should be populated by implementors in constructors.
     */
    List<IDynamicModelsSpace> INSTANCES = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    static void bakeModels(ModelBakeEvent event) {
        INSTANCES.forEach(blockMeta -> blockMeta.bakeModel(event));
    }

    @SideOnly(Side.CLIENT)
    static void registerModels() {
        INSTANCES.forEach(IDynamicModelsSpace::registerModel);
    }

    @SideOnly(Side.CLIENT)
    static void registerSprites(TextureMap map) {
        INSTANCES.forEach(dynamicSprite -> dynamicSprite.registerSprite(map));
    }

    @SideOnly(Side.CLIENT)
    public static void registerCustomStateMappers() {
        for (IDynamicModelsSpace model : INSTANCES) {
            if (model.getSelf() == null || !(model.getSelf() instanceof Block block)) continue;
            StateMapperBase mapper = model.getStateMapper(block.getRegistryName());
            if (mapper != null)
                ModelLoader.setCustomStateMapper(
                        block,
                        mapper
                );
        }

    }

    @SideOnly(Side.CLIENT)
    static void registerItemColorHandlers(ColorHandlerEvent.Item evt) {
        for (IDynamicModelsSpace model : INSTANCES) {
            IItemColor colorHandler = model.getItemColorHandler();
            Object self = model.getSelf();

            if (colorHandler == null || !(self instanceof Item item)) continue;

            evt.getItemColors().registerItemColorHandler(colorHandler, item);
        }
    }

    @SideOnly(Side.CLIENT)
    static void registerBlockColorHandlers(ColorHandlerEvent.Block evt) {
        for (IDynamicModelsSpace model : INSTANCES) {
            IBlockColor colorHandler = model.getBlockColorHandler();
            Object self = model.getSelf();

            if (colorHandler == null || !(self instanceof Block item)) continue;

            evt.getBlockColors().registerBlockColorHandler(colorHandler, item);
        }
    }
}
