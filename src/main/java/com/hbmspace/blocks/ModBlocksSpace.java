package com.hbmspace.blocks;

import com.hbm.blocks.generic.BlockNTMOre;
import com.hbm.blocks.machine.BlockOrbitalStation;
import com.hbm.main.MainRegistry;
import com.hbmspace.blocks.machine.MachineHTR3;
import com.hbmspace.blocks.machine.MachineHTRF4;
import com.hbmspace.blocks.machine.MachineLPW2;
import com.hbmspace.blocks.machine.MachineXenonThruster;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBlocksSpace {
    public static List<Block> ALL_BLOCKS = new ArrayList<>();

    public static final Block moon_rock = new BlockBaseSpace(Material.ROCK, "moon_rock").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F);
    public static final Block tumor = new BlockBaseSpace(Material.CLAY, "tumor").setSoundType(SoundType.SNOW).setCreativeTab(MainRegistry.resourceTab).setHardness(1.0F);
    public static final Block duna_sands = new BlockFallingBaseSpace(Material.SAND, "duna_sands", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
    public static final Block duna_rock = new BlockBaseSpace(Material.ROCK, "duna_rock").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F);
    public static final Block dry_ice = new BlockBaseSpace(Material.ICE,"dry_ice").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
    public static final Block ferric_clay = new BlockBaseSpace(Material.CLAY, "ferric_clay").setSoundType(SoundType.GROUND).setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F);
    public static final Block eve_silt = new BlockFallingBaseSpace(Material.SAND, "eve_silt", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
    public static final Block eve_rock = new BlockBaseSpace(Material.ROCK, "eve_rock").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F);
    public static final Block laythe_silt = new BlockFallingBaseSpace(Material.SAND, "laythe_silt", SoundType.SAND).setCreativeTab(MainRegistry.resourceTab).setHardness(0.5F);
    public static final Block ike_regolith = new BlockBaseSpace(Material.ROCK, "ike_regolith").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block ike_stone = new BlockBaseSpace(Material.ROCK, "ike_stone").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block dres_rock = new BlockBaseSpace(Material.ROCK, "dres_rock").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block moho_regolith = new BlockBaseSpace(Material.ROCK, "moho_regolith").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block moho_stone = new BlockBaseSpace(Material.ROCK, "moho_stone").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block minmus_regolith = new BlockBaseSpace(Material.ROCK, "minmus_regolith").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block minmus_stone = new BlockBaseSpace(Material.ROCK, "minmus_stone").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block minmus_smooth = new BlockBaseSpace(Material.ROCK, "minmus_smooth").setSoundType(SoundType.STONE).setCreativeTab(MainRegistry.resourceTab).setHardness(1.5F).setResistance(10.0F);
    public static final Block ore_gas = new BlockNTMOre("ore_gas", 3).setCreativeTab(MainRegistry.resourceTab).setBlockUnbreakable().setHardness(5.0F).setResistance(10.0F);
    public static final Block ore_gas_empty = new BlockBaseSpace(Material.ROCK, "ore_gas_empty").setCreativeTab(MainRegistry.resourceTab).setHardness(5.0F).setResistance(10.0F);

    public static final Block machine_lpw2 = new MachineLPW2("machine_lpw2").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
    public static final Block machine_htr3 = new MachineHTR3("machine_htr3").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
    public static final Block machine_htrf4 = new MachineHTRF4("machine_htrf4").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
    public static final Block machine_xenon_thruster = new MachineXenonThruster(Material.IRON, "machine_xenon_thruster").setHardness(5.0F).setResistance(100.0F).setCreativeTab(MainRegistry.machineTab);
    public static final Block orbital_station = new BlockOrbitalStation(Material.IRON, "orbital_station").setBlockUnbreakable().setResistance(Float.POSITIVE_INFINITY).setCreativeTab(null);

    public static void preInit(){
        for(Block block : ALL_BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
}
