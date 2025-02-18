/*
 * This is the latest source code of Village Spawn Point.
 * Minecraft version: 1.19.2, mod version: 2.5.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.villagespawnpoint.events;

import com.natamus.collective_fabric.functions.BlockPosFunctions;
import com.natamus.collective_fabric.functions.FeatureFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.storage.ServerLevelData;

public class VillageSpawnEvent {
	public static void onWorldLoad(ServerLevel serverworld, ServerLevelData serverLevelData) {
		WorldGenSettings generatorsettings = serverworld.getServer().getWorldData().worldGenSettings();
		
		if (!generatorsettings.generateStructures()) {
			return;
		}

		System.out.println("[Village Spawn Point] Finding the nearest village. This might take a few seconds.");
		BlockPos spawnpos = BlockPosFunctions.getCenterNearbyVillage(serverworld);
		if (spawnpos == null) {
			return;
		}

		System.out.println("[Village Spawn Point] Village found! The world will now generate.");
		
		serverworld.setDefaultSpawnPos(spawnpos, 1.0f);
		
		if (generatorsettings.generateBonusChest()) {
			FeatureFunctions.placeBonusChest(serverworld, spawnpos);
		}
	}
}
