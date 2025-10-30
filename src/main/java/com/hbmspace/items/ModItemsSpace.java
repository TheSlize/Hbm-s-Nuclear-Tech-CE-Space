package com.hbmspace.items;

import com.hbm.blocks.ICustomBlockItem;
import com.hbmspace.items.ItemVOTVdrive;
import com.hbm.main.MainRegistry;
import com.hbmspace.blocks.ModBlocksSpace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModItemsSpace {

    public static final List<Item> ALL_ITEMS = new ArrayList<>();
    public static final Item full_drive = new ItemVOTVdrive("hard_drive_full").setTranslationKey("hard_drive_full").setCreativeTab(MainRegistry.controlTab);

    public static void preInit() {
        for (Item item : ALL_ITEMS) {
            ForgeRegistries.ITEMS.register(item);
        }

        for (Block block : ModBlocksSpace.ALL_BLOCKS) {
            if (block instanceof ICustomBlockItem) {
                ((ICustomBlockItem) block).registerItem();
            } else {
                ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
            }
        }
    }
}
