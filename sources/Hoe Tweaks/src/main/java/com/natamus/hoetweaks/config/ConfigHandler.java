/*
 * This is the latest source code of Hoe Tweaks.
 * Minecraft version: 1.19.2, mod version: 1.6.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.hoetweaks.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {
		public final ForgeConfigSpec.ConfigValue<Double> cropBlockBreakSpeedModifier;
		public final ForgeConfigSpec.ConfigValue<Boolean> mustCrouchToHaveBiggerHoeRange;
		
		public final ForgeConfigSpec.ConfigValue<Integer> woodenTierHoeRange;
		public final ForgeConfigSpec.ConfigValue<Integer> stoneTierHoeRange;
		public final ForgeConfigSpec.ConfigValue<Integer> goldTierHoeRange;
		public final ForgeConfigSpec.ConfigValue<Integer> ironTierHoeRange;
		public final ForgeConfigSpec.ConfigValue<Integer> diamondTierHoeRange;
		public final ForgeConfigSpec.ConfigValue<Integer> netheriteTierHoeRange;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			cropBlockBreakSpeedModifier = builder
					.comment("How much quicker a cropblock (pumpkin/melon) is broken than by default.")
					.defineInRange("cropBlockBreakSpeedModifier", 8.0, 0.0, 20.0);
			mustCrouchToHaveBiggerHoeRange = builder
					.comment("Whether the bigger hoe range should only be used if the player is crouching when right-clicking the center block.")
					.define("mustCrouchToHaveBiggerHoeRange", true);
			
			woodenTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 0 = 1x1")
					.defineInRange("woodenTierHoeRange", 0, 0, 32);
			stoneTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 1 = 3x3")
					.defineInRange("stoneTierHoeRange", 1, 0, 32);
			goldTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 2 = 5x5")
					.defineInRange("goldTierHoeRange", 2, 0, 32);
			ironTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 2 = 5x5")
					.defineInRange("ironTierHoeRange", 2, 0, 32);
			diamondTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 3 = 7x7")
					.defineInRange("diamondTierHoeRange", 3, 0, 32);
			netheriteTierHoeRange = builder
					.comment("The wooden hoe till range (default while crouching). 4 = 9x9")
					.defineInRange("netheriteTierHoeRange", 4, 0, 32);
			
			builder.pop();
		}
	}
}