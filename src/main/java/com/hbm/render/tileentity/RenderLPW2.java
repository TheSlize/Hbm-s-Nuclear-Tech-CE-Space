package com.hbm.render.tileentity;

import com.hbm.blocks.BlockDummyable;
import com.hbm.blocks.ModBlocks;
import com.hbmspace.interfaces.AutoRegister;
import com.hbm.main.ResourceManagerSpace;
import com.hbm.render.item.ItemRenderBase;
import com.hbm.tileentity.machine.TileEntityMachineLPW2;
import com.hbm.util.BobMathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

@AutoRegister
public class RenderLPW2 extends TileEntitySpecialRenderer<TileEntityMachineLPW2> implements IItemRendererProviderSpace {

    @Override
    public void render(TileEntityMachineLPW2 te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y, z + 0.5);

        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        switch(te.getBlockMetadata() - BlockDummyable.offset) {
            case 2: GlStateManager.rotate(90, 0F, 1F, 0F); break;
            case 4: GlStateManager.rotate(180, 0F, 1F, 0F); break;
            case 3: GlStateManager.rotate(270, 0F, 1F, 0F); break;
            case 5: GlStateManager.rotate(0, 0F, 1F, 0F); break;
        }

        long time = te.getWorld().getTotalWorldTime();

        double t = te.lastTime + (te.time - te.lastTime) * partialTicks;

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
        ResourceManagerSpace.lpw2.renderPart("Frame");

        renderMainAssembly(sway, h, v, piston, rotor, turbine);

        GlStateManager.pushMatrix();
        GlStateManager.translate(-2.9375, 0, 2.375);
        GL11.glRotated(sway * 10, 0, 1, 0);
        GlStateManager.translate(2.9375, 0, -2.375);
        ResourceManagerSpace.lpw2.renderPart("WireLeft");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(2.9375, 0, 2.375);
        GL11.glRotated(sway * -10, 0, 1, 0);
        GlStateManager.translate(-2.9375, 0, -2.375);
        ResourceManagerSpace.lpw2.renderPart("WireRight");
        GlStateManager.popMatrix();

