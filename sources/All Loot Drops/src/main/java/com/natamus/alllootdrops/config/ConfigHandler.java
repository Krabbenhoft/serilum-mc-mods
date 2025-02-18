/*
 * This is the latest source code of All Loot Drops.
 * Minecraft version: 1.19.2, mod version: 2.5.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.alllootdrops.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {
		public final ForgeConfigSpec.ConfigValue<Integer> lootQuantity;
		public final ForgeConfigSpec.ConfigValue<Boolean> lootingEnchantAffectsQuantity;
		public final ForgeConfigSpec.ConfigValue<Double> lootingEnchantExtraQuantityChance;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			lootQuantity = builder
					.comment("Determines the amount of loot dropped by each mob.")
					.defineInRange("lootQuantity", 1, 1, 128);
			lootingEnchantAffectsQuantity = builder
					.comment("If enabled, the looting enchant has a chance to increase the quantity of loot dropped. Per level a roll is done with the chance from 'lootingEnchantExtraQuantityChancePerLevel'. If the roll succeeds, 1 is added to the loot amount.")
					.define("lootingEnchantAffectsQuantity", true);
			lootingEnchantExtraQuantityChance = builder
					.comment("The chance a roll succeeds in adding 1 to the total loot amount.")
					.defineInRange("lootingEnchantExtraQuantityChance", 0.5, 0, 1.0);
			
			builder.pop();
		}
	}
}