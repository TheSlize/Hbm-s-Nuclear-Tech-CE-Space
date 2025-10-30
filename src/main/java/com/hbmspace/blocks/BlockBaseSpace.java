package com.hbmspace.blocks;

import com.hbm.blocks.BlockBase;
import com.hbm.blocks.ModBlocks;
import net.minecraft.block.material.Material;

public class BlockBaseSpace extends BlockBase {
    public BlockBaseSpace(Material mat, String s){
        super(mat, s);
        ModBlocks.ALL_BLOCKS.remove(this);
        ModBlocksSpace.ALL_BLOCKS.add(this);
    }
}
