package com.hbmspace.dim;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenLiquidsCelestial extends WorldGenerator {

	// Identical to WorldGenLiquids except you can specify the stone block to replace

	private final Block liquidBlock;
	private final Block targetBlock;

	public WorldGenLiquidsCelestial(Block liquidBlock, Block targetBlock) {
		this.liquidBlock = liquidBlock;
		this.targetBlock = targetBlock;
	}

	public boolean generate(World world, Random rand, BlockPos pos) {
		if(world.getBlockState(pos.up()).getBlock() != targetBlock) {
			return false;
		} else if(world.getBlockState(pos.down()).getBlock() != targetBlock) {
			return false;
		} else if(world.getBlockState(pos).getMaterial() != Material.AIR && world.getBlockState(pos).getBlock() != targetBlock) {
			return false;
		} else {
			int l = 0;

			if(world.getBlockState(pos.add(-1, 0, 0)).getBlock() == targetBlock) {
				++l;
			}

			if(world.getBlockState(pos.add(1, 0, 0)).getBlock() == targetBlock) {
				++l;
			}

			if(world.getBlockState(pos.add(0, 0, -1)).getBlock() == targetBlock) {
				++l;
			}

			if(world.getBlockState(pos.add(0, 0, 1)).getBlock() == targetBlock) {
				++l;
			}

			int i1 = 0;

			if(world.isAirBlock(pos.add(-1, 0, 0))) {
				++i1;
			}

			if(world.isAirBlock(pos.add(1, 0, 0))) {
				++i1;
			}

			if(world.isAirBlock(pos.add(0, 0, -1))) {
				++i1;
			}

			if(world.isAirBlock(pos.add(0, 0, 1))) {
				++i1;
			}

			if(l == 3 && i1 == 1) {
				world.setBlockState(pos, this.liquidBlock.getDefaultState(), 2);
				world.scheduledUpdatesAreImmediate = true;
				this.liquidBlock.updateTick(world, pos, liquidBlock.getDefaultState(), rand);
				world.scheduledUpdatesAreImmediate = false;
			}

			return true;
		}
	}
}
