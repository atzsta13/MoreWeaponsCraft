package com.moreweaponscraft.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class ACConfig {
	
	// config file
	private static Configuration config;
	
	public static void mainRegistry(File f) {
		config = new Configuration(f);
		getConfig();
	}
	
	// Vanilla
	public static boolean enableEnchantmentUpgrades;
	public static boolean jeiIntegration;
	public static String emeraldSwordBase;
	public static int emeraldSwordDamage;
	
	// Advanced Addons
	public static boolean durabilityTooltips;
	public static List<String> tooltipModBL;
	public static boolean enableAdvancedBow;
	public static boolean enableAdvancedShield;
	public static boolean enableWoodenArmor;
	public static boolean enableStoneArmor;
	public static boolean enableNetherArmor;
	public static boolean enableObsidianArmor;
	public static boolean enableTrackingDispenser;
	public static int trackingDispenserRange;
	
	// other
	public static boolean enableCreativeSword;
	
	/** Do it up */
	private static void getConfig() {
		final String OPTIONS = config.CATEGORY_GENERAL;
		
		config.load();
		
		// Vanilla
		enableEnchantmentUpgrades = config.getBoolean("Enable enchantment upgrades", OPTIONS, true, "");
		jeiIntegration = config.getBoolean("Show Enchantment Upgrade Recipes in JEI", OPTIONS, true, "Disable to reduce memory usage");
		emeraldSwordBase = config.getString("Base for Advanced Emerald Sword Recipe", OPTIONS, "minecraft:golden_sword", "");
		emeraldSwordDamage = config.getInt("Emerald Sword Attack Damage", OPTIONS, 18, 15, 30, "");
		


		if(config.hasChanged()) config.save();
	}

}
