package com.hbmspace.render.tileentity;

import com.hbm.render.item.ItemRenderBase;
import com.hbm.render.tileentity.IItemRendererProvider;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public interface IItemRendererProviderSpace extends IItemRendererProvider {
    Item getItemForRenderer();

    default Item[] getItemsForRenderer() {
        return new Item[] { this.getItemForRenderer() };
    }

    ItemRenderBase getRenderer(Item item);

    default void correctRotation(TileEntity te){}
}
