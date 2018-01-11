package com.moreweaponscraft.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ACUtils {
	
	/** Get the metadata from an instance of IBlockSource */
	public static int getBlockSourceMetadata(IBlockSource src) {
		try {
			return src.getBlockState().getBlock().getMetaFromState(src.getBlockState());
		} catch(Exception e) {
			return 0;
		}
	}
	
	/** Returns the dispenser's facing direction based on its metadata */
	public static EnumFacing getDispenserFacing(int meta) {
        return EnumFacing.getFront(meta & 7);
    }
	
	/** Cached for speed */
	
	
    /** An unlimited type of areItemStacksEqual for crafting recipes (non-amount sensitive, cannot be null) */
    public static boolean areItemStacksEqualForCrafting(ItemStack... stacks) {
    	ItemStack comp = stacks[0];
    	if(comp.isEmpty()) return false;
    	ItemStack comp1 = comp.copy(); comp1.setCount(1);
    	for(int n = 1; n < stacks.length; n++) {
    		if(stacks[n].isEmpty()) return false;
    		ItemStack comp2 = stacks[n].copy(); comp2.setCount(1);
    		if(!ItemStack.areItemStacksEqual(comp1, comp2)) return false;
    	}
    	return true;
    }
    
    /** Does a given list of ItemStacks contain comp? Not amount sensitive! */
    public static boolean doesItemStackListContain(List<ItemStack> list, ItemStack comp, boolean remove) {
    	ItemStack comp1 = comp.copy(); comp1.setCount(1);
    	for(ItemStack stack : list) {
    		ItemStack stack1 = stack.copy(); stack1.setCount(1);
    		if(ItemStack.areItemStacksEqual(stack1, comp1)) {
    			if(remove) list.remove(stack);
    			return true;
    		}
    	}
    	return false;
    }
    
    /** Does a given list of ItemStacks contain comp? Not amount sensitive! */
    public static boolean doesItemStackListContain(List<ItemStack> list, Item comp, boolean remove) {
    	return doesItemStackListContain(list, new ItemStack(comp), remove);
    }
    
    /** Does a given list of ItemStacks contain comp? Not amount sensitive! */
    public static boolean doesItemStackListContain(List<ItemStack> list, Block comp, boolean remove) {
    	return doesItemStackListContain(list, new ItemStack(comp), remove);
    }
    
    /** Find a method with multiple method names (old reflection helper) */
    public static <E> Method findMethod(Class<? super E> clazz, E instance, String[] methodNames, Class<?>... methodTypes)
    {
        Exception failed = null;
        for (String methodName : methodNames)
        {
            try
            {
                Method m = clazz.getDeclaredMethod(methodName, methodTypes);
                m.setAccessible(true);
                return m;
            }
            catch (Exception e)
            {
                failed = e;
            }
        }
        throw new ReflectionHelper.UnableToFindMethodException(methodNames, failed);
    }

}
