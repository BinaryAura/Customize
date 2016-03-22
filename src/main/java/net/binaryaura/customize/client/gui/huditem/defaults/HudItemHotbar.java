package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItemIconSet;

public class HudItemHotbar extends HudItemIconSet {
	
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = 0;
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Hotbar HudItem
	//		Text for No. in stack
	//		Icon for Cursor
	//		IconGauge for Background and items
	public HudItemHotbar(String name) {
		super(name);
	}

	@Override
	protected void init() {
		
	}
}
