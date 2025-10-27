package com.hbm.blocks;

import com.hbm.blocks.machine.MachineLPW2;
import com.hbm.main.MainRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksSpace {

    public static final Block machine_lpw2 = new MachineLPW2("machine_lpw2").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);

    public static List<Block> ALL_BLOCKS = new ArrayList<>();

    public static void preInit(){
        for(Block block : ALL_BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
}
