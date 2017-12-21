package com.moreweaponscraft.init;

import com.moreweaponscraft.api.IItemAdvanced;
import com.moreweaponscraft.api.ISwordAdvanced;
import com.moreweaponscraft.registry.RegistryHelper;
import com.moreweaponscraft.swords.SwordBasic;
import com.moreweaponscraft.util.JointList;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/** Swords are defined here! */
public class Swords {
	
	// sword list


	//http://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/modification-development/2459113-how-do-i-make-a-sword-teleport-a-player
	private static JointList<ISwordAdvanced> swords;

	public static void mainRegistry() {
		swords = new JointList();
		setupMaterials();
		addSwords();
		registerSwords();
	}
	
	/** Get the list of registered swords */
	public static List<ISwordAdvanced> getRegisteredSwords() {
		return swords;
	}
	
	// swords and sword materials

	public static SwordBasic theFirst;
	public static SwordBasic theExcalibur;
	public static SwordBasic theEnderSword;


	public static ToolMaterial advancedEmerald;
	public static ToolMaterial advancedDiamond;
	public static ToolMaterial advancedEnder;

	

	private static void setupMaterials() {
		// regular materials
		(advancedEmerald = EnumHelper.addToolMaterial("advancedEmerald", 4, 500, 12.0F, 10.0f, 22)).setRepairItem(new ItemStack(Blocks.EMERALD_BLOCK));
		(advancedEnder = EnumHelper.addToolMaterial("advancedEnder", 1, 10, 1.0F, 20.0f, 3)).setRepairItem(new ItemStack(Blocks.END_STONE));
		(advancedDiamond = EnumHelper.addToolMaterial("advancedDiamond", 4, 10000, 50.0F, 50.0F, 5)).setRepairItem(new ItemStack(Blocks.DIAMOND_BLOCK));

		

	}







	
	/** Define new swords here */
	private static void addSwords() {
		swords.join(

    		theFirst = (SwordBasic)new SwordBasic(advancedEmerald).setRegistryName("the_first"),
			theExcalibur = (SwordBasic)new SwordBasic(advancedDiamond).setRegistryName("the_excalibur"),
				theEnderSword = (SwordBasic) new SwordBasic(advancedEmerald).setRegistryName("the_ender_sword")


	);
	}
	
	/** Register the swords with the game registry */
	private static void registerSwords() {
		JointList<IItemAdvanced> items = new JointList().join(swords);
		RegistryHelper.registerItems(items);
	}
	
	/** Register the swords with the item model mesher */
	@SideOnly(Side.CLIENT)
	public static void registerRendering() {
		// iterate through them
		for(ISwordAdvanced sword : swords) {
			sword.registerRender();
		}
	}
	


}
