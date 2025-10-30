package com.hbmspace.main;

import com.hbm.hfr.render.loader.HFRWavefrontObject;
import com.hbm.lib.RefStrings;
import com.hbm.render.amlfrom1710.IModelCustom;
import net.minecraft.util.ResourceLocation;

public class ResourceManagerSpace {

    public static final IModelCustom lpw2 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/lpw2.obj")).asVBO();
    public static final IModelCustom htr3 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/htr3.obj")).asVBO();
    public static final IModelCustom htrf4 = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/htrf4.obj")).asVBO();
    public static final IModelCustom docking_port = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/docking_port.obj")).asVBO();
    public static final IModelCustom xenon_thruster = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/machines/xenon_thruster.obj")).asVBO();
    public static final IModelCustom drop_pod = new HFRWavefrontObject(new ResourceLocation(RefStrings.MODID, "models/missile_parts/rp_drop_pod.obj")).asVBO();
    public static final ResourceLocation lpw2_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/lpw2.png");
    public static final ResourceLocation lpw2_error_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/lpw2_term_error.png");//Xenon
    public static final ResourceLocation xenon_thruster_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/xenon_thruster.png");
    public static final ResourceLocation xenon_exhaust_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/xenon_trail.png");
    public static final ResourceLocation docking_port_tex = new ResourceLocation(RefStrings.MODID, "textures/models/machines/docking_port.png");
    public static final ResourceLocation drop_pod_tex = new ResourceLocation(RefStrings.MODID, "textures/models/missile_parts/warheads/rp_drop_pod.png");

}
