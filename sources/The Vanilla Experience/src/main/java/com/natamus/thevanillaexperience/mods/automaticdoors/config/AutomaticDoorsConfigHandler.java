/*
 * This is the latest source code of The Vanilla Experience.
 * Minecraft version: 1.17.1, mod version: 1.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.thevanillaexperience.mods.automaticdoors.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class AutomaticDoorsConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {
		public final ForgeConfigSpec.ConfigValue<Integer> doorOpenTime;
		public final ForgeConfigSpec.ConfigValue<Boolean> shouldOpenIronDoors;
		public final ForgeConfigSpec.ConfigValue<Boolean> preventOpeningOnSneak;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			doorOpenTime = builder
					.comment("The time in ms the door should stay open.")
					.defineInRange("doorOpenTime", 2500, 0, 10000);
			shouldOpenIronDoors = builder
					.comment("When enabled, iron doors will also be opened automatically.")
					.define("shouldOpenIronDoors", true);
			preventOpeningOnSneak = builder
					.comment("When enabled, doors won't be opened automatically when the player is sneaking.")
					.define("preventOpeningOnSneak", true);
			
			builder.pop();
		}
	}
}