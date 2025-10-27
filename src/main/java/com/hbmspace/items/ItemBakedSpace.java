package com.hbmspace.items;

import com.hbm.items.ItemBakedBase;
import com.hbm.items.ModItems;

public class ItemBakedSpace extends ItemBakedBase {

    public ItemBakedSpace(String s, String texturePath){
        super(s, texturePath);
        ModItems.ALL_ITEMS.remove(this); // it doesn't really do much but I'd keep the original list without redundant shit
        ModItemsSpace.ALL_ITEMS.add(this);
    }

    public ItemBakedSpace(String s){
        super(s);
        ModItems.ALL_ITEMS.remove(this);
        ModItemsSpace.ALL_ITEMS.add(this);
    }
}