        double coverTimer = (t / 5D) % (Math.PI * 4);
        double cover = (Math.sin(coverTimer) + Math.sin(coverTimer * 2) + Math.sin(coverTimer * 4)) * 0.5;

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -cover * 0.125);
        ResourceManagerSpace.lpw2.renderPart("Cover");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 3.5);
        GL11.glScaled(1, 1, (3 + cover * 0.125) / 3);
        GlStateManager.translate(0, 0, -3.5);
        ResourceManagerSpace.lpw2.renderPart("SuspensionCoverFront");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -5.5);
        GL11.glScaled(1, 1, (1.5 - cover * 0.125) / 1.5);
        GlStateManager.translate(0, 0, 5.5);
        ResourceManagerSpace.lpw2.renderPart("SuspensionCoverBack");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -9);
        GL11.glScaled(1, 1, (1.25 - sway * 0.125) / 1.25);
        GlStateManager.translate(0, 0, 9);
        ResourceManagerSpace.lpw2.renderPart("SuspensionBackOuter");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -9.5);
        GL11.glScaled(1, 1, (1.75 - sway * 0.125) / 1.75);
        GlStateManager.translate(0, 0, 9.5);
        ResourceManagerSpace.lpw2.renderPart("SuspensionBackCenter");
        GlStateManager.popMatrix();

        double serverTimer = (t / 2D) % (Math.PI * 4);
        double sx = (Math.sin(serverTimer + Math.PI) + Math.sin(serverTimer * 1.5D)) / 1.90596D;
        double sy = (Math.sin(serverTimer) + Math.sin(serverTimer * 1.5D)) / 1.90596D;

        double serverSway = 0.0625D * 0.25D;

        GlStateManager.pushMatrix();
        GlStateManager.translate(sx * serverSway, 0, sy * serverSway);
        ResourceManagerSpace.lpw2.renderPart("Server1");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(-sy * serverSway, 0, sx * serverSway);
        ResourceManagerSpace.lpw2.renderPart("Server2");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(sy * serverSway, 0, -sx * serverSway);
        ResourceManagerSpace.lpw2.renderPart("Server3");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(-sx * serverSway, 0, -sy * serverSway);
        ResourceManagerSpace.lpw2.renderPart("Server4");
        GlStateManager.popMatrix();

        double errorTimer = ((time + partialTicks) / 3D);

        GlStateManager.pushMatrix();
        GlStateManager.translate(sy * serverSway, 0, sx * serverSway);

        ResourceManagerSpace.lpw2.renderPart("Monitor");

		/*Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManager.lpw2_term_tex);
		ResourceManager.lpw2.renderPart("Screen");*/

        Minecraft.getMinecraft().getTextureManager().bindTexture(ResourceManagerSpace.lpw2_error_tex);

        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0, (BobMathUtil.sps(errorTimer) + errorTimer / 2D) % 1, 0);
        ResourceManagerSpace.lpw2.renderPart("Screen");
        GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);

        GlStateManager.popMatrix();

        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.popMatrix();
    }

    @Override
    public Item getItemForRenderer() {
        return Item.getItemFromBlock(ModBlocks.machine_lpw2);
    }

    @Override
    public ItemRenderBase getRenderer(Item item) {
        return new ItemRenderBase() {
            public void renderInventory() {
                GlStateManager.translate(1, -1, 0);
                GlStateManager.scale(1.6, 1.6, 1.6);
            }

            public void renderCommon() {
                GlStateManager.scale(0.5, 0.5, 0.5);
                GlStateManager.shadeModel(GL11.GL_SMOOTH);
                bindTexture(ResourceManagerSpace.lpw2_tex);
                ResourceManagerSpace.lpw2.renderAll();
                GlStateManager.shadeModel(GL11.GL_FLAT);
            }
        };
    }

    public static void renderMainAssembly(double sway, double h, double v, double piston, double rotor, double turbine) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, -sway * 0.125);
        ResourceManagerSpace.lpw2.renderPart("Center");

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 3.5, 0);

        GlStateManager.pushMatrix();
        GL11.glRotated(rotor * 360, 0, 0, -1);
        GlStateManager.translate(0, -3.5, 0);
        ResourceManagerSpace.lpw2.renderPart("Rotor");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GL11.glRotated(turbine * 360, 0, 0, 1);
        GlStateManager.translate(0, -3.5, 0);
        ResourceManagerSpace.lpw2.renderPart("TurbineFront");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GL11.glRotated(turbine * 360, 0, 0, -1);
        GlStateManager.translate(0, -3.5, 0);
        ResourceManagerSpace.lpw2.renderPart("TurbineBack");
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, piston * 0.375D + 0.375D);
        ResourceManagerSpace.lpw2.renderPart("Piston");
        GlStateManager.popMatrix();

        renderBell(h, v);
        GlStateManager.popMatrix();

        renderShroud(h, v);
    }

    public static void renderBell(double h, double v) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 3.5, 2.75);
        double magnitude = 2D;
        GL11.glRotated(v * magnitude, 0, 1, 0);
        GL11.glRotated(h * magnitude, 1, 0, 0);
        GlStateManager.translate(0, -3.5, -2.75);
        ResourceManagerSpace.lpw2.renderPart("Engine");
        GlStateManager.popMatrix();
    }

    public static void renderShroud(double h, double v) {

        double magnitude = 0.125D;
        double rotation = 5D;
        double offset = 10D;

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, -h * magnitude, 0);
        ResourceManagerSpace.lpw2.renderPart("ShroudH");

        renderFlap(90 + 22.5D, rotation * v + offset);
        renderFlap(90 - 22.5D, rotation * v + offset);
        renderFlap(270 + 22.5D, rotation * -v + offset);
        renderFlap(270 - 22.5D, rotation * -v + offset);

        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(v * magnitude, 0, 0);
        ResourceManagerSpace.lpw2.renderPart("ShroudV");

        renderFlap(22.5D, rotation * h + offset);
        renderFlap(-22.5D, rotation * h + offset);
        renderFlap(180 + 22.5D, rotation * -h + offset);
        renderFlap(180 - 22.5D, rotation * -h + offset);

        GlStateManager.popMatrix();

        double length = 0.6875D;

        GlStateManager.pushMatrix();
        GlStateManager.translate(-2.625D, 0, 0);
        GL11.glScaled((length + v * magnitude) / length, 1, 1);
        GlStateManager.translate(2.625D, 0, 0);
        ResourceManagerSpace.lpw2.renderPart("SuspensionLeft");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(2.625D, 0, 0);
        GL11.glScaled((length - v * magnitude) / length, 1, 1);
        GlStateManager.translate(-2.625D, 0, 0);
        ResourceManagerSpace.lpw2.renderPart("SuspensionRight");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 6.125D, 0);
        GL11.glScaled(1, (length + h * magnitude) / length, 1);
        GlStateManager.translate(0, -6.125D, 0);
        ResourceManagerSpace.lpw2.renderPart("SuspensionTop");
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0.875D, 0);
        GL11.glScaled(1, (length - h * magnitude) / length, 1);
        GlStateManager.translate(0, -0.875D, 0);
        ResourceManagerSpace.lpw2.renderPart("SuspensionBottom");
        GlStateManager.popMatrix();
    }

    public static void renderFlap(double position, double rotation) {
        GlStateManager.pushMatrix();

        GlStateManager.translate(0, 3.5D, 0);
        GL11.glRotated(position, 0, 0, 1);
        GlStateManager.translate(0, -3.5D, 0);

        GlStateManager.translate(0, 6.96875D, 8.5D);
        GL11.glRotated(rotation, 1, 0, 0);
        GlStateManager.translate(0, -6.96875D, -8.5D);

        ResourceManagerSpace.lpw2.renderPart("Flap");
        GlStateManager.popMatrix();
    }

}
