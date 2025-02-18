/*
 * This is the latest source code of Kelp Fertilizer.
 * Minecraft version: 1.19.2, mod version: 2.0.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.kelpfertilizer.dispenser;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class BehaviourKelpDispenser implements DispenseItemBehavior {
	protected final Item kelp;

	public BehaviourKelpDispenser(Item itemIn){
		kelp = itemIn;
	}

	@Override
	public ItemStack dispense(BlockSource source, ItemStack itemstack) {
		Level world = source.getLevel();
		if (world.isClientSide) {
			return itemstack;
		}
		
		BlockPos pos = source.getPos();
		BlockState state = source.getBlockState();
		Direction facing = state.getValue(DispenserBlock.FACING);
		BlockPos facepos = pos.relative(facing);
		
		if (BoneMealItem.applyBonemeal(itemstack, world, facepos, null)) {
			world.levelEvent(2005, facepos, 0);
		}
		
		return itemstack;
	}
}
