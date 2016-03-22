package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItemText;

public class HudItemTitle extends HudItemText {
	
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = 0;
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;	

	//	TODO: Make Title HudItem
	public HudItemTitle(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem() {
		
	}
	
	@Override
	public void updateTick() {
		
		super.updateTick();
	}
	
	@Override
	protected void init() {
		
	}

	@Override
	protected int getDeltaX() {
		return 0;
	}

	@Override
	protected int getDeltaY() {
		return 0;
	}

	@Override
	protected int getBGColor() {
		return 0;
	}

	@Override
	protected int getColor() {
		return 0;
	}

	@Override
	protected int getBGAlpha() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		return 0;
	}

	protected void setHeightAndWidth() {
		
	}

}
