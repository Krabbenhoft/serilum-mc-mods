/*
 * This is the latest source code of Your Items Are Safe.
 * Minecraft version: 1.19.2, mod version: 1.3.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.youritemsaresafe.util;

import java.util.List;

import com.natamus.collective.functions.CompareItemFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.youritemsaresafe.config.ConfigHandler;

import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Util {
	public static int processCheck(List<ItemStack> itemstacks, int itemsleft, String compare, int decrease) {
		for (ItemStack itemstack : itemstacks) {
			if (itemsleft <= 0) {
				break;
			}
			
			int count = itemstack.getCount();
			if (comparePassed(compare, itemstack)) {
				while (count > 0 && itemsleft > 0) {
					itemsleft -= decrease;
					count -= 1;
					itemstack.setCount(count);
				}
			}
		}
		
		return itemsleft;		
	}
	
	public static boolean comparePassed(String compare, ItemStack itemstack) {
		switch (compare) {
			case "log":
				return CompareItemFunctions.isLog(itemstack);
			case "plank":
				return CompareItemFunctions.isPlank(itemstack);
			case "chest":
				return CompareItemFunctions.isChest(itemstack);
			case "stone":
				return CompareItemFunctions.isStone(itemstack);
			case "slab":
				return CompareItemFunctions.isSlab(itemstack);
		}
		
		return false;
	}
	
	public static int processLogCheck(List<ItemStack> itemstacks, int planksleft) {
		return processCheck(itemstacks, planksleft, "log", 4);
	}
	
	public static int processPlankCheck(List<ItemStack> itemstacks, int planksleft) {
		return processCheck(itemstacks, planksleft, "plank", 1);
	}
	
	public static int processChestCheck(List<ItemStack> itemstacks, int planksleft) {
		return processCheck(itemstacks, planksleft, "chest", 8);
	}
	
	public static int processStoneCheck(List<ItemStack> itemstacks, int stoneleft) {
		return processCheck(itemstacks, stoneleft, "stone", 1);
	}
	
	public static int processSlabCheck(List<ItemStack> itemstacks, int stoneleft) {
		return processCheck(itemstacks, stoneleft, "slab", 1);
	}
	
	public static void failureMessage(Player player, int planksleft, int stoneleft, int planksneeded, int stoneneeded) {
		if (ConfigHandler.GENERAL.sendMessageOnCreationFailure.get()) {
			String failurestring = ConfigHandler.GENERAL.creationFailureMessage.get();
			failurestring = failurestring.replaceAll("%plankamount%", planksleft + "").replaceAll("%stoneamount%", stoneleft + "");
			
			StringFunctions.sendMessage(player, failurestring, ChatFormatting.RED);
		}
		
		Level world = player.getCommandSenderWorld();
		Vec3 vec = player.position();
		
		System.out.println(planksleft + ", " + planksneeded);
		if (planksleft != planksneeded) {
			ItemEntity planks = new ItemEntity(world, vec.x, vec.y+1, vec.z, new ItemStack(Items.OAK_PLANKS, planksneeded-planksleft));
			world.addFreshEntity(planks);
		}
		
		if (stoneleft != stoneneeded) {
			ItemEntity stones = new ItemEntity(world, vec.x, vec.y+1, vec.z, new ItemStack(Items.STONE, stoneneeded-stoneleft));
			world.addFreshEntity(stones);
		}
	}
	public static void successMessage(Player player) {
		if (ConfigHandler.GENERAL.sendMessageOnCreationSuccess.get()) {
			StringFunctions.sendMessage(player, ConfigHandler.GENERAL.creationSuccessMessage.get(), ChatFormatting.DARK_GREEN);
		}		
	}
}
