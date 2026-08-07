package com.hbmspace.handler.registires;

import com.hbm.blocks.ModBlocks;
import com.hbm.main.MainRegistry;
import com.hbmspace.blocks.generic.BlockOreFluid;
import com.hbmspace.enums.EnumAddonTypes;
import com.hbmspace.main.SpaceMain;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static com.hbmspace.blocks.ModBlocksSpace.ore_oil_empty;

public class ModBlocksReplaceHandler {

    public static void initReplacings(RegistryEvent.Register<Block> event) {
        BlockOreFluid ore_oil_override = (BlockOreFluid) new BlockOreFluid("ore_oil", ore_oil_empty, BlockOreFluid.ReserveType.OIL).setCreativeTab(MainRegistry.blockTab).setHardness(5.0F).setResistance(10.0F);
        BlockOreFluid ore_bedrock_oil_override = (BlockOreFluid) new BlockOreFluid("ore_bedrock_oil", null, BlockOreFluid.ReserveType.OIL).setCreativeTab(MainRegistry.blockTab).setBlockUnbreakable().setResistance(1_000_000);
        // NTM's own drained oil block still exists in worlds that were played without this addon,
        // and is what the overworld reserve drains into, so it has to count as part of the reservoir.
        ore_oil_override.withEarthEmpty(ModBlocks.ore_oil_empty);
        // keep NTM's own appearance on Earth; other bodies still use the planet stone composite
        ore_oil_override.withEarthTexture(new ResourceLocation("hbm", "blocks/ore_oil"));
        ore_bedrock_oil_override.withEarthTexture(new ResourceLocation("hbm", "blocks/ore_bedrock_oil"));
        // Th3_Sl1ze: lmao, EnumAddonTypes was useful not only for enums..
        EnumAddonTypes.setInstanceField(IForgeRegistryEntry.Impl.class, "registryName", ore_oil_override, null);
        EnumAddonTypes.setInstanceField(IForgeRegistryEntry.Impl.class, "registryName", ore_bedrock_oil_override, null);

        ore_oil_override.setRegistryName("hbm", "ore_oil");
        ore_bedrock_oil_override.setRegistryName("hbm", "ore_bedrock_oil");
        event.getRegistry().register(ore_oil_override);
        event.getRegistry().register(ore_bedrock_oil_override);

        EnumAddonTypes.setStaticField(ModBlocks.class, "ore_oil", ore_oil_override);
        EnumAddonTypes.setStaticField(ModBlocks.class, "ore_bedrock_oil", ore_bedrock_oil_override);
    }

    /**
     * Validates that the block/item replacements actually took effect. A silent failure here turns
     * into oil that cannot be detected, drilled or saved, so it is worth shouting about.
     */
    public static void verifyReplacements() {
        check("hbm:ore_oil", ModBlocks.ore_oil);
        check("hbm:ore_bedrock_oil", ModBlocks.ore_bedrock_oil);

        if (!(ModBlocks.ore_oil instanceof BlockOreFluid))
            warn("ModBlocks.ore_oil is not a BlockOreFluid (" + ModBlocks.ore_oil.getClass().getName() + ")");
        if (!(ModBlocks.ore_bedrock_oil instanceof BlockOreFluid))
            warn("ModBlocks.ore_bedrock_oil is not a BlockOreFluid (" + ModBlocks.ore_bedrock_oil.getClass().getName() + ")");
        if (BlockOreFluid.getFullBlock(ModBlocks.ore_oil_empty) != ModBlocks.ore_oil)
            warn("hbm:ore_oil_empty is not mapped back to hbm:ore_oil; drills would treat drained NTM oil as plain stone");
        if (BlockOreFluid.getFullBlock(ore_oil_empty) != ModBlocks.ore_oil)
            warn("hbmspace:ore_oil_empty is not mapped back to hbm:ore_oil");

        checkItem("hbm:ore_oil", ModBlocks.ore_oil);
        checkItem("hbm:ore_bedrock_oil", ModBlocks.ore_bedrock_oil);
    }

    private static void check(String name, Block expected) {
        Block registered = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(name));
        if (registered != expected)
            warn("block " + name + " resolves to " + registered + " but ModBlocks holds " + expected);
    }

    private static void checkItem(String name, Block block) {
        Item fromBlock = Item.getItemFromBlock(block);
        Item registered = ForgeRegistries.ITEMS.getValue(new ResourceLocation(name));
        if (fromBlock != registered)
            warn("item " + name + " (" + registered + ") is not the item of the replaced block (" + fromBlock + ")");
    }

    private static void warn(String message) {
        SpaceMain.logger.error("[oil replacement] {}", message);
    }
}
