package com.hbmspace.main;

import com.hbm.sound.AudioWrapper;
import com.hbm.sound.AudioWrapperClient;
import com.hbmspace.render.tileentity.IItemRendererProviderSpace;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ClientProxy extends ServerProxy {

    @Override
    public File getDataDir() {
        return Minecraft.getMinecraft().gameDir;
    }

    @Override
    public void registerRenderInfo() {
        if (!Minecraft.getMinecraft().getFramebuffer().isStencilEnabled())
            Minecraft.getMinecraft().getFramebuffer().enableStencil();
    }
    @Override
    public void preInit(FMLPreInitializationEvent evt) {
        for (TileEntitySpecialRenderer<? extends TileEntity> renderer : TileEntityRendererDispatcher.instance.renderers.values()) {
            if (renderer instanceof IItemRendererProviderSpace prov) {
                for (Item item : prov.getItemsForRenderer()) {
                    item.setTileEntityItemStackRenderer(prov.getRenderer(item));
                }
            }
        }

        // same crap but for items directly because why invent a new solution when this shit works just fine
        for (Item renderer : Item.REGISTRY) {
            if (renderer instanceof IItemRendererProviderSpace provider) {
                for (Item item : provider.getItemsForRenderer()) {
                    item.setTileEntityItemStackRenderer(provider.getRenderer(item));
                }
            }
        }
    }

    @Override
    public AudioWrapper getLoopedSound(SoundEvent sound, SoundCategory cat, float x, float y, float z, float volume, float range, float pitch, int keepAlive) {
        AudioWrapperClient audio = new AudioWrapperClient(sound, cat, true);
        audio.updatePosition(x, y, z);
        audio.updateVolume(volume);
        audio.updateRange(range);
        audio.setKeepAlive(keepAlive);
        return audio;
    }
    @Override
    public AudioWrapper getLoopedSound(SoundEvent sound, SoundCategory cat, float x, float y, float z, float volume, float range, float pitch) {
        AudioWrapperClient audio = new AudioWrapperClient(sound, cat, true);
        audio.updatePosition(x, y, z);
        audio.updateVolume(volume);
        audio.updateRange(range);
        return audio;
    }
}
