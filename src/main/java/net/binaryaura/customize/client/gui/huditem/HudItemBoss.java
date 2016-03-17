package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.SpriteSet;

public class HudItemBoss extends HudItemBar {

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
	protected SpriteSet getLayers() {
		return null;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return null;
	}

}
