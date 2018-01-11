package com.moreweaponscraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.moreweaponscraft.api.IBlockAdvanced;
import com.moreweaponscraft.creativetabs.ACCreativeTabs;

/** Used to implement a basic block */
public class BlockBasic extends Block implements IBlockAdvanced {

	public BlockBasic(Material mat, float hardness, float resistance) {
		super(mat);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setCreativeTab(ACCreativeTabs.main);
	}
	
}
