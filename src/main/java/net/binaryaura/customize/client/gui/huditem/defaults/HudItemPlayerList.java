package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItemText;

/**
 * Text displaying all players, a scoreboard statistic, and
 * connection strength. PLAYERLIST is a {link HudItem Text}
 * and uses its constructor and renderer.
 * 
 * PLAYERLIST has multiple text objects and icon gauges.
 * HudItemText will NOT be able to handle this HudItem.
 * 
 * @author	BinaryAura
 * @see 	HudItem
 * @see		HudItemText
 */
public class HudItemPlayerList extends HudItemText {
	
	/**
	 * Relative x-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = 0;
	
	/**
	 * The reference point for the x and y value when no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;	

	//	TODO: Make Player List HudItem
	//		Icon/IconSet for player heads
	//		IconGauge for Signal
	//		Text for names
	//		IconSet/Text for Scoreboard display
	
	/**
	 * Constructs an instance of the PLAYERLIST object with the
	 * specified <code>name</code>. This includes setting initial
	 * location values, orientation, and fetching the textures for
	 * the text.
	 * @param name
	 */
	public HudItemPlayerList(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
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
