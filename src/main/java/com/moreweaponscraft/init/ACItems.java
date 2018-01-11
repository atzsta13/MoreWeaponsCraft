package com.moreweaponscraft.init;

import java.util.List;

import com.moreweaponscraft.api.IItemAdvanced;
import com.moreweaponscraft.ref.RefStrings;
import com.moreweaponscraft.registry.RegistryHelper;
import com.moreweaponscraft.util.JointList;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** Item class */
public class ACItems {
	
	private static JointList<IItemAdvanced> items;

	
	public static void mainRegistry() {
		Swords.mainRegistry();
		items = new JointList();


		registerItems();
	}
	

	/** Register the items */
	private static void registerItems() {
		RegistryHelper.registerItems(items);
	}
	
	/** Register the renderers */
	@SideOnly(Side.CLIENT)
	public static void registerRendering() {
		Swords.registerRendering();
		for(IItemAdvanced item : items) {
			item.registerRender();
		}
	}

}
