package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;

public class HudItemMountHealth extends HudItemIconGauge {
	
	private static final int DFLT_DEMO_AMT = 20;
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = 0;
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Mount Health Gauge HudItem
	public HudItemMountHealth(String name) {
		super(name);
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		return null;
	}

	@Override
	protected SpriteSet getDemoSpriteSet() {
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

	@Override
	protected float getDemoAmount() {
		return DFLT_DEMO_AMT;
	}
}
