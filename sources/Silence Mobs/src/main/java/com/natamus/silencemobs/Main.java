/*
 * This is the latest source code of Silence Mobs.
 * Minecraft version: 1.19.2, mod version: 2.3.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.silencemobs;

import com.natamus.collective.check.RegisterMod;
import com.natamus.silencemobs.cmds.CommandSt;
import com.natamus.silencemobs.config.ConfigHandler;
import com.natamus.silencemobs.events.EntityEvent;
import com.natamus.silencemobs.util.Reference;

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

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::loadComplete);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHandler.spec);

        RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent e) {
    	CommandSt.register(e.getDispatcher());
    }
	
    private void loadComplete(final FMLLoadCompleteEvent event) {
    	MinecraftForge.EVENT_BUS.register(new EntityEvent());
	}
}