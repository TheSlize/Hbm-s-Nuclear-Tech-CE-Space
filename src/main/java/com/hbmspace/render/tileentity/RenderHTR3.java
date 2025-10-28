package com.hbmspace.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.util.BobMathUtil;
import com.hbmspace.blocks.ModBlocksSpace;
import com.hbmspace.interfaces.AutoRegister;
import com.hbmspace.main.ResourceManagerSpace;
import com.hbmspace.tileentity.machine.TileEntityMachineHTR3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

@AutoRegister
public class RenderHTR3 extends TileEntitySpecialRenderer<TileEntityMachineHTR3>
        implements IItemRendererProviderSpace {

    @Override
    public void render(TileEntityMachineHTR3 rocket, double x, double y, double z, float interp, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x + 0.5D, y - 3D, z + 0.5D);
            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            GlStateManager.shadeModel(GL11.GL_SMOOTH);

            switch (rocket.getBlockMetadata() - BlockDummyable.offset) {
                case 3 -> GlStateManager.rotate(270, 0F, 1F, 0F);
                case 5 -> GlStateManager.rotate(0, 0F, 1F, 0F);
                case 2 -> GlStateManager.rotate(90, 0F, 1F, 0F);
                case 4 -> GlStateManager.rotate(180, 0F, 1F, 0F);
            }

            double t = rocket.lastTime + (rocket.time - rocket.lastTime) * interp;

            double swayTimer = (t / 3D) % (Math.PI * 4);
            double sway = (Math.sin(swayTimer) + Math.sin(swayTimer * 2) + Math.sin(swayTimer * 4) + 2.23255D) * 0.5;

            double bellTimer = (t / 5D) % (Math.PI * 4);
            double h = (Math.sin(bellTimer + Math.PI) + Math.sin(bellTimer * 1.5D)) / 1.90596D;
            double v = (Math.sin(bellTimer) + Math.sin(bellTimer * 1.5D)) / 1.90596D;

            double pistonTimer = (t / 5D) % (Math.PI * 2);
            double piston = BobMathUtil.sps(pistonTimer);
            double rotorTimer = (t / 5D) % (Math.PI * 16);
            double rotor = (BobMathUtil.sps(rotorTimer) + rotorTimer / 2D - 1) / 25.1327412287D;
            double turbine = (t % 100) / 100D;

            bindTexture(ResourceManagerSpace.lpw2_tex);
            // FIXME does whatever shit it wants but it doesn't render properly
            ResourceManagerSpace.htr3.renderPart("Center");

            ResourceManagerSpace.htr3.renderOnly("PipeL1", "PipeL2", "PipeR1", "PipeR2");

            renderMainAssembly(sway, h, v, piston, rotor, turbine);

            double coverTimer = (t / 5D) % (Math.PI * 4);
            double cover = (Math.sin(coverTimer) + Math.sin(coverTimer * 2) + Math.sin(coverTimer * 4)) * 0.5;

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, -cover * 0.125);
            ResourceManagerSpace.htr3.renderOnly("CoverTop", "CoverBottom");
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.translate(0, 0, 3.5);
            GlStateManager.scale(1, 1, (3 + cover * 0.125) / 3);
            GlStateManager.translate(0, 0, -3.5);
            ResourceManagerSpace.htr3.renderOnly("SuspensionBottom", "SuspensionTop");
            GlStateManager.popMatrix();

            GlStateManager.shadeModel(GL11.GL_FLAT);
        }

        GlStateManager.popMatrix();
    }

    public static void renderMainAssembly(double sway, double h, double v, double piston, double rotor, double turbine) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -sway * 0.125);

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, piston * 0.375D + 0.375D);
        ResourceManagerSpace.htr3.renderPart("Piston");
        GlStateManager.popMatrix();

        renderBell(h, v);
        GlStateManager.popMatrix();

        renderShroud(h, v);
    }

    public static void renderBell(double h, double v) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 3.5, -1);
        double magnitude = 2D;
        GlStateManager.rotate((float)(v * magnitude), 0f, 1f, 0f);
        GlStateManager.rotate((float)(h * magnitude), 1f, 0f, 0f);
        GlStateManager.translate(0, -3.5, 1);
        ResourceManagerSpace.htr3.renderPart("Engine");
        GlStateManager.popMatrix();
    }

    public static void renderShroud(double h, double v) {

        double magnitude = 0.125D;

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, -h * magnitude, 0);
        ResourceManagerSpace.htr3.renderPart("ShroudH");

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(v * magnitude, 0, 0);
        ResourceManagerSpace.htr3.renderPart("ShroudV");

        GlStateManager.popMatrix();
    }

    @Override
    public Item getItemForRenderer() {
        return Item.getItemFromBlock(ModBlocksSpace.machine_htr3);
    }

    @Override
    public ItemRenderBase getRenderer(Item item) {
        return new ItemRenderBase() {
            public void renderInventory() {
                GlStateManager.translate(0, -3, 0);
                GlStateManager.scale(2.5, 2.5, 2.5);
            }

            public void renderCommon() {
                if(type == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) RenderLPW2.offsets.apply(type);
                GlStateManager.scale(0.5, 0.5, 0.5);
                GlStateManager.shadeModel(GL11.GL_SMOOTH);
                bindTexture(ResourceManagerSpace.lpw2_tex);
                ResourceManagerSpace.htr3.renderAllExcept("ExhaustVacuum");
                GlStateManager.shadeModel(GL11.GL_FLAT);
            }
        };
    }
}
