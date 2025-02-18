/*
 * This is the latest source code of Collective.
 * Minecraft version: 1.19.2, mod version: 4.50.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.collective_fabric.functions;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;

public class CompareItemFunctions {
	public static boolean itemIsInRegistryHolder(Item item, TagKey<Item> tagKey) {
		return item.builtInRegistryHolder().is(tagKey);
	}

	public static boolean isSapling(Item item) {
		return itemIsInRegistryHolder(item, ItemTags.SAPLINGS) || Block.byItem(item) instanceof SaplingBlock;
	}
	public static boolean isSapling(ItemStack itemstack) {
		return isSapling(itemstack.getItem());
	}

	public static boolean isLog(Item item) {
		return itemIsInRegistryHolder(item, ItemTags.LOGS);
	}
	public static boolean isLog(ItemStack itemstack) {
		return isLog(itemstack.getItem());
	}

	public static boolean isPlank(Item item) {
		return itemIsInRegistryHolder(item, ItemTags.PLANKS);
	}
	public static boolean isPlank(ItemStack itemstack) {
		return isPlank(itemstack.getItem());
	}

	public static boolean isChest(Item item) {
		Block block = Block.byItem(item);
		return block.equals(Blocks.CHEST) || block.equals(Blocks.TRAPPED_CHEST);
	}
	public static boolean isChest(ItemStack itemstack) {
		return isChest(itemstack.getItem());
	}

	public static boolean isStone(Item item) {
		return itemIsInRegistryHolder(item, ItemTags.STONE_CRAFTING_MATERIALS);
	}
	public static boolean isStone(ItemStack itemstack) {
		return isStone(itemstack.getItem());
	}

	public static boolean isSlab(Item item) {
		return itemIsInRegistryHolder(item, ItemTags.SLABS);
	}
	public static boolean isSlab(ItemStack itemstack) {
		return isSlab(itemstack.getItem());
	}
}
