package com.moreweaponscraft.swords;

import net.minecraft.item.ItemSword;

import com.moreweaponscraft.api.ISwordAdvanced;
import com.moreweaponscraft.creativetabs.ACCreativeTabs;

/** Used to implement a basic sword */
public class SwordBasic extends ItemSword implements ISwordAdvanced {

	public SwordBasic(ToolMaterial material) {
		super(material);
		this.setCreativeTab(ACCreativeTabs.main);
	}

}
