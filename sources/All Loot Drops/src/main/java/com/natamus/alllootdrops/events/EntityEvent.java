/*
 * This is the latest source code of All Loot Drops.
 * Minecraft version: 1.19.2, mod version: 2.5.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.alllootdrops.events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.natamus.alllootdrops.config.ConfigHandler;
import com.natamus.collective.data.GlobalVariables;
import com.natamus.collective.functions.ItemFunctions;
import com.natamus.collective.functions.WorldFunctions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class EntityEvent {
	@SubscribeEvent
	public void onWorldLoad(LevelEvent.Load e) {
		Level world = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (world == null) {
			return;
		}
		
		if (GlobalVariables.entitydrops != null) {
			return;
		}
		
		ItemFunctions.generateEntityDropsFromLootTable(world);
	}
	
	@SubscribeEvent
	public void mobItemDrop(LivingDropsEvent e) {
		Entity entity = e.getEntity();
		Level world = entity.getCommandSenderWorld();
		if (world.isClientSide) {
			return;
		}
		
		if (entity instanceof Player || entity instanceof LivingEntity == false) {
			return;
		}
		
		if (GlobalVariables.entitydrops == null) {
			System.out.println("[All Loot Drops] Error: Unable to find generated loot drops. Attempting to generate them now.");
			ItemFunctions.generateEntityDropsFromLootTable(world);
			if (GlobalVariables.entitydrops == null) {
				System.out.println("[All Loot Drops] Error: Still unable to generate loot drops. Please submit a bug report at 'https://github.com/ricksouth/serilum-mc-mods/labels/Mod:%20All%20Loot%20Drops'.");
				return;
			}
		}

		LivingEntity le = (LivingEntity)entity;
		EntityType<?> type = le.getType();
		
		List<Item> alldrops = GlobalVariables.entitydrops.get(type);
		if (alldrops == null) {
			return;
		}
		
		int amount = ConfigHandler.GENERAL.lootQuantity.get();
		if (ConfigHandler.GENERAL.lootingEnchantAffectsQuantity.get()) {
			int lootinglevel = e.getLootingLevel();
			double increasechance = ConfigHandler.GENERAL.lootingEnchantExtraQuantityChance.get();
			for (int n = 0; n < lootinglevel; n++) {
				double num = GlobalVariables.random.nextDouble();
				if (num <= increasechance) {
					amount+=1;
				}
			}
		}
		
		Vec3 evec = entity.position();
		
		Collection<ItemEntity> drops = e.getDrops();
		List<ItemEntity> tr = new ArrayList<ItemEntity>();
		
		Iterator<ItemEntity> it = e.getDrops().iterator();
		while (it.hasNext()) {
			ItemEntity ie = it.next();
			ItemStack stack = ie.getItem();
			Item item = stack.getItem();
			if (alldrops.contains(item)) {
				tr.add(ie);
			}
		}
		
		if (tr.size() > 0) {
			for (ItemEntity ie : tr) {
				drops.remove(ie);
			}
		}

		for (Item item : alldrops) {
			ItemEntity ie = new ItemEntity(world, evec.x, evec.y+1, evec.z, new ItemStack(item, amount));
			drops.add(ie);
		}
	}
}
