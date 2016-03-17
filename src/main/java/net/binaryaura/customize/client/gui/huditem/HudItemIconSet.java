package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItemIconSet extends HudItem {

	//	TODO: Set up IconSet HudItem
	public HudItemIconSet(String name) {
		super(name);
		type = HudItemType.ICON_SET;
	}

	@Override
	public void renderHUDItem(int x, int y) {

	}

}
