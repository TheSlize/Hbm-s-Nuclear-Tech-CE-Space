package com.hbmspace.blocks;

import com.hbm.blocks.BlockFallingBase;
import com.hbm.blocks.ModBlocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockFallingBaseSpace extends BlockFallingBase {
    public BlockFallingBaseSpace(Material materialIn, String name, SoundType type) {
        super(materialIn, name, type);
        ModBlocks.ALL_BLOCKS.remove(this);
        ModBlocksSpace.ALL_BLOCKS.add(this);
    }
}
