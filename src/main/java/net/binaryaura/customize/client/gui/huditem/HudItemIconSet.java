package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItemIconSet extends HudItem {

	public HudItemIconSet(String name) {
		super(name);
		type = HudItemType.ICON_SET;
	}

	@Override
	public void renderHUDItem() {
		// TODO Auto-generated method stub

	}

}
