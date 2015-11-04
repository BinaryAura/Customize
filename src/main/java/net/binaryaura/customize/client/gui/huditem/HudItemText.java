package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.FontRenderer;

public abstract class HudItemText extends HudItem {

	public HudItemText(String name) {
		super(name);
		type = HudItemType.TEXT;
		fontRenderer = mc.fontRendererObj;
	}

	@Override
	public void renderHUDItem() {
		// TODO Auto-generated method stub

	}

	protected FontRenderer fontRenderer;
}
