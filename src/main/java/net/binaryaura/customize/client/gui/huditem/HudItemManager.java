package net.binaryaura.customize.client.gui.huditem;

import java.util.HashMap;

import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.ScaledResolution;

/**
 * Manager for all HUDItems. HudItemManager keeps track of all
 * HudItems and has methods to add and remove HudItems. It also
 * holds global values that all HudItems may use.
 * 
 * There is strictly one instance of the HudItemManager which can be
 * obtained by <code>HudItemManager.getInstance()</code>.
 * @author	BinaryAura
 *
 */
public class HudItemManager {
	
	// What about future(modded-in) types?
	@Deprecated
	public static enum HudItemType {
		BAR(),
		ICON(),
		ICON_GUAGE(),
		ICON_SET(),
		TEXT();
		
		HudItemType() {}
	}
	
	/**
	 * Gets the global instance of the HudItemManager.
	 * 
	 * @return		Global instance of this class.
	 */
	public static HudItemManager getInstance() {
		return instance;
	}
	
	/**
	 * Instance of the HudItemManager.
	 * There should only be one instance for all mods.
	 */
	private static HudItemManager instance = new HudItemManager();
	
	/**
	 * Updates the <code>res</code> of the Minecraft Window.
	 * 
	 * @param res 		The resolution for the Minecraft Window.
	 */
	public void updateRes(ScaledResolution res) {
		this.res = res;
	}
	
	/**
	 * Get the resolution of Minecraft Window.
	 * 
	 * @return		Resolution of Minecraft Window.
	 */
	public ScaledResolution getRes() {
		return res;
	}
	
	/**
	 * Get all HudItems.
	 * @return		Collection of all the assigned HUDItems.
	 */
	public Iterable<HudItem> getHudItems() {
		return hudItems.values();
	}
	
	/**
	 * Get the HudItem with the assigned <code>name</code>.
	 * 
	 * @param name		The name of the HUDItem to be retrieved.
	 * @return			HudItem with the given name.
	 */
	public HudItem getHudItem(String name) {
		return hudItems.get(name);
	}
	
	/**
	 * Get the HudItem with the assigned <code>id</code>.
	 * 
	 * @param id		The ID of the HUDItem to be retrieved.
	 * @return			HudItem with the given ID.
	 */
	public HudItem getHudItem(int id) {
		for(HudItem hudItem : hudItems.values()) {
			if(hudItem.getId() == id)
				return hudItem;
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem.");
		return null;
	}
	
	/**
	 * Registers a HudItem to the Manager.
	 * 
	 * @param hudItem		The HUDItem to be writen to the registry.
	 */
	public void registerHudItem(HudItem hudItem) {
		if(hudItem != null) {			
			hudItems.put(hudItem.getName(), hudItem);
			hudItem.setId(nextId++);
			Customize.log.info("Registered " + hudItem + " (" + hudItem.getId() + ")");
		} else {
			Customize.log.warn("Null HudItem. Skipping.");
		}
	}
	
	/**
	 * Unregisters a HudItem from the Manager.
	 * 
	 * @param name			The HUDItem to be stricken from the registry.
	 */
	public void unregisterHudItem(String name) {
		if(hudItems.containsKey(name)) {
			hudItems.remove(name);
			Customize.log.info("Unregisterd HudItem " + name);
		} else {
			Customize.log.warn("HudItem " + name + " isn't registered. Skipping.");
		}
	}	

	/**
	 * Constructs an instance of the manager
	 */
	private HudItemManager() {
		hudItems = new HashMap<String, HudItem>();
	}
	
	/**
	 * The ID for the next HUDItem assigned.
	 */
	private int nextId = 0;
	
	/**
	 * The resolution for Minecraft Window.
	 */
	private ScaledResolution res;
	
	/**
	 * Registry of all the assigned HUDItems.
	 */
	private HashMap<String, HudItem> hudItems;	
}
