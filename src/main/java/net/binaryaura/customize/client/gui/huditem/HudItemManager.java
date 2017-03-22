package net.binaryaura.customize.client.gui.huditem;

import java.util.ArrayList;
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
	
	/**
	 * Instance of the HudItemManager.
	 * There should only be one instance for all mods.
	 */
	private static HudItemManager instance = new HudItemManager();	
	
	/**
	 * Gets the global instance of the HudItemManager.
	 * 
	 * @return		Global instance of this class.
	 */
	public static HudItemManager getInstance() {
		return instance;
	}

	/**
	 * Constructs an instance of the manager
	 */
	private HudItemManager() {
		hudItems = new ArrayList<HudItem>();
	}
	
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
	 * 
	 * @return		Collection of all the assigned HUDItems.
	 */
	public Iterable<HudItem> getHudItems() {
		return hudItems;
	}
	
	/**
	 * Get the HudItem with the assigned <code>name</code>.
	 * 
	 * @param name		The name of the HUDItem to be retrieved.
	 * @return			HudItem with the given name.
	 */
	public HudItem get(String name) {
		for(HudItem hudItem : hudItems.toArray(new HudItem[hudItems.size()])) {
			if(name.equals(hudItem.name))
				return hudItem;
		}
		Customize.log.warn("HUDItem "+ name + " isn't in the registry. Skipping");
		return null;
	}
	
	/**
	 * Get the HudItem with the assigned <code>id</code>.
	 * 
	 * @param id		The ID of the HUDItem to be retrieved.
	 * @return			HudItem with the given ID.
	 */
	public HudItem get(int id) {
		for(HudItem hudItem : hudItems.toArray(new HudItem[hudItems.size()])) {
			if(hudItem.getId() == id)
				return hudItem;
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem. Skipping");
		return null;
	}
	
	/**
	 * Move the HudItem with the assigned <code>id</code> down one. This means that the
	 * hudItem will be printed sooner, and thus under subsequent hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */	
	public void moveDown(int id) {
		for(HudItem item : hudItems) {
			int idx = hudItems.indexOf(item);
			hudItems.remove(item);
			HudItem temp = hudItems.get(idx--);
			hudItems.add(idx, temp);
			Customize.log.info("Moved " + item + " down");
		}
		Customize.log.warn("ID " + id + " doesn't relate to a hudItem. Skipping");
	}
	
	/**
	 * Move the HudItem with the assigned <code>name</code> down one. This means that the
	 * hudItem will be printed sooner, and thus under subsequent hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */
	public void moveDown(String name) {
		for(HudItem item : hudItems) {
			int idx = hudItems.indexOf(item);
			hudItems.remove(item);
			HudItem temp = hudItems.get(idx--);
			hudItems.add(idx, temp);
			Customize.log.info("Moved " + item + " down");
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registry. Skipping");
	}
	
	/**
	 * Move the HudItem with the assigned <code>id</code> up one. This means that the
	 * hudItem will be printed later, and thus on top of preceding hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */	
	public void moveUp(int id) {
		for(HudItem item : hudItems) {
			int idx = hudItems.indexOf(item);
			hudItems.remove(item);
			HudItem temp = hudItems.get(idx++);
			hudItems.add(idx, temp);
			Customize.log.info("Moved " + item + " up");
		}
		Customize.log.warn("ID " + id + " doesn't relate to a hudItem. Skipping");
	}
	
	/**
	 * Move the HudItem with the assigned <code>name</code> up one. This means that the
	 * hudItem will be printed later, and thus on top of preceding hudItems.
	 * 
	 * @param name		The name of the HUDItem to be moved.
	 */
	public void moveUp(String name) {
		for(HudItem item : hudItems) {
			if(name.equals(item.name)) {
				int idx = hudItems.indexOf(item);
				hudItems.remove(item);
				HudItem temp = hudItems.get(idx++);
				hudItems.add(idx, temp);
				Customize.log.info("Moved HudItem " + item + " up");
			}
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registry. Skipping");
	}
	
	/**
	 * Registers the <code>hudItem</code> to the Manager.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 */
	public void register(HudItem hudItem) {
		if(hudItem != null) {			
			hudItems.add(hudItem);
			hudItem.setId(nextId++);
			Customize.log.info("Registered " + hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param above			The name of the HudItem to be written above.
	 */
	public void register(HudItem hudItem, int ref) {
		if(hudItem != null) {
			for(HudItem item : hudItems)
			{
				if(ref == item.getId())
				{					
					hudItems.add(hudItems.indexOf(item), hudItem);
					hudItem.setId(nextId++);
					Customize.log.info("Registered " + hudItem + " above " + item);
					return;
				}
			}
			Customize.log.warn("Reference ID " + ref + " doesn't relate to a hudItem. Adding HudItem " + hudItem + " to top");
			register(hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager above the hudItem with the assigned name
	 * <code>ref</code>.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param ref			The name of the HudItem to be written above.
	 */
	public void register(HudItem hudItem, String ref) {
		if(hudItem != null) {
			for(HudItem item : hudItems)
			{
				if(ref.equals(item.name))
				{
					hudItems.add(hudItems.indexOf(item), hudItem);
					hudItem.setId(nextId++);
					Customize.log.info("Registered " + hudItem + " above " + item);
					return;
				}
			}
			Customize.log.warn("HudItem reference " + ref + " isn't in the registry. Adding " + hudItem + " to top");
			register(hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Registers <code>hudItem</code> to the Manager above the hudItem <code>ref</code>.
	 * 
	 * @param hudItem		The HUDItem to be written to the registry.
	 * @param ref			The HudItem to be written above.
	 */
	public void register(HudItem hudItem, HudItem ref) {
		if(hudItem != null) {
			if(ref != null && hudItems.contains(ref)) {
				hudItems.add(hudItems.indexOf(ref), hudItem);
				hudItem.setId(nextId++);
				Customize.log.info("Registered " + hudItem + " above " + hudItem);
			} else {
				if(ref == null) {
					Customize.log.warn("Null HudItem Reference. Adding " + hudItem + " to top");
					register(hudItem);
				} else {
					Customize.log.warn("Reference " + ref + " isn't in the registry. Adding " + hudItem + " to top");
					register(hudItem);
				}
			}
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	/**
	 * Unregisters the HudItem with the assigned <code>id</code> from the Manager.
	 * 
	 * @param id		The id of the HUDItem to be stricken from the registry.
	 */
	public void unregister(int id) {
		for(HudItem hudItem : new HudItem[hudItems.size()]) {
			if(id == hudItem.getId()) {
				hudItems.remove(hudItem);
				Customize.log.info("Unregistered HudItem " + hudItem);
				return;
			}
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem. Skipping");
	}
	
	/**
	 * Unregisters a HudItem with the assigned <code>name</code> from the Manager.
	 * 
	 * @param name		The name of the HUDItem to be stricken from the registry.
	 */
	public void unregister(String name) {
		for(HudItem hudItem : new HudItem[hudItems.size()]) {
			if(name.equals(hudItem.name)) {
				hudItems.remove(hudItem);
				Customize.log.info("Unregisterd HudItem " + hudItem);
				return;
			}
		}
		Customize.log.warn("HUDItem " + name + " isn't in the registery. Skipping");
	}
	
	/**
	 * Unregisters the <code>hudItem</code> from the Manager.
	 * 
	 * @param hudItem	The HUDItem to be stricken from the registry.
	 */
	public void unregister(HudItem hudItem) {
		if(hudItems.contains(hudItem)) {
			hudItems.remove(hudItem);
			Customize.log.info("Unregistered HudItem " + hudItem);
		} else {
			Customize.log.warn(hudItem + " isn't in the registry. Skipping");
		}
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
	private ArrayList<HudItem> hudItems;
}
