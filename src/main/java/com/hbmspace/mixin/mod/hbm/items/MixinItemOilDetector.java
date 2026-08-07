package com.hbmspace.mixin.mod.hbm.items;

import com.hbm.items.tool.ItemOilDetector;
import com.hbm.lib.HBMSoundHandler;
import com.hbm.packet.PacketDispatcher;
import com.hbm.packet.toclient.PlayerInformPacketLegacy;
import com.hbmspace.blocks.generic.BlockOreFluid;
import com.hbmspace.util.OilReserveUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemOilDetector.class)
public abstract class MixinItemOilDetector extends Item {

    /** Same as NTM: 25 block radius, 50 gaussian samples, scan from 15 above the player down to y=1. */
    @Unique private static final int space$RANGE = 25;
    @Unique private static final int space$SAMPLES = 50;

    @Overwrite
    public @NotNull ActionResult<ItemStack> onItemRightClick(World world, @NotNull EntityPlayer player, @NotNull EnumHand hand) {

        if (!world.isRemote) {
            int x = MathHelper.floor(player.posX);
            int y = MathHelper.floor(player.posY);
            int z = MathHelper.floor(player.posZ);

            Block directBedrock = OilReserveUtil.findBedrockReserve(world, x, z);
            Block direct = OilReserveUtil.findReserveInColumn(world, x, y, z);

            Block nearby = null;
            Block nearbyBedrock = null;

            if (direct == null && directBedrock == null) {
                for (int i = 0; i < space$SAMPLES && nearby == null && nearbyBedrock == null; i++) {
                    int lx = (int) MathHelper.clamp(world.rand.nextGaussian() * space$RANGE / 2F, -space$RANGE, space$RANGE);
                    int lz = (int) MathHelper.clamp(world.rand.nextGaussian() * space$RANGE / 2F, -space$RANGE, space$RANGE);

                    nearby = OilReserveUtil.findReserveInColumn(world, x + lx, y, z + lz);
                    nearbyBedrock = OilReserveUtil.findBedrockReserve(world, x + lx, z + lz);
                }
            }

            if (directBedrock != null) {
                space$inform(player, ".bullseyeBedrock", TextFormatting.DARK_GREEN);
            } else if (direct != null) {
                space$inform(player, ".bullseye" + space$reserveType(direct), TextFormatting.GREEN);
            } else if (nearbyBedrock != null) {
                space$inform(player, ".detectedBedrock", TextFormatting.GOLD);
            } else if (nearby != null) {
                space$inform(player, ".detected" + space$reserveType(nearby), TextFormatting.YELLOW);
            } else {
                space$inform(player, ".noOil", TextFormatting.RED);
            }
        }

        world.playSound(null, player.posX, player.posY, player.posZ, HBMSoundHandler.techBleep, SoundCategory.PLAYERS, 1.0F, 1.0F);

        player.swingArm(hand);

        return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Unique
    private void space$inform(EntityPlayer player, String suffix, TextFormatting color) {
        TextComponentTranslation text = new TextComponentTranslation(this.getTranslationKey() + suffix);
        text.setStyle(new Style().setColor(color));
        PacketDispatcher.wrapper.sendTo(new PlayerInformPacketLegacy(text, 8), (EntityPlayerMP) player);
    }

    @Unique
    private String space$reserveType(Block reserve) {
        return reserve instanceof BlockOreFluid ? ((BlockOreFluid) reserve).getUnlocalizedReserveType() : "";
    }

}
