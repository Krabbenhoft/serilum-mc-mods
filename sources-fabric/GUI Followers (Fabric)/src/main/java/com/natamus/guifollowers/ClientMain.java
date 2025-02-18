/*
 * This is the latest source code of GUI Followers.
 * Minecraft version: 1.19.2, mod version: 2.3.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.guifollowers;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.natamus.collective_fabric.fabric.callbacks.CollectivePlayerEvents;
import com.natamus.guifollowers.events.FollowerEvent;
import com.natamus.guifollowers.events.GUIEvent;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientMain implements ClientModInitializer {
	private static KeyMapping hotkey = KeyBindingHelper.registerKeyBinding(new KeyMapping("Clear Follower List", InputConstants.Type.KEYSYM, 92, "key.categories.misc"));
	
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		ClientTickEvents.END_CLIENT_TICK.register((Minecraft client) -> {
		    while (hotkey.isDown()) {
		    	FollowerEvent.onHotkeyPress();
		    	hotkey.setDown(false);
		    }
		    
		    FollowerEvent.onPlayerTick(client);
		});
		
		CollectivePlayerEvents.PLAYER_LOGGED_OUT.register((Level world, Player player) -> {
			FollowerEvent.onPlayerLogout(world, player);
		});
		
		HudRenderCallback.EVENT.register((PoseStack poseStack, float tickDelta) -> {
			GUIEvent.renderOverlay(poseStack, tickDelta);
		});
	}
}
