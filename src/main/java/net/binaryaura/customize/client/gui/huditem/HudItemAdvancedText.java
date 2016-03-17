package net.binaryaura.customize.client.gui.huditem;

public abstract class HudItemAdvancedText extends HudItem{

	//	TODO: Set up Advanced Text Class
	public HudItemAdvancedText(String name) {
		super(name);
	}

	protected abstract int getBGColor(int line);

	protected abstract int getColor(int line);

	protected abstract int getBGAlpha();

	protected abstract int getAlpha();

	@Override
	protected void init() {

	}

}
