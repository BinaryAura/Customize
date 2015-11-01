package net.binaryaura.customize.gui.hud;

public abstract class HUDItem {

	protected String name;
	protected HUDItemType type;
	
	public String getName() {
		return name;
	}
	
	public HUDItemType getType() {
		return type;
	}
	
	public abstract void renderHUDItem();

	@Override
	public String toString() {
		return "HUDItem " + name;
	}
	
	
	
}
