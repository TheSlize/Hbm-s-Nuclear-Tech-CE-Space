package com.hbmspace.blocks;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbm.items.IDynamicModels;
import com.hbmspace.items.IDynamicModelsSpace;
import net.minecraft.block.material.Material;

public abstract class BlockDummyableSpace extends BlockDummyable implements IDynamicModelsSpace {
    public BlockDummyableSpace(Material materialIn, String s) {
        super(materialIn, s);
        ModBlocks.ALL_BLOCKS.remove(this);
        IDynamicModels.INSTANCES.remove(this);
        ModBlocksSpace.ALL_BLOCKS.add(this);
        IDynamicModelsSpace.INSTANCES.add(this);
    }

    public BlockDummyableSpace(Material materialIn, String s, boolean useBakedModel) {
        super(materialIn, s, useBakedModel);
        ModBlocks.ALL_BLOCKS.remove(this);
        ModBlocksSpace.ALL_BLOCKS.add(this);
    }
}
