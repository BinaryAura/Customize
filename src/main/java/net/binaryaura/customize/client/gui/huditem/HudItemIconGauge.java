package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemIconGauge extends HudItem {

	public HudItemIconGauge(String name) {
		super(name);
		type = HudItemType.ICON_GUAGE;
	}

	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		
	}
}
