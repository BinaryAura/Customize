package net.binaryaura.customize.client;

import net.binaryaura.customize.client.gui.huditem.defaults.HudItemAir;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemArmor;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemCrosshairs;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemExperience;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemFood;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemHealth;
import net.binaryaura.customize.client.gui.huditem.defaults.HudItemToolTip;
import net.binaryaura.customize.common.CommonProxy;
import net.binaryaura.customize.common.Customize;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * The Client Proxy is the mod's handler for the Minecraft
 * Client. It handles renderer registration.
 * 
 * Here all the HudItems are registered. If a mod wants to add a HudItem
 * they would type the following:
 * 
 * <code>HudItemManager.getInstance().registerHudItem(new HudItemHealth(HEALTH));</code>
 * 
 * If one wished to remove an already existing HudItem:
 * 
 * <code>HudItemManager.getInstance().unregisterHudItem(ClientProxy.HEALTH);
 * 
 * Of coarse it isn't necessary to string all of this into one command. However, the
 * "ClientProxy.HEALTH" is referring to the field in this class. "HEALTH" would be in the
 * would be in the modder's class.
 * 
 * @author	BinaryAura
 * @see 	HudItemManager
 * @see 	HudItem
 */
public class ClientProxy extends CommonProxy {

	/**
	 * Reference Name for the default AIR HudItem.
	 */
	public static final String AIR = "air",
							   ARMOR = "armor",
							   BOSSHEALTH = "bosshealth",
							   CROSSHAIRS = "crosshairs",
							   DEBUG = "debug",
							   EXPERIENCE = "experience",
							   FOOD = "food",
							   HEALTH = "health",
							   HOTBAR = "hotbar",
							   JUMP = "jump",
							   MOUNTHEALTH = "mounthealth",
							   OBJECTIVE = "objective",
							   PLAYERLIST = "playerlist",
							   SUBTITLE = "subtitle",
							   TITLE = "title",
							   TOOLTIP = "tooltip";
	
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
	
	/**
	 * Registers all HudItems with the HudItemManager.
	 */
	private void registerHudItems() {
		Customize.hudManager.registerHudItem(new HudItemHealth(HEALTH));
		Customize.hudManager.registerHudItem(new HudItemExperience(EXPERIENCE));
		Customize.hudManager.registerHudItem(new HudItemFood(FOOD));
		Customize.hudManager.registerHudItem(new HudItemAir(AIR));
		Customize.hudManager.registerHudItem(new HudItemArmor(ARMOR));
		Customize.hudManager.registerHudItem(new HudItemCrosshairs(CROSSHAIRS));
		Customize.hudManager.registerHudItem(new HudItemToolTip(TOOLTIP));
	}
	
	/**
	 * Registers all Renderers, including the HudItems.
	 */
	private void registerRenderers() {
		registerHudItems();
	}

}
