package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.Minecraft;

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
	
	public abstract void renderHUDItem();

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected Minecraft mc;	
}
