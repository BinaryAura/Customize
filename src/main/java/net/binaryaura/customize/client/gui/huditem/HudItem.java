package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItem {

	protected String name;
	protected HudItemType type;
	
	public String getName() {
		return name;
	}
	
	public HudItemType getType() {
		return type;
	}
	
	public abstract void renderHUDItem();

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	
	
}
