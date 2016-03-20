package net.binaryaura.customize.client;

import net.binaryaura.customize.client.gui.huditem.HudItemAir;
import net.binaryaura.customize.client.gui.huditem.HudItemArmor;
import net.binaryaura.customize.client.gui.huditem.HudItemExperience;
import net.binaryaura.customize.client.gui.huditem.HudItemFood;
import net.binaryaura.customize.client.gui.huditem.HudItemHealth;
import net.binaryaura.customize.client.gui.huditem.HudItemToolTip;
import net.binaryaura.customize.common.CommonProxy;
import net.binaryaura.customize.common.Customize;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	public static final String AIR = "air";
	public static final String ARMOR = "armor";
	public static final String BOSSHEALTH = "bosshealth";
	public static final String CROSSHAIRS = "crosshairs";
	public static final String DEBUG = "debug";
	public static final String EXPERIENCE = "experience";
	public static final String FOOD = "food";
	public static final String HEALTH = "health";
	public static final String HOTBAR = "hotbar";
	public static final String JUMP = "jump";
	public static final String MOUNTHEALTH = "mounthealth";
	public static final String OBJECTIVE = "objective";
	public static final String PLAYERLIST = "playerlist";
	public static final String SUBTITLE = "subtitle";
	public static final String TITLE = "title";	
	public static final String TOOLTIP = "tooltip";
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerRenderers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
	
	private void registerHudItems() {
		Customize.hudManager.registerHudItem(new HudItemHealth(HEALTH));
		Customize.hudManager.registerHudItem(new HudItemExperience(EXPERIENCE));
		Customize.hudManager.registerHudItem(new HudItemFood(FOOD));
		Customize.hudManager.registerHudItem(new HudItemAir(AIR));
		Customize.hudManager.registerHudItem(new HudItemArmor(ARMOR));
		Customize.hudManager.registerHudItem(new HudItemToolTip(TOOLTIP));
	}
	
	private void registerRenderers() {
		registerHudItems();
	}

}
