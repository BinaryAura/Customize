package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemTexturedBar;

public class HudItemBoss extends HudItemTexturedBar {
	
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = 0;
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Boss Health Bar HudItem
	//		Bar for health
	//		Text for name
	public HudItemBoss(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected float getAmount(int layer) {
		return 0;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return null;
	}

	@Override
	protected SpriteSet getLayers() {
		return null;
	}

}
