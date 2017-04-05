package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItemText;

/**
 * Text displaying scores, objectives, etc. as according to the
 * sessions settings. OBJECTIVE is a {@link HudItemText} and uses
 * its constructor and renderer.
 * 
 * OBJECTIVE may prove more complex than HudItemText can handle.
 * In such case a more advanced version capable of columns and
 * other formating must be made for this HudItem.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemText
 */
public class HudItemObjective extends HudItemText {

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
	 * The reference point for the x and y values when no save entry
	 * is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;	
	
	//	TODO: Make Objective HudItem
	
	/**
	 * Constructs an instance of the OBJECTIVE object with the specified
	 * <code>name</code>. This includes setting initial location values,
	 * orientation, and fetching the textures for the text.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemObjective(String name) {
		super(name);
	}
	
	@Override
	public void renderHUDItem(int x, int y) {
	}

	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		x = DFLT_X;
		y = DFLT_Y;
	}
	
	@Override
	protected int getColor() {
		return WHITE;
	}

	@Override
	protected String getString() {
		return null;
	}
}
