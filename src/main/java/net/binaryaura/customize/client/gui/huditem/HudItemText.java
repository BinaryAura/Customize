package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;

public abstract class HudItemText extends HudItem {

	public HudItemText(String name) {
		super(name);
		type = HudItemType.TEXT;
	}

	@Override
	public void renderHUDItem() {
		// TODO Auto-generated method stub

	}

}
