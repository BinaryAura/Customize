package net.binaryaura.customize.client.gui.huditem;

import java.util.LinkedHashSet;

import net.binaryaura.customize.client.gui.Sprite;
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
	
	@Override
	public void renderLayer(int layer) {
		for(int i = 0; i < 10; i++) {
			switch(orientation) {
				case RIGHT:
					
			}
		}
	}
	
	protected LinkedHashSet<Sprite> textures;
}
