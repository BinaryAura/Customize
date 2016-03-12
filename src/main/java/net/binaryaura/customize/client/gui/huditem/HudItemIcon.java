package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemIcon extends HudItem{

	public HudItemIcon(String name) {
		super(name);
		type = HudItemType.ICON;
	}
	
	@Override
	public void renderHUDItem() {
		
	}
}
