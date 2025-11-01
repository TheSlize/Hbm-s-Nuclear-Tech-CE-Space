package com.hbmspace.mixin.mod.hbm;

import com.hbm.main.NetworkHandler;
import com.hbm.packet.PacketDispatcher;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PacketDispatcher.class, remap = false)
public class MixinPacketDispatcher {
    @Shadow
    @Final
    public static NetworkHandler wrapper;

    @Inject(method = "registerPackets", at = @At("TAIL"))
    private static void onRegisterPackets(CallbackInfo ci, @Local int i) {
        //mlbv: empty for now, if new packets are added, register here; the @Local int i is the local counter variable captured(thanks mixinextras!)
    }
}
