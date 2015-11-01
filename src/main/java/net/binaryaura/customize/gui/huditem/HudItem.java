package net.binaryaura.customize.gui.huditem;

public abstract class HudItem {

	protected String name;
	protected HudItemType type;
	
	public String getName() {
		return name;
	}
	
	public HudItemType getType() {
		return type;
	}
	
	public abstract void renderHUDItem();

	@Override
	public String toString() {
		return "HUDItem " + name;
	}
	
	
	
}
