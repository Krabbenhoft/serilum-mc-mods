/*
 * This is the latest source code of The Vanilla Experience.
 * Minecraft version: 1.17.1, mod version: 1.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.thevanillaexperience.mods.starterkit.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.natamus.collective.functions.PlayerFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.thevanillaexperience.mods.starterkit.util.StarterKitUtil;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import net.minecraft.ChatFormatting;

public class CommandStarterkit {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
    	dispatcher.register(Commands.literal("starterkit").requires((iCommandSender) -> iCommandSender.hasPermission(2))
			.then(Commands.literal("set")
			.executes((command) -> {
				CommandSourceStack source = command.getSource();
				Player player;
				try {
					player = source.getPlayerOrException();
				}
				catch (CommandSyntaxException ex) {
					StringFunctions.sendMessage(source, "This command can only be executed as a player in-game.", ChatFormatting.RED);
					return 1;
				}
				
				String newskconfig = PlayerFunctions.getPlayerGearString(player);
				if (!StarterKitUtil.processNewGearString(newskconfig)) {
					StringFunctions.sendMessage(source, "Something went wrong while processing the new starterkit config.", ChatFormatting.RED);
					return 0;
				}
				
				StringFunctions.sendMessage(source, "Starter Kit config updated. All new players will now receive your current inventory.", ChatFormatting.DARK_GREEN);
				return 1;
			}))
		);
    }
}
