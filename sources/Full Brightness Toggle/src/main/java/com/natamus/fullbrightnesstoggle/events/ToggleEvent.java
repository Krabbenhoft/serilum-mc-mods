/*
 * This is the latest source code of Full Brightness Toggle.
 * Minecraft version: 1.18.2, mod version: 1.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.fullbrightnesstoggle.events;

import com.natamus.fullbrightnesstoggle.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ToggleEvent {
	private static Minecraft mc = null;
	private static double initialgamma = -1;
	private static double maxgamma = 14.0F % 28.0F + 1.0F;
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onKey(InputEvent.KeyInputEvent e) {
		if (e.getAction() != 1) {
			return;
		}
		
		if (mc == null) {
			mc = Minecraft.getInstance();
		}
		
		if (mc.screen instanceof ChatScreen) {
			return;
		}
		
		if (e.getKey() == Main.hotkey.getKey().getValue()) {
			Options settings = mc.options;
			if (initialgamma < 0) {
				if (settings.gamma >= 1.0F) {
					initialgamma = 1.0F;
					settings.gamma = 1.0F;
				}
				else {
					initialgamma = settings.gamma;			
				}
			}
			
			boolean gomax = false;
			if (settings.gamma != initialgamma && settings.gamma != maxgamma) {
				initialgamma = settings.gamma;
				gomax = true;
			}
			
			if (settings.gamma == initialgamma || gomax) {
				settings.gamma = maxgamma;
			}
			else {		          
				settings.gamma = initialgamma;
			} 			
		}
	}
}
