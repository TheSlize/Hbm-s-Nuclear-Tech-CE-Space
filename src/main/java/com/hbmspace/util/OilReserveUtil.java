package com.hbmspace.util;

import com.hbm.blocks.ModBlocks;
import com.hbmspace.blocks.generic.BlockOreFluid;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Single place that decides what counts as a fluid reserve in the world, so worldgen, the
 * detector, the drills and the block itself cannot drift apart.
 *
 * @see BlockOreFluid
 */
public final class OilReserveUtil {

    /** Bedrock reserves live in the lowest few layers, depending on the celestial body. */
    public static final int BEDROCK_SCAN_TOP = 4;
    /** How far above the player a column scan starts, as in NTM's own detector. */
    public static final int COLUMN_SCAN_HEADROOM = 15;

    private OilReserveUtil() {}

    /** True if a machine may extract from this block. */
    public static boolean isFullReserve(Block block) {
        return BlockOreFluid.isFullReserve(block) || block == ModBlocks.ore_oil || block == ModBlocks.ore_bedrock_oil;
    }

    /** True if a machine may traverse this block while looking for a full reserve. */
    public static boolean isDrainedReserve(Block block) {
        return BlockOreFluid.isEmptyReserve(block) || block == ModBlocks.ore_oil_empty;
    }

    public static boolean isReserve(Block block) {
        return isFullReserve(block) || isDrainedReserve(block);
    }

    /**
     * Finds an undrained, non-bedrock reserve in the column at (x, z). Drained blocks are ignored:
     * the detector reports deposits worth drilling, not exhausted ones.
     */
    public static Block findReserveInColumn(World world, int x, int y, int z) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int top = Math.min(y + COLUMN_SCAN_HEADROOM, world.getHeight() - 1);

        for (int i = top; i > 0; i--) {
            Block block = world.getBlockState(pos.setPos(x, i, z)).getBlock();
            if (block == ModBlocks.ore_bedrock_oil) continue; // reported separately
            if (isFullReserve(block)) return block;
        }

        return null;
    }

    /** Finds a bedrock reserve at (x, z). */
    public static Block findBedrockReserve(World world, int x, int z) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int y = 0; y <= BEDROCK_SCAN_TOP; y++) {
            Block block = world.getBlockState(pos.setPos(x, y, z)).getBlock();
            if (block == ModBlocks.ore_bedrock_oil) return block;
        }

        return null;
    }
}
