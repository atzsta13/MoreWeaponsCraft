package com.moreweaponscraft.registry;

import java.lang.reflect.Constructor;
import java.util.List;

import com.moreweaponscraft.MoreWeaponsCraft;
import com.moreweaponscraft.api.IBlockAdvanced;
import com.moreweaponscraft.api.IItemAdvanced;
import com.moreweaponscraft.crafting.ACCraftingManager;
import com.moreweaponscraft.init.ACItems;
import com.moreweaponscraft.ref.Log;
import com.moreweaponscraft.util.JointList;
import com.google.common.collect.ObjectArrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Helps register stuff in the game */
public class RegistryHelper {
	
	public static final List<IRecipe> RECIPES_TO_REGISTER = new JointList();
	public static final List<Block> BLOCKS_TO_REGISTER = new JointList();
	public static final List<Item> ITEMS_TO_REGISTER = new JointList();
	
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
        BLOCKS_TO_REGISTER.add(block.setUnlocalizedName(block.getRegistryName().toString()));
        
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
	        ITEMS_TO_REGISTER.add(item.setRegistryName(block.getRegistryName()));
        }
	}
	
	/** Register the item correctly */
	public static void registerItem(IItemAdvanced regItem) {
		Item item = (Item)regItem;
		ITEMS_TO_REGISTER.add(item.setUnlocalizedName(item.getRegistryName().toString()));
	}
	
	/** Register blocks */
	@SubscribeEvent
	public void onBlockRegistry(Register<Block> e) {

		
		for(Block b : BLOCKS_TO_REGISTER) {
			e.getRegistry().register(b);
		}
		BLOCKS_TO_REGISTER.clear();
		
		Log.logger.info("Blocks registered.");
	}
	
	/** Register items */
	@SubscribeEvent
	public void onItemRegistry(Register<Item> e) {
		ACItems.mainRegistry();
		
		for(Item i : ITEMS_TO_REGISTER) {
			e.getRegistry().register(i);
		}
		ITEMS_TO_REGISTER.clear();
		


		
		MoreWeaponsCraft.proxy.registerModels();
		
		Log.logger.info("Items registered.");
	}
	
	/** Register recipes */
	@SubscribeEvent
	public void onRecipeRegistry(Register<IRecipe> e) {
		ACCraftingManager.addCraftingRecipes();
		
		for(IRecipe r : RECIPES_TO_REGISTER) {
			e.getRegistry().register(r);
		}
		RECIPES_TO_REGISTER.clear();
		
		Log.logger.info("Recipes registered.");
	}

}
