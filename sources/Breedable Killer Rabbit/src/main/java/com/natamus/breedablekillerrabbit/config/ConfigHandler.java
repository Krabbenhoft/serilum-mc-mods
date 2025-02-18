/*
 * This is the latest source code of Breedable Killer Rabbit.
 * Minecraft version: 1.19.2, mod version: 1.9.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.breedablekillerrabbit.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {		
		public final ForgeConfigSpec.ConfigValue<Double> chanceBabyRabbitIsKiller;
		public final ForgeConfigSpec.ConfigValue<Boolean> removeKillerRabbitNameTag;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			chanceBabyRabbitIsKiller = builder
					.comment("The chance that a baby rabbit is of the killer variant.")
					.defineInRange("chanceBabyRabbitIsKiller", 0.05, 0, 1.0);
			removeKillerRabbitNameTag = builder
					.comment("Remove the name 'The Killer Bunny' from the baby killer rabbit.")
					.define("removeKillerRabbitNameTag", true);
			
			builder.pop();
		}
	}
}