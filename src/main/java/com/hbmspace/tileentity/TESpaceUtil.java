package com.hbmspace.tileentity;

import com.hbm.inventory.fluid.Fluids;
import com.hbmspace.dim.CelestialBody;
import com.hbmspace.dim.orbit.WorldProviderOrbit;
import com.hbmspace.dim.trait.CBT_Atmosphere;
import com.hbmspace.handler.atmosphere.AtmosphereBlob;
import com.hbmspace.handler.atmosphere.ChunkAtmosphereManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TESpaceUtil {
    public static boolean breatheAir(World world, BlockPos pos, int amount) {
        CBT_Atmosphere atmosphere = world.provider instanceof WorldProviderOrbit ? null : CelestialBody.getTrait(world, CBT_Atmosphere.class);
        if (atmosphere != null) {
            if (atmosphere.hasFluid(Fluids.AIR, 0.19) || atmosphere.hasFluid(Fluids.OXYGEN, 0.09)) {
                return true;
            }
        }

        List<AtmosphereBlob> blobs = ChunkAtmosphereManager.proxy.getBlobs(world, pos.getX(), pos.getY(), pos.getZ());
        for (AtmosphereBlob blob : blobs) {
            if (blob.hasFluid(Fluids.AIR, 0.19) || blob.hasFluid(Fluids.OXYGEN, 0.09)) {
                blob.consume(amount);
                return true;
            }
        }

        return false;
    }
}
