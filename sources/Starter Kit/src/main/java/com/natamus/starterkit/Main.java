/*
 * This is the latest source code of Starter Kit.
 * Minecraft version: 1.19.2, mod version: 3.4.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.starterkit;

import com.natamus.collective.check.RegisterMod;
import com.natamus.starterkit.cmds.CommandStarterkit;
import com.natamus.starterkit.config.ConfigHandler;
import com.natamus.starterkit.events.FirstSpawnEvent;
import com.natamus.starterkit.util.Reference;
import com.natamus.starterkit.util.Util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class Main {
	public static Main instance;
	
    public Main() {
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        modEventBus.addListener(this::loadComplete);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHandler.spec);

        RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
    }
    
    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandStarterkit.register(e.getDispatcher());
    }
	
    private void loadComplete(final FMLLoadCompleteEvent event) {
    	try {
			Util.getOrCreateGearConfig(true);
		} catch (Exception e) { }
    	
    	MinecraftForge.EVENT_BUS.register(new FirstSpawnEvent());
	}
}