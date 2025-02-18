/*
 * This is the latest source code of The Vanilla Experience.
 * Minecraft version: 1.17.1, mod version: 1.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.thevanillaexperience.mods.scaffoldingdropsnearby.events;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.natamus.collective.functions.WorldFunctions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ScaffoldingBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ScaffoldingDropsNearbyScaffoldingEvent {
	private static CopyOnWriteArrayList<BlockPos> lastScaffoldings = new CopyOnWriteArrayList<BlockPos>();
	private static HashMap<BlockPos, Date> lastaction = new HashMap<BlockPos, Date>();
	
	@SubscribeEvent
	public void onScaffoldingItem(EntityJoinWorldEvent e) {
		Level world = e.getWorld();
		if (world.isClientSide) {
			return;
		}
		
		Entity entity = e.getEntity();
		if (entity instanceof ItemEntity == false) {
			return;	
		}
		
		ItemEntity ie = (ItemEntity)entity;
		ItemStack itemstack = ie.getItem();
		if (itemstack.getItem() instanceof ScaffoldingBlockItem == false) {
			return;
		}
		
		Date now = new Date();
		
		BlockPos scafpos = entity.blockPosition();
		BlockPos lowscafpos = new BlockPos(scafpos.getX(), 1, scafpos.getZ());
		for (BlockPos lspos : lastScaffoldings) {
			if (lastaction.containsKey(lspos)) {
				Date lastdate = lastaction.get(lspos);
				long ms = (now.getTime()-lastdate.getTime());
				if (ms > 2000) {
					lastScaffoldings.remove(lspos);
					lastaction.remove(lspos);
					continue;
				}			
			}
			
			if (lowscafpos.closerThan(new BlockPos(lspos.getX(), 1, lspos.getZ()), 20)) {
				entity.teleportTo(lspos.getX(), lspos.getY()+1, lspos.getZ());
				lastaction.put(lspos.immutable(), now);
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent e) {
		Level world = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
		if (world == null) {
			return;
		}
		
		BlockState state = e.getState();
		Block block = state.getBlock();
		if (block.equals(Blocks.SCAFFOLDING)) {
			BlockPos pos = e.getPos().immutable();
			lastScaffoldings.add(pos);
			lastaction.put(pos, new Date());
		}
	}
}
