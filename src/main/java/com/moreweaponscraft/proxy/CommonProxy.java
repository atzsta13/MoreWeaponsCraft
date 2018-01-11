package com.moreweaponscraft.proxy;

import com.moreweaponscraft.config.ACConfig;
import com.moreweaponscraft.creativetabs.ACCreativeTabs;

import com.moreweaponscraft.registry.RegistryHelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/** Mod common initialization code */
public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RegistryHelper());
		ACConfig.mainRegistry(event.getSuggestedConfigurationFile());
		ACCreativeTabs.mainRegistry();
		//ACBlocks.mainRegistry();
		//ACItems.mainRegistry();

	}
	
	public void onInit(FMLInitializationEvent event) {
		//ACCraftingManager.mainRegistry();
		ACCreativeTabs.updateCreativeTabs();

	}
	
	public void postInit(FMLPostInitializationEvent event) {




	}
	
	public void registerModels() {}
			
}
