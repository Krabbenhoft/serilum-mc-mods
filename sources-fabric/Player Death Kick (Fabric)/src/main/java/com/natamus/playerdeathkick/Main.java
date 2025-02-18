/*
 * This is the latest source code of Player Death Kick.
 * Minecraft version: 1.19.2, mod version: 2.1.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.playerdeathkick;

import com.natamus.collective_fabric.check.RegisterMod;
import com.natamus.playerdeathkick.config.ConfigHandler;
import com.natamus.playerdeathkick.events.DeathEvent;
import com.natamus.playerdeathkick.util.Reference;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() { 
		ConfigHandler.setup();

		registerEvents();
		
		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}
	
	private void registerEvents() {
		ServerPlayerEvents.ALLOW_DEATH.register((ServerPlayer player, DamageSource damageSource, float damageAmount) -> {
			DeathEvent.onDeathEvent(player, damageSource, damageAmount);
			return true;
		});
	}
}
