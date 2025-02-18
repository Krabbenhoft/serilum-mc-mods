/*
 * This is the latest source code of Random Mob Effects.
 * Minecraft version: 1.19.2, mod version: 1.9.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.randommobeffects.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {
		public final ForgeConfigSpec.ConfigValue<Integer> potionEffectLevel;
		public final ForgeConfigSpec.ConfigValue<Boolean> hideEffectParticles;
		public final ForgeConfigSpec.ConfigValue<Boolean> disableCreepers;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			potionEffectLevel = builder
					.comment("The default level of the effects the mod applies, by default 1.")
					.defineInRange("potionEffectLevel", 1, 1, 50);
			hideEffectParticles = builder
					.comment("When enabled, hides the particles from the mobs with an effect.")
					.define("hideEffectParticles", false);
			disableCreepers = builder
					.comment("Creepers can create infinite lingering potion effects which is probably not what you want. When enabled, the mod doesn't give creepers a random effect.")
					.define("disableCreepers", true);
			
			builder.pop();
		}
	}
}