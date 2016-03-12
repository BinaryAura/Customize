package net.binaryaura.customize.client.gui.huditem;

import java.util.HashMap;

import net.binaryaura.customize.common.Customize;

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
	
	public static HashMap<String, HudItem> hudItems;

	public HudItemManager() {
		hudItems = new HashMap<String, HudItem>();
	}
	
	public void registerHudItem(HudItem hudItem) {
		if(hudItem != null) {			
			hudItems.put(hudItem.getName(), hudItem);
			Customize.log.info("Registered " + hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
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
	
	private int screenWidth, screenHeight;
}
