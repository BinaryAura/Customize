package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItemIcon extends HudItem{

	public HudItemIcon(String name) {
		super(name);
		type = HudItemType.ICON;
	}
	
	@Override
	public void renderHUDItem() {
		
	}

}