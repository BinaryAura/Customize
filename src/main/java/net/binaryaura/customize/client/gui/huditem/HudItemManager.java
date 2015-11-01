package net.binaryaura.customize.client.gui.huditem;

import java.util.HashSet;

import net.binaryaura.customize.common.Customize;

public class HudItemManager {
	
	public static enum HudItemType {
		BAR(),
		ICON(),
		ICON_GUAGE(),
		ICON_SET(),
		TEXT();
		
		HudItemType() {
		}
	}

	public HudItemManager() {
		hudItems = new HashSet<HudItem>();
	}
	
	public void registerHudItem(HudItem hudItem) {
		if(hudItem != null) {
			hudItems.add(hudItem);
			Customize.log.info("Registered " + hudItem);
		} else {
			Customize.log.warn("Null HudItem. Skipping");
		}
	}
	
	public void unregisterHudItem(HudItem hudItem) {
		if(hudItems.contains(hudItem)) {
			hudItems.remove(hudItem);
			Customize.log.info("Unregisterd " + hudItem);
		} else {
			Customize.log.warn(hudItem + " isn't registered. Skipping.");
		}
	}
	
	HashSet<HudItem> hudItems;
}
