/*
 * This is the latest source code of The Vanilla Experience.
 * Minecraft version: 1.17.1, mod version: 1.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.thevanillaexperience.mods.cryingghasts.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CryingGhastsConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {		
		public final ForgeConfigSpec.ConfigValue<Integer> maxDistanceToGhastBlocks;
		public final ForgeConfigSpec.ConfigValue<Integer> ghastTearDelayTicks;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			maxDistanceToGhastBlocks = builder
					.comment("The maximum distance in blocks the player can be away from the ghasts in order for them to start dropping tears periodically.")
					.defineInRange("maxDistanceToGhastBlocks", 32, 1, 256);
			ghastTearDelayTicks = builder
					.comment("The delay in between ghasts dropping a tear in ticks (1 second = 20 ticks).")
					.defineInRange("ghastTearDelayTicks", 1200, 1, 72000);
			
			builder.pop();
		}
	}
}