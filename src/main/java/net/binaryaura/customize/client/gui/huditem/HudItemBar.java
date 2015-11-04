package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItemBar extends HudItem {

	public HudItemBar(String name) {
		super(name);
		type = HudItemType.BAR;
	}
	
	@Override
	public void renderHUDItem() {
		

	}

}
