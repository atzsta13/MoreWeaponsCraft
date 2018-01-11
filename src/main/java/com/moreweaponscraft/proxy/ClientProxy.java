package com.moreweaponscraft.proxy;

import com.moreweaponscraft.events.InformationHandler;
import com.moreweaponscraft.init.ACBlocks;
import com.moreweaponscraft.init.ACItems;
import com.moreweaponscraft.registry.ClientRegistryHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Client-only initialization code */
public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(new ClientRegistryHelper());
	}
	
	@Override
	public void onInit(FMLInitializationEvent event) {
		super.onInit(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		MinecraftForge.EVENT_BUS.register(new InformationHandler());
	}
	
	@Override
	public void registerModels() {
		super.registerModels();
		//Diese einé Zeile -.-
		//ACBlocks.registerRendering();

		ACItems.registerRendering();
		}

}
