/*
 * This is the latest source code of Player Tracking Compass.
 * Minecraft version: 1.19.2, mod version: 2.0.
 *
 * Please don't distribute without permission.
 * For all modding projects, feel free to visit the CurseForge page: https://curseforge.com/members/serilum/projects
 */

package com.natamus.playertrackingcompass;

import com.natamus.collective.check.RegisterMod;
import com.natamus.playertrackingcompass.network.PacketToClientUpdateTarget;
import com.natamus.playertrackingcompass.network.RequestServerPacket;
import com.natamus.playertrackingcompass.util.Reference;
import com.natamus.playertrackingcompass.util.RegistryHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(Reference.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Main {
	public static Main instance;
	public static SimpleChannel network;
	
    public Main() {
        instance = this;

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        modEventBus.addListener(this::commonSetup);
        
        RegistryHandler.init();
        
        RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
		network = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID, Reference.MOD_ID), () -> "1.0", s -> true, s -> true);

		network.registerMessage(0, RequestServerPacket.class, RequestServerPacket::toBytes, RequestServerPacket::new, RequestServerPacket::handle);
		
		network.registerMessage(1, PacketToClientUpdateTarget.class, PacketToClientUpdateTarget::toBytes, PacketToClientUpdateTarget::new, PacketToClientUpdateTarget::handle);
    }
}