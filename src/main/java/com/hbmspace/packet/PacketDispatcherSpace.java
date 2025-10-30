package com.hbmspace.packet;

import com.hbmspace.main.RefStrings;
import com.hbm.main.NetworkHandler;
import com.hbmspace.packet.toclient.PermaSyncPacket;
import net.minecraftforge.fml.relauncher.Side;

public class PacketDispatcherSpace {
    public static final NetworkHandler wrapper = new NetworkHandler(RefStrings.MODID);

    public static void registerPackets(){
        int i = 0;

        wrapper.registerMessage(PermaSyncPacket.Handler.class, PermaSyncPacket.class, i++, Side.CLIENT);
    }
}
