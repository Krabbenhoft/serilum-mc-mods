/*
 * This is the latest source code of Villager Names.
 * Minecraft version: 1.19.2, mod version: 4.0.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.villagernames;

import java.io.IOException;

import com.natamus.collective_fabric.check.RegisterMod;
import com.natamus.villagernames.config.ConfigHandler;
import com.natamus.villagernames.events.VillagerEvent;
import com.natamus.villagernames.util.Names;
import com.natamus.villagernames.util.Reference;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
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
    	try {
			Names.setCustomNames();
		} catch (IOException e) { }
		
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			VillagerEvent.onSpawn(world, entity);
		});
		
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			return VillagerEvent.onVillagerInteract(player, world, hand, entity, hitResult);
		});
	}
}
