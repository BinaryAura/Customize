package net.binaryaura.customize.client.gui.huditem;

import java.util.HashMap;

import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.ScaledResolution;

public class HudItemManager {
	
	// What about future(modded-in) types?
	public static enum HudItemType {
		BAR(),
		ICON(),
		ICON_GUAGE(),
		ICON_SET(),
		TEXT();
		
		HudItemType() {}
	}
	
	private static int nextId = 0;
	
	private static ScaledResolution res;
	private static HashMap<String, HudItem> hudItems;
	
	public static void updateRes(ScaledResolution res) {
		HudItemManager.res = res;
	}
	
	public static ScaledResolution getRes() {
		return res;
	}
	
	public static Iterable<HudItem> getHudItems() {
		return hudItems.values();
	}
	
	public static HudItem getHudItem(String name) {
		return hudItems.get(name);
	}
	
	public static HudItem getHudItem(int id) {
		for(HudItem hudItem : hudItems.values()) {
			if(hudItem.getId() == id)
				return hudItem;
		}
		Customize.log.warn("ID " + id + " doesn't relate to a HudItem.");
		return null;
	}

	public HudItemManager() {
		hudItems = new HashMap<String, HudItem>();
	}
	
	public void registerHudItem(HudItem hudItem) {
		if(hudItem != null) {			
			hudItems.put(hudItem.getName(), hudItem);
			hudItem.setId(nextId++);
			Customize.log.info("Registered " + hudItem + " (" + hudItem.getId() + ")");
		} else {
			Customize.log.warn("Null HudItem. Skipping.");
		}
	}
	
	public void unregisterHudItem(String name) {
		if(hudItems.containsKey(name)) {
			hudItems.remove(name);
			Customize.log.info("Unregisterd HudItem " + name);
		} else {
			Customize.log.warn("HudItem " + name + " isn't registered. Skipping.");
		}
	}	
}
