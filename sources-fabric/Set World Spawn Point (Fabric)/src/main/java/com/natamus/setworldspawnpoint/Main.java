/*
 * This is the latest source code of Set World Spawn Point.
 * Minecraft version: 1.19.2, mod version: 2.5.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.setworldspawnpoint;

import com.natamus.collective_fabric.check.RegisterMod;
import com.natamus.collective_fabric.fabric.callbacks.CollectiveMinecraftServerEvents;
import com.natamus.setworldspawnpoint.config.ConfigHandler;
import com.natamus.setworldspawnpoint.events.WorldSpawnEvent;
import com.natamus.setworldspawnpoint.util.Reference;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.ServerLevelData;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() { 
		ConfigHandler.setup();

		registerEvents();
		
		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}
	
	private void registerEvents() {
		CollectiveMinecraftServerEvents.WORLD_SET_SPAWN.register((ServerLevel serverLevel, ServerLevelData serverLevelData) -> {
			WorldSpawnEvent.onWorldLoad(serverLevel, serverLevelData);
		});
		
		ServerPlayerEvents.AFTER_RESPAWN.register((ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean alive) -> {
			WorldSpawnEvent.onPlayerRespawn(oldPlayer, newPlayer, alive);
		});
		
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			WorldSpawnEvent.onEntityJoin(world, entity);
		});
	}
}
