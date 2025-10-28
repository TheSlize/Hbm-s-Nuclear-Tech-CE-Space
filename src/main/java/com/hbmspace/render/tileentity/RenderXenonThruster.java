package com.hbmspace.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.interfaces.AutoRegister;
import com.hbm.render.item.ItemRenderBase;
import com.hbmspace.main.ResourceManagerSpace;
import com.hbmspace.tileentity.machine.TileEntityXenonThruster;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

@AutoRegister
public class RenderXenonThruster extends TileEntitySpecialRenderer<TileEntityXenonThruster>
        implements IItemRendererProviderSpace {

    @Override
    public void render(
            TileEntityXenonThruster te,
            double x,
            double y,
            double z,
            float partialTicks,
            int destroyStage,
            float alpha) {

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x + 0.5, y - 1.0, z + 0.5);

            GlStateManager.rotate(-90, 0, 1, 0);

            switch (te.getBlockMetadata() - BlockDummyable.offset) {
                case 2 -> GlStateManager.rotate(90, 0F, 1F, 0F);
                case 4 -> GlStateManager.rotate(180, 0F, 1F, 0F);
                case 3 -> GlStateManager.rotate(270, 0F, 1F, 0F);
                case 5 -> GlStateManager.rotate(0, 0F, 1F, 0F);
            }

            GlStateManager.translate(0, 0, -0.5);
            float trailStretch = te.getWorld().rand.nextFloat();
            trailStretch = 1.2F - (trailStretch * trailStretch * 0.2F);
            trailStretch *= te.thrustAmount;

            GlStateManager.shadeModel(GL11.GL_SMOOTH);

            bindTexture(ResourceManagerSpace.xenon_thruster_tex);
            ResourceManagerSpace.xenon_thruster.renderPart("Thruster");

            if (trailStretch > 0) {
                GlStateManager.color(1, 1, 1, te.thrustAmount);

                GlStateManager.disableCull();
                GlStateManager.disableLighting();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(
                        GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
                GlStateManager.depthMask(false);

                GlStateManager.translate(0, 0, -1F);
                GlStateManager.scale(1, 1, trailStretch);
                GlStateManager.translate(0, 0, 1F);

                bindTexture(ResourceManagerSpace.xenon_exhaust_tex);
                ResourceManagerSpace.xenon_thruster.renderPart("Exhaust");

                GlStateManager.depthMask(true);
                GlStateManager.enableLighting();
                GlStateManager.enableCull();
                GlStateManager.disableBlend();

                GlStateManager.color(1, 1, 1, 1);
            }

            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public Item getItemForRenderer() {
        return Item.getItemFromBlock(ModBlocksSpace.machine_xenon_thruster);
    }

    @Override
    public ItemRenderBase getRenderer(Item item) {
        return new ItemRenderBase() {
            public void renderInventory() {
                GlStateManager.translate(0, -4, 0);
                GlStateManager.scale(4, 4, 4);
            }

            public void renderCommon() {
                if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) RenderLPW2.offsets.apply(type);
                GlStateManager.shadeModel(GL11.GL_SMOOTH);
                bindTexture(ResourceManagerSpace.xenon_thruster_tex);
                ResourceManagerSpace.xenon_thruster.renderPart("Thruster");
                GlStateManager.shadeModel(GL11.GL_FLAT);
            }
        };
    }
}
