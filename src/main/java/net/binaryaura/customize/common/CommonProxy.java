package net.binaryaura.customize.common;

import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.util.KeyRegister;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * CommonProxy is the Mod's handler for the Minecraft Server.
 * It handles general registration. (e.g. Blocks, Items, Keys,
 * etc.)
 * 
 * @author	BinaryAura *
 */
public class CommonProxy {

	/**
	 * Handles preInitialization registration.
	 * 
	 * @param event			PreInitialization Event
	 */
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	/**
	 * Handles most registration. In this mod only needs a tick
	 * handler and a key register.
	 * 
	 * @param event			Initialization Event
	 */
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new KeyRegister());
		MinecraftForge.EVENT_BUS.register(new HudItemManager());
	}
	
	/**
	 * Handles postInitialization registration.
	 * 
	 * @param event			PostInitialization Event
	 */
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
