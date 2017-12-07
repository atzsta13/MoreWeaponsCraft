package com.advancedcombat.init;

import com.advancedcombat.api.IItemAdvanced;
import com.advancedcombat.api.ISwordAdvanced;
import com.advancedcombat.config.ACConfig;
import com.advancedcombat.registry.RegistryHelper;
import com.advancedcombat.swords.SwordBasic;
import com.advancedcombat.swords.SwordCustomRepair;
import com.advancedcombat.swords.SwordShiny;
import com.advancedcombat.util.JointList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/** Swords are defined here! */
public class Swords {
	
	// sword list
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
	public static SwordCustomRepair advancedWoodenSword;
	public static SwordBasic advancedStoneSword;
	public static SwordBasic advancedIronSword;
	public static SwordBasic advancedGoldenSword;
	public static SwordBasic advancedEmeraldSword;
	public static SwordBasic advancedDiamondSword;
	public static SwordBasic netherStarSword;
	public static SwordBasic advancedNetherStarSword;
	public static ToolMaterial advancedWood;
	public static ToolMaterial advancedStone;
	public static ToolMaterial advancedIron;
	public static ToolMaterial advancedGold;
	public static ToolMaterial advancedEmerald;
	public static ToolMaterial advancedDiamond;
	public static ToolMaterial netherStar;
	public static ToolMaterial advancedNetherStar;
	

	private static void setupMaterials() {
		// regular materials
		advancedWood = EnumHelper.addToolMaterial("advancedWood", 1, 236, 8.0F, 1.75F, 8);
		(advancedStone = EnumHelper.addToolMaterial("advancedStone", 2, 524, 12.0F, 3.0F, 3)).setRepairItem(new ItemStack(ACBlocks.advancedStone));
		(advancedIron = EnumHelper.addToolMaterial("advancedIron", 3, 2250, 18.0F, 10.0F, 7)).setRepairItem(new ItemStack(Blocks.IRON_BLOCK));
		(advancedGold = EnumHelper.addToolMaterial("advancedGold", 2, 1561, 8.0F, 6.0F, 15)).setRepairItem(new ItemStack(Blocks.GOLD_BLOCK));
		(advancedEmerald = EnumHelper.addToolMaterial("advancedEmerald", 4, 5, 12.0F, ACConfig.emeraldSwordDamage - 4, 22)).setRepairItem(new ItemStack(Blocks.EMERALD_BLOCK));
		(advancedDiamond = EnumHelper.addToolMaterial("advancedDiamond", 4, 8192, 24.0F, 20.0F, 5)).setRepairItem(new ItemStack(Blocks.DIAMOND_BLOCK));
		(netherStar = EnumHelper.addToolMaterial("netherStar", 4, 9366, 20.0F, 26.0F, 1)).setRepairItem(new ItemStack(Items.NETHER_STAR));
		(advancedNetherStar = EnumHelper.addToolMaterial("advancedNetherStar", 5, 16384, 30.0F, 56.0F, 0)).setRepairItem(new ItemStack(ACBlocks.netherStarBlock));
		

	}
	
	/** Define new swords here */
	private static void addSwords() {
		swords.join(
			// regular swords
			advancedWoodenSword = (SwordCustomRepair)new SwordCustomRepair(advancedWood).setRegistryName("advanced_wooden_sword"),
			advancedStoneSword = (SwordBasic)new SwordBasic(advancedStone).setRegistryName("advanced_stone_sword"),
			advancedIronSword = (SwordBasic)new SwordBasic(advancedIron).setRegistryName("advanced_iron_sword"),
			advancedGoldenSword = (SwordBasic)new SwordBasic(advancedGold).setRegistryName("advanced_golden_sword"),
			advancedEmeraldSword = (SwordBasic)new SwordBasic(advancedEmerald).setRegistryName("advanced_emerald_sword"),
			advancedDiamondSword = (SwordBasic)new SwordBasic(advancedDiamond).setRegistryName("advanced_diamond_sword"),
			netherStarSword = (SwordBasic)new SwordShiny(netherStar).setRegistryName("nether_star_sword"),
			advancedNetherStarSword = (SwordBasic)new SwordShiny(advancedNetherStar).setRegistryName("advanced_nether_star_sword")
			
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
	
	/** Finish up sword information */
	public static void finalizeSwords() {
		// wooden sword repairability
		advancedWoodenSword.addRepairOres("logWood");
		

	}

}
