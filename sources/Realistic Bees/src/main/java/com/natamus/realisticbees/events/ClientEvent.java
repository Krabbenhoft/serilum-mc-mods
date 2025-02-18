/*
 * This is the latest source code of Realistic Bees.
 * Minecraft version: 1.19.2, mod version: 2.7.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.realisticbees.events;

import com.natamus.collective.functions.ConfigFunctions;
import com.natamus.realisticbees.config.ConfigHandler;
import com.natamus.realisticbees.renderer.CustomBeeRenderer;
import com.natamus.realisticbees.util.Reference;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=Reference.MOD_ID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvent {
	@SubscribeEvent
    public static void loadComplete(EntityRenderersEvent.RegisterRenderers e) {
		if (ConfigFunctions.getDictValues(Reference.MOD_ID).size() > 0 && ConfigFunctions.getDictValues(Reference.MOD_ID).get("beeSizeModifier") != null) {
			if (ConfigFunctions.getDictValues(Reference.MOD_ID).get("beeSizeModifier").equals("1.0")) {
				return;
			}
		}
		
		e.registerEntityRenderer(EntityType.BEE, manager -> new CustomBeeRenderer(manager));
	}
}
