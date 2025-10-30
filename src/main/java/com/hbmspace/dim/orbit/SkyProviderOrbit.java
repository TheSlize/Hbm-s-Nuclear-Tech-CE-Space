package com.hbmspace.dim.orbit;

import com.hbmspace.dim.CelestialBody;
import com.hbmspace.dim.SkyProviderCelestial;
import com.hbmspace.dim.SolarSystem;
import com.hbmspace.dim.SolarSystem.AstroMetric;
import com.hbmspace.dim.orbit.OrbitalStation.StationState;
import com.hbm.lib.Library;
import com.hbm.util.BobMathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class SkyProviderOrbit extends SkyProviderCelestial {

	private static CelestialBody lastBody;

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		WorldProviderOrbit provider = (WorldProviderOrbit) world.provider;
		OrbitalStation station = OrbitalStation.clientStation;
		double progress = station.getTransferProgress(partialTicks);
		float orbitalTilt = 80;

		GlStateManager.depthMask(false);
		GlStateManager.disableFog();
		GlStateManager.disableAlpha();
		GlStateManager.enableTexture2D();

		GlStateManager.enableBlend();
		RenderHelper.disableStandardItemLighting();

		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

		float celestialAngle = getCelestialAngle(world, partialTicks, station);
		float celestialPhase = (1 - (celestialAngle + 0.5F) % 1) * 2 - 1;

		float starBrightness = world.getStarBrightness(partialTicks);

		renderStars(partialTicks, world, mc, starBrightness, celestialAngle, orbitalTilt);

		GlStateManager.pushMatrix();
		{
			GlStateManager.rotate(orbitalTilt, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(celestialAngle * 360.0F, 1.0F, 0.0F, 0.0F);

			// digma balls
			renderDigamma(partialTicks, world, mc, celestialAngle);

			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

			double sunSize = SolarSystem.calculateSunSize(station.orbiting);
			if(station.state != StationState.ORBIT) {
				double sunTargetSize = SolarSystem.calculateSunSize(station.target);
				sunSize = BobMathUtil.lerp(progress, sunSize, sunTargetSize);
			}
			double coronaSize = sunSize * (3 - Library.smoothstep(Math.abs(celestialPhase), 0.7, 0.8));

			renderSun(partialTicks, world, mc, station.orbiting.getStar(), sunSize, coronaSize, 1, 0);

			CelestialBody orbiting = station.orbiting;

			List<AstroMetric> metrics;
			if(station.state == StationState.ORBIT) {
				double altitude = provider.getOrbitalAltitude(station.orbiting);
				metrics = SolarSystem.calculateMetricsFromSatellite(world, partialTicks, station.orbiting, altitude);
			} else {
				double fromAlt = provider.getOrbitalAltitude(station.orbiting);
				double toAlt = provider.getOrbitalAltitude(station.target);
				metrics = SolarSystem.calculateMetricsBetweenSatelliteOrbits(world, partialTicks, station.orbiting, station.target, fromAlt, toAlt, progress);

				if(progress > 0.5) orbiting = station.target;
			}

			renderCelestials(partialTicks, world, mc, metrics, celestialAngle, null, new Vec3d(0, 0, 0), 1, 1, orbiting, 160);
		}
		GlStateManager.popMatrix();

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableFog();

		GlStateManager.enableTexture2D();
		GlStateManager.depthMask(true);
	}

	// All angles within are normalized to -180/180
	private float getCelestialAngle(WorldClient world, float partialTicks, OrbitalStation station) {
		float celestialAngle = world.getCelestialAngle(partialTicks);
		if(station.state == StationState.ORBIT) return celestialAngle;

		celestialAngle = celestialAngle * 360.0F - 180.0F;

		if(station.state != StationState.ARRIVING) lastBody = station.orbiting;

		double progress = station.getUnscaledProgress(partialTicks);
		float travelAngle = -(float)SolarSystem.calculateSingleAngle(world, partialTicks, lastBody, station.target);
		travelAngle = MathHelper.wrapDegrees(travelAngle + 90.0F);

		if(station.state == StationState.TRANSFER) {
			return (travelAngle + 180.0F) / 360.0F;
		} else if(station.state == StationState.LEAVING) {
			return ((float)BobMathUtil.clerp(progress, celestialAngle, travelAngle) + 180.0F) / 360.0F;
		} else {
			return ((float)BobMathUtil.clerp(progress, travelAngle, celestialAngle) + 180.0F) / 360.0F;
		}
	}
	
}
