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

import com.natamus.collective_fabric.fabric.callbacks.CollectiveEntityEvents;

import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.phys.Vec3;

@Mixin(value = AbstractHorse.class, priority = 1001)
public class AbstractHorseMixin {
	@Inject(method = "travel", at = @At(value= "INVOKE", target = "Lnet/minecraft/world/entity/animal/horse/AbstractHorse;setIsJumping(Z)V", ordinal = 0))
	public void AbstractHorse_travel(Vec3 vec3, CallbackInfo ci) {
		AbstractHorse horse = (AbstractHorse)(Object)this;
		CollectiveEntityEvents.ON_ENTITY_IS_JUMPING.invoker().onLivingJump(horse.level, horse);
	}
}
