/*
 * This is the latest source code of Bottled Air.
 * Minecraft version: 1.19.2, mod version: 1.5.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.bottledair.events;

import com.natamus.bottledair.config.ConfigHandler;
import com.natamus.bottledair.util.Util;
import com.natamus.collective_fabric.data.GlobalVariables;
import com.natamus.collective_fabric.functions.ItemFunctions;
import com.natamus.collective_fabric.functions.PlayerFunctions;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class AirEvent {
	public static InteractionResultHolder<ItemStack> onBottleClick(Player player, Level world, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (world.isClientSide) {
			return InteractionResultHolder.pass(stack);
		}
		
		if (!stack.getItem().equals(Items.GLASS_BOTTLE)) {
			return InteractionResultHolder.pass(stack);
		}
		
		if (!player.isInWater()) {
			return InteractionResultHolder.pass(stack);
		}
		
		int maxair = player.getMaxAirSupply();
		int air = player.getAirSupply();
		
		if (air >= maxair) {
			return InteractionResultHolder.pass(stack);
		}
		
		int newair = air + ConfigHandler.amountOfAirInBottles.getValue();
		if (newair > maxair) {
			newair = maxair;
		}
		
		player.setAirSupply(newair);
		
		InteractionHand otherhand = PlayerFunctions.getOtherHand(hand);
		ItemStack otherstack = player.getItemInHand(otherhand);
		Item otheritem = otherstack.getItem();
		
		if (!Util.firetypeitems.contains(otheritem)) {
			return InteractionResultHolder.pass(stack);
		}
		
		Inventory inv = player.getInventory();
		for (int i=35; i > 0; i--) {
			ItemStack slot = inv.getItem(i);
			Item slotitem = slot.getItem();
			if (slotitem.equals(Items.POTION)) {
				if (PotionUtils.getPotion(slot).equals(Potions.WATER)) {
					slot.shrink(1);
					
					double num = GlobalVariables.random.nextDouble();
					if (num > ConfigHandler.chanceGlassBottleBreaksWithFireTypeInOffhand.getValue()) {
						ItemFunctions.giveOrDropItemStack(player, new ItemStack(Items.GLASS_BOTTLE, 1));
					}
					
					break;
				}
			}
		}
		
		return InteractionResultHolder.pass(stack);
	}
}
