/*
 * This is the latest source code of Pet Names.
 * Minecraft version: 1.19.2, mod version: 1.9.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.petnames.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigHandler {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final General GENERAL = new General(BUILDER);
	public static final ForgeConfigSpec spec = BUILDER.build();

	public static class General {
		/*
	@Config.Comment("Use the list of female names when naming pets.")
	public static boolean _useFemaleNames = true;
	@Config.Comment("Use the list of male names when naming pets.")
	public static boolean _useMaleNames = true;
	
	@Config.Comment("Give baby wolves a name.")
	public static boolean nameWolves = true;
	@Config.Comment("Give kittens a name.")
	public static boolean nameCats = true;
	@Config.Comment("Give baby horses a name.")
	public static boolean nameHorses = true;
	@Config.Comment("Give baby donkeys a name.")
	public static boolean nameDonkeys = true;
	@Config.Comment("Give baby mules a name.")
	public static boolean nameMules = true;
	@Config.Comment("Give baby llamas a name.")
	public static boolean nameLlamas = true;
		 */
		
		public final ForgeConfigSpec.ConfigValue<Boolean> _useFemaleNames;
		public final ForgeConfigSpec.ConfigValue<Boolean> _useMaleNames;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameWolves;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameCats;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameHorses;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameDonkeys;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameMules;
		public final ForgeConfigSpec.ConfigValue<Boolean> nameLlamas;

		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			_useFemaleNames = builder
					.comment("Use the list of female names when naming pets.")
					.define("_useFemaleNames", true);
			_useMaleNames = builder
					.comment("Use the list of male names when naming pets.")
					.define("_useMaleNames", true);
			nameWolves = builder
					.comment("Give baby wolves a name.")
					.define("nameWolves", true);
			nameCats = builder
					.comment("Give kittens a name.")
					.define("nameCats", true);
			nameHorses = builder
					.comment("Give baby horses a name.")
					.define("nameHorses", true);
			nameDonkeys = builder
					.comment("Give baby donkeys a name.")
					.define("nameDonkeys", true);
			nameMules = builder
					.comment("Give baby mules a name.")
					.define("nameMules", true);
			nameLlamas = builder
					.comment("Give baby llamas a name.")
					.define("nameLlamas", true);
			
			builder.pop();
		}
	}
}