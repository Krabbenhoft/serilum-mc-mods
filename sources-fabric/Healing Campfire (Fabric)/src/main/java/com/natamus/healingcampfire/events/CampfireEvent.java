/*
 * This is the latest source code of Healing Campfire.
 * Minecraft version: 1.19.2, mod version: 3.9.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.healingcampfire.events;

import com.natamus.collective_fabric.functions.FABFunctions;
import com.natamus.healingcampfire.config.ConfigHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CampfireEvent {
	public static void playerTickEvent(ServerLevel world, ServerPlayer player) {
		if (player.tickCount % ConfigHandler.checkForCampfireDelayInTicks.getValue() != 0) {
			return;
		}
		
		List<BlockPos> nearbycampfires = FABFunctions.getAllTaggedTileEntityPositionsNearbyEntity(BlockTags.CAMPFIRES, ConfigHandler.healingRadius.getValue(), world, player);
		if (nearbycampfires.size() == 0) {
			return;
		}
		
		MobEffectInstance effectinstance;
		if (ConfigHandler.hideEffectParticles.getValue()) {
			effectinstance = new MobEffectInstance(MobEffects.REGENERATION, ConfigHandler.effectDurationSeconds.getValue()*20, ConfigHandler.regenerationLevel.getValue()-1, true, false);
		}
		else {
			effectinstance = new MobEffectInstance(MobEffects.REGENERATION, ConfigHandler.effectDurationSeconds.getValue()*20, ConfigHandler.regenerationLevel.getValue()-1);
		}
		
		BlockPos campfire = null;
		for (BlockPos nearbycampfire : nearbycampfires) {
			BlockState campfirestate = world.getBlockState(nearbycampfire);
			Block block = campfirestate.getBlock();
			
			if (!ConfigHandler.enableEffectForNormalCampfires.getValue()) {
				if (block.equals(Blocks.CAMPFIRE)) {
					continue;
				}
			}
			if (!ConfigHandler.enableEffectForSoulCampfires.getValue()) {
				if (block.equals(Blocks.SOUL_CAMPFIRE)) {
					continue;
				}
			}
			
			if (ConfigHandler.campfireMustBeLit.getValue()) {
				Boolean islit = campfirestate.getValue(CampfireBlock.LIT);
				if (!islit) {
					continue;
				}
			}
			if (ConfigHandler.campfireMustBeSignalling.getValue()) {
				Boolean issignalling = campfirestate.getValue(CampfireBlock.SIGNAL_FIRE);
				if (!issignalling) {
					continue;
				}
			}
			
			campfire = nearbycampfire.immutable();
			break;
		}
		
		if (campfire == null) {
			return;
		}
		
		BlockPos ppos = player.blockPosition();
		double r = (double)ConfigHandler.healingRadius.getValue();
		if (ppos.closerThan(campfire, r)) {
			boolean addeffect = true;
			MobEffectInstance currentregen = player.getEffect(effectinstance.getEffect());
			if (currentregen != null) {
				int currentduration = currentregen.getDuration();
				if (currentduration > (ConfigHandler.effectDurationSeconds.getValue()*10)) {
					addeffect = false;
				}
			}
			
			if (addeffect) {
				player.addEffect(effectinstance);
			}
		}
		if (ConfigHandler.healPassiveMobs.getValue()) {
			for (Entity entity : world.getEntities(player, new AABB(campfire.getX()-r, campfire.getY()-r, campfire.getZ()-r, campfire.getX()+r, campfire.getY()+r, campfire.getZ()+r))) {
				if (entity instanceof LivingEntity && (!(entity instanceof Player)) && !entity.getType().getCategory().equals(MobCategory.MONSTER)) {
					LivingEntity le = (LivingEntity)entity;
					
					boolean addeffect = true;
					MobEffectInstance currentregen = le.getEffect(effectinstance.getEffect());
					if (currentregen != null) {
						int currentduration = currentregen.getDuration();
						if (currentduration > (ConfigHandler.effectDurationSeconds.getValue()*10)) {
							addeffect = false;
						}
					}
					
					if (addeffect) {
						le.addEffect(effectinstance);
					}
				}
			}
		}
	}
}