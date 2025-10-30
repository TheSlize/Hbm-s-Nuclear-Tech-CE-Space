package com.hbmspace.packet;

import com.hbm.handler.pollution.PollutionHandler;
import com.hbm.main.MainRegistry;
import com.hbmspace.dim.CelestialBody;
import com.hbmspace.dim.SolarSystemWorldSavedData;
import com.hbmspace.dim.WorldProviderCelestial;
import com.hbmspace.dim.trait.CelestialBodyTrait;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.util.HashMap;

public class PermaSyncHandler {

    public static IntOpenHashSet boykissers = new IntOpenHashSet();
    public static float[] pollution = new float[PollutionHandler.PollutionType.values().length];

    public static void writePacket(ByteBuf buf, World world, EntityPlayerMP player) {

        /// CBT ///
        if(world.getTotalWorldTime() % 5 == 1) { // update a little less frequently to not blast the players with large packets
            buf.writeBoolean(true);

            SolarSystemWorldSavedData solarSystemData = SolarSystemWorldSavedData.get(world);
            for(CelestialBody body : CelestialBody.getAllBodies()) {
                HashMap<Class<? extends CelestialBodyTrait>, CelestialBodyTrait> traits = solarSystemData.getTraits(body.name);
                if(traits != null) {
                    buf.writeBoolean(true); // Has traits marker (since we can have an empty list)
                    buf.writeInt(traits.size());

                    for(int i = 0; i < CelestialBodyTrait.traitList.size(); i++) {
                        Class<? extends CelestialBodyTrait> traitClass = CelestialBodyTrait.traitList.get(i);
                        CelestialBodyTrait trait = traits.get(traitClass);

                        if(trait != null) {
                            buf.writeInt(i); // ID of the trait, in order registered
                            trait.writeToBytes(buf);
                        }
                    }
                } else {
                    buf.writeBoolean(false);
                }
            }
        } else {
            buf.writeBoolean(false);
        }
        /// CBT ///

        /// TIME OF DAY ///
        if(world.provider instanceof WorldProviderCelestial && world.provider.getDimension() != 0) {
            buf.writeBoolean(true);
            ((WorldProviderCelestial) world.provider).serialize(buf);
        } else {
            buf.writeBoolean(false);
        }
        /// TIME OF DAY ///
    }

    public static void readPacket(ByteBuf buf, World world, EntityPlayer player) {

        /// CBT ///
        if(buf.readBoolean()) {
            try {
                HashMap<String, HashMap<Class<? extends CelestialBodyTrait>, CelestialBodyTrait>> traitMap = new HashMap<>();

                for(CelestialBody body : CelestialBody.getAllBodies()) {
                    if(buf.readBoolean()) {
                        HashMap<Class<? extends CelestialBodyTrait>, CelestialBodyTrait> traits = new HashMap<>();

                        int cbtSize = buf.readInt();
                        for(int i = 0; i < cbtSize; i++) {
                            CelestialBodyTrait trait = CelestialBodyTrait.traitList.get(buf.readInt()).newInstance();
                            trait.readFromBytes(buf);

                            traits.put(trait.getClass(), trait);
                        }

                        traitMap.put(body.name, traits);
                    }
                }

                SolarSystemWorldSavedData.updateClientTraits(traitMap);
            } catch (Exception ex) {
                // If any exception occurs, stop parsing any more bytes, they'll be unaligned
                // We'll unset the client trait set to prevent any issues

                MainRegistry.logger.catching(ex);
                SolarSystemWorldSavedData.updateClientTraits(null);

                return;
            }
        }
        /// CBT ///

        /// TIME OF DAY ///
        if(buf.readBoolean() && world.provider instanceof WorldProviderCelestial) {
            ((WorldProviderCelestial) world.provider).deserialize(buf);
        }
        /// TIME OF DAY ///
    }
}
