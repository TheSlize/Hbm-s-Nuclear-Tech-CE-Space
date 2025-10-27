package com.hbmspace.main;

import com.hbm.hfr.render.loader.HFRWavefrontObject;
import com.hbm.render.amlfrom1710.IModelCustom;
import net.minecraft.util.ResourceLocation;

public class ResourceManagerSpace {

    public static final IModelCustom lpw2 = new HFRWavefrontObject(new ResourceLocation("hbm", "models/machines/lpw2.obj")).asVBO();
    public static final ResourceLocation lpw2_tex = new ResourceLocation("hbm", "textures/models/machines/lpw2.png");
    public static final ResourceLocation lpw2_error_tex = new ResourceLocation("hbm", "textures/models/machines/lpw2_term_error.png");
}
