package com.hbmspace.render.misc;

import com.hbm.items.ModItems;
import com.hbm.items.weapon.ItemMissile;
import com.hbm.main.ResourceManager;
import com.hbm.render.amlfrom1710.IModelCustom;
import com.hbmspace.render.entity.rocket.part.RenderDropPod;
import com.hbmspace.render.entity.rocket.part.RenderRocketPart;
import com.hbm.render.misc.MissilePart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;

public class RocketPart extends MissilePart {
    public static HashMap<Integer, MissilePart> parts = new HashMap<>();

    public RenderRocketPart renderer;

    private RocketPart(Item item, ItemMissile.PartType type, double height, double guiheight, IModelCustom model, ResourceLocation texture) {
        super(item, type, height, guiheight, model, texture);
    }

    private RocketPart withRenderer(RenderRocketPart renderer) {
        this.renderer = renderer;
        return this;
    }

    public static void registerAllParts() {
        parts.clear();

        registerPart(ModItems.rp_capsule_20, ItemMissile.PartType.WARHEAD, 3.5, 2.25, ResourceManager.soyuz_lander_neo, ResourceManager.module_lander_tex);
        registerPart(ModItems.rp_station_core_20, ItemMissile.PartType.WARHEAD, 7, 5, ResourceManager.mp_w_fairing, ResourceManager.mp_w_fairing_tex);
        registerPart(ModItems.rp_pod_20, ItemMissile.PartType.WARHEAD, 3.0, 2.25, ResourceManager.drop_pod, ResourceManager.drop_pod_tex).withRenderer(new RenderDropPod());
    }

    public static RocketPart registerPart(Item item, ItemMissile.PartType type, double height, double guiheight, IModelCustom model, ResourceLocation texture) {
        RocketPart part = new RocketPart(item, type, height, guiheight, model, texture);
        parts.put(item.hashCode(), part);
        return part;
    }

    public static RocketPart getPart(ItemStack stack) {
        if(stack == null)
            return null;

        return getPart(stack.getItem());
    }
    public static RocketPart getPart(Item item) {
        if(item == null)
            return null;

        return (RocketPart) parts.get(item.hashCode());
    }

    public static RocketPart getPart(int id) {
        if(id <= 0)
            return null;

        return getPart(Item.getItemById(id));
    }

    public static int getId(MissilePart m) {
        if(m == null) return 0;
        return Item.getIdFromItem(m.part);
    }
}
