package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItem {

	protected String name;
	protected HudItemType type;
	protected Random rand;
	
	public HudItem(String name){
		this.name = name;
		mc = Minecraft.getMinecraft();
	}
	
	public String getName() {
		return name;
	}
	
	public HudItemType getType() {
		return type;
	}
	
	public abstract void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent);

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected Minecraft mc;
}
