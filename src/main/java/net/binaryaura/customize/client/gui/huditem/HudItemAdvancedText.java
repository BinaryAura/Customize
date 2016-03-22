package net.binaryaura.customize.client.gui.huditem;

public abstract class HudItemAdvancedText extends HudItem{

	//	TODO: Set up Advanced Text Class
	public HudItemAdvancedText(String name) {
		super(name);
	}

	@Override
	protected void init() {

	}

	protected abstract int getBGAlpha();
	protected abstract int getBGColor(int line);
	protected abstract int getAlpha();
	protected abstract int getColor(int line);
}
