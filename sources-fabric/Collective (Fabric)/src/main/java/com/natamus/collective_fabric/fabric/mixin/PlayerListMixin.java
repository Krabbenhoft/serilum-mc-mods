/*
 * This is the latest source code of Collective.
 * Minecraft version: 1.19.2, mod version: 4.50.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.collective_fabric.fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.natamus.collective_fabric.fabric.callbacks.CollectivePlayerEvents;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;

@Mixin(value = PlayerList.class, priority = 1001)
public class PlayerListMixin {
	@Inject(method = "placeNewPlayer", at = @At(value= "TAIL"))
	public void PlayerList_placeNewPlayer(Connection connection, ServerPlayer player, CallbackInfo ci) {
		CollectivePlayerEvents.PLAYER_LOGGED_IN.invoker().onPlayerLoggedIn(player.level, player);
	}
	
	@Inject(method = "remove", at = @At(value= "HEAD"))
	public void PlayerList_remove(ServerPlayer player, CallbackInfo ci) {
		CollectivePlayerEvents.PLAYER_LOGGED_OUT.invoker().onPlayerLoggedOut(player.level, player);
	}
}
