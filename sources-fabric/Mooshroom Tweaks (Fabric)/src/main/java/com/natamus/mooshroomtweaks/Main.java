/*
 * This is the latest source code of Mooshroom Tweaks.
 * Minecraft version: 1.19.2, mod version: 2.1.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.mooshroomtweaks;

import com.natamus.collective_fabric.check.RegisterMod;
import com.natamus.mooshroomtweaks.config.ConfigHandler;
import com.natamus.mooshroomtweaks.events.MooshroomEvent;
import com.natamus.mooshroomtweaks.util.Reference;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() { 
		ConfigHandler.setup();

		registerEvents();
		
		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}
	
	private void registerEvents() {
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			MooshroomEvent.onEntityJoin(world, entity);
		});
	}
}
