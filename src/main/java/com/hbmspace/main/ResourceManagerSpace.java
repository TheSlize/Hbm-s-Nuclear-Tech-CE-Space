package com.hbmspace.main;

import com.hbm.hfr.render.loader.HFRWavefrontObject;
import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.IModelCustom;
import net.minecraft.util.ResourceLocation;

public class ResourceManagerSpace {

    public static final IModelCustom lpw2 = new HFRWavefrontObject(new ResourceLocation("hbm", "models/machines/lpw2.obj")).asVBO();
    public static final IModelCustom htr3 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/htr3.obj")).asVBO();
    public static final IModelCustom htrf4 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/htrf4.obj")).asVBO();
    public static final IModelCustom xenon_thruster = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/xenon_thruster.obj")).asVBO();
    public static final ResourceLocation lpw2_tex = new ResourceLocation("hbm", "textures/models/machines/lpw2.png");
    public static final ResourceLocation lpw2_error_tex = new ResourceLocation("hbm", "textures/models/machines/lpw2_term_error.png");//Xenon
    public static final ResourceLocation xenon_thruster_tex = new ResourceLocation(com.hbm.lib.RefStrings.MODID, "textures/models/machines/xenon_thruster.png");
    public static final ResourceLocation xenon_exhaust_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/xenon_trail.png");
}
