package com.hbmspace.items;

import com.hbm.items.IDynamicModels;
import com.hbm.items.ItemEnumMulti;
import com.hbm.items.ModItems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class ItemEnumMultiSpace extends ItemEnumMulti implements IDynamicModelsSpace {
    public ItemEnumMultiSpace(String registryName, Class<? extends Enum<?>> theEnum, boolean multiName, boolean multiTexture) {
        super(registryName, theEnum, multiName, multiTexture);
        ModItems.ALL_ITEMS.remove(this);
        ModItemsSpace.ALL_ITEMS.add(this);
        IDynamicModels.INSTANCES.remove(this);
        IDynamicModelsSpace.INSTANCES.add(this);
    }
    public ItemEnumMultiSpace(String registryName, Class<? extends Enum<?>> theEnum, boolean multiName, String texture) {
        super(registryName, theEnum, multiName, texture);
        ModItems.ALL_ITEMS.remove(this);
        ModItemsSpace.ALL_ITEMS.add(this);
        IDynamicModels.INSTANCES.remove(this);
        IDynamicModelsSpace.INSTANCES.add(this);
    }
}
