/*
 * This is the latest source code of Configurable Furnace Burn Time.
 * Minecraft version: 1.19.2, mod version: 1.3.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.configurablefurnaceburntime.events;

import com.natamus.configurablefurnaceburntime.config.ConfigHandler;

import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class FurnaceBurnEvent {
	@SubscribeEvent
	public void furnaceBurnTimeEvent(FurnaceFuelBurnTimeEvent e) {
		int burntime = e.getBurnTime();
		int newburntime = (int)Math.ceil(burntime * ConfigHandler.GENERAL.burnTimeModifier.get());
		
		e.setBurnTime(newburntime);
	}
}
