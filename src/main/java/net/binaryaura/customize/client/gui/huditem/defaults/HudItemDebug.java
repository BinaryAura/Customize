package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemText;

/**
 * Text that gives information concerning the game and the system its
 * run on. This cannot be moved and takes up the whole screen. DEBUG
 * is a {@link HudItemText} and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemText
 */
public class HudItemDebug extends HudItemText {

	/**
	 * Relative x-value where the icon will be rendered. This value
	 * cannot be changed.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the icon will be rendered. This value
	 * cannot be changed.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = 0;
	
	/**
	 * The reference point for the x and y value. DEBUG uses the
	 * TOPLEFT Anchor. This cannot be changed.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	
	//	TODO: Make Debug Text Screen 
	//		HudItem or otherwise
	
	/**
	 * Constructs an instance of the DEBUG screen(?!) with the specified
	 * <code>name</code>. This includes setting initial, and final location
	 * values, orientation, and fetching the textures for the bar.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemDebug(String name) {
		super(name);
		canMove = false;
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
	protected int getDeltaX() {
		return 0;
	}

	@Override
	protected int getDeltaY() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		return 0;
	}

	@Override
	protected int getBGAlpha() {
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

	/* (non-Javadoc)
	 * @see net.binaryaura.customize.client.gui.huditem.HudItemText#getSize()
	 */
	@Override
	protected float getSize() {
		return 0;
	}
}
