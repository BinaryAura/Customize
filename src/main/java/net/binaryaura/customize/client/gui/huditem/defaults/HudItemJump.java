package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemTexturedBar;

public class HudItemJump extends HudItemTexturedBar {
	
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = 0;
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Jump Bar HudItem
	public HudItemJump(String name) {
		super(name);
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
