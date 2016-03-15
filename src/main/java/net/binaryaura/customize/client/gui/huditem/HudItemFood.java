package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.SpriteSet;

public class HudItemFood extends HudItemIconGauge {

	public HudItemFood(String name) {
		super(name);
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		return null;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return null;
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}

	@Override
	protected int getIconDeltaPara(int icon) {
		return 0;
	}

	@Override
	protected float getAmount() {
		return 0;
	}

	@Override
	protected void init() {
		
	}

}
