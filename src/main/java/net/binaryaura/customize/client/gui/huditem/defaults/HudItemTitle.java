package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemText;

/**
 * Primary Title Text. TITLE fades in then, fades out
 * after a brief period. TITLE is a {@link HudItemText}
 * and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see 	HudItemText
 */
public class HudItemTitle extends HudItemText {
	
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

	//	TODO: Make Title HudItem
	
	/**
	 * Constructs an instance of the TITLE object with the specified
	 * <code>name</code>. This includes setting initial location values,
	 * orientation, and fetching the textures for the text.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemTitle(String name) {
		super(name);
	}
	
	
	@Override
	public void renderHUDItem(int x, int y) {
	}
	
	@Override
	protected void init() {
		
	}
	
	@Override
	public void updateTick() {
		
		super.updateTick();
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
