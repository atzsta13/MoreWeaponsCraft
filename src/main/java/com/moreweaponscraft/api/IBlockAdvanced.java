package com.moreweaponscraft.api;

import com.moreweaponscraft.registry.ClientRegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Interface to define simple methods for blocks to use */
public interface IBlockAdvanced extends IPropertiesAdvanced {
	
	@SideOnly(Side.CLIENT)
	@Override
	public default void registerRender() {
		//neu
		ClientRegistryHelper.registerModel(Item.getItemFromBlock((Block)this), 0, new ModelResourceLocation(((Block)this).getRegistryName(), "inventory"));
	}
	
	/** Gets the block's item class to use when registering */
	public default Class<? extends ItemBlock> getItemClass() {
		return ItemBlock.class;
	}
	
	/** Gets additional arguments to pass through to the ItemBlock constructor */
	public default Object[] getItemClassArgs() {
		return new Object[0];
	}

}
