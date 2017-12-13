package com.moreweaponscraft.registry;

import com.google.common.collect.ObjectArrays;
import com.moreweaponscraft.api.IBlockAdvanced;
import com.moreweaponscraft.api.IItemAdvanced;
import com.moreweaponscraft.ref.Log;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.Constructor;

/** Helps register items and blocks in the game */
public class RegistryHelper {
	
	/** Register a list of blocks at once */
	public static void registerBlocks(Iterable<IBlockAdvanced> regBlocks) {
		for(IBlockAdvanced r : regBlocks) registerBlock(r);
	}
	
	/** Register a list of items at once */
	public static void registerItems(Iterable<IItemAdvanced> regItems) {
		for(IItemAdvanced r : regItems) registerItem(r);
	}
	 
	/** Register the block correctly */
	public static void registerBlock(IBlockAdvanced regBlock) {
		Block block = (Block)regBlock;
		ItemBlock item;
		
		// register the block by itself first
        GameRegistry.register(block.setUnlocalizedName(block.getRegistryName().toString()));
        
		// try to get the ItemBlock
        if(regBlock.getItemClass() != null) {
	        try {
				Class<?>[] ctorArgClasses = new Class[regBlock.getItemClassArgs().length + 1];
		        ctorArgClasses[0] = Block.class; // start with the block
		        for (int idx = 1; idx < ctorArgClasses.length; idx++) {
		            ctorArgClasses[idx] = regBlock.getItemClassArgs()[idx - 1].getClass();
		        }
		        Constructor<? extends ItemBlock> itemCtor = regBlock.getItemClass().getConstructor(ctorArgClasses);
		        item = itemCtor.newInstance(ObjectArrays.concat(regBlock, regBlock.getItemClassArgs()));
	        } catch (Exception e) {
	        	Log.logger.error("Unable to register block " + block.getRegistryName());
	        	return;
	        }
	        
	        // register the ItemBlock if there are no errors
	        GameRegistry.register(item.setRegistryName(block.getRegistryName()));
        }
	}
	
	/** Register the item correctly */
	public static void registerItem(IItemAdvanced regItem) {
		Item item = (Item)regItem;
		GameRegistry.register(item.setUnlocalizedName(item.getRegistryName().toString()));
	}

}
