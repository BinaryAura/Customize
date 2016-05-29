package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemBar;

/**
 * Bar that tracks the charge of the jump of the horse that
 * the player has mounted. JUMP is a {@link HudItemBar} and
 * uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemBar
 */
public class HudItemJump extends HudItemBar {
	 
	/**
	 * Relative x-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the bar will be rendered if no save
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
	
	/**
	 * The direction the bar will fill when rendered when no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Jump Bar HudItem
	
	/**
	 * Constructs an instance of the JUMP bar with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the bar.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemJump(String name) {
		super(name);
	}
	
	/**
	 * Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered.
	 * 
	 * @param x			The x-value of the upper left corner.
	 * @param y			The y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		super.renderHUDItem(x, y);
	}

	/**
	 * Initializes class specific fields such as location,
	 * orientation, and textures.
	 */
	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		x = DFLT_X;
		y = DFLT_Y;
	}

	/**
	 * Gets the fraction of the specified layer that should
	 * be displayed.
	 * 
	 * @param layer		The index of the layer in question.
	 * 
	 * @return the fraction of layer that should be displayed (between 0 and 1, inclusive).
	 */
	@Override
	protected float getAmount(int layer) {
		return 0;
	}
	
	/**
	 * Gets the textures to be used when displaying the bar
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	@Override
	protected SpriteSet getDemoSpriteSet() {
		return null;
	}

	/**
	 * Get the textures to be used during game-play. These will
	 * be the same as {@link #getDemoSpriteSet()}.
	 * 
	 * @return the textures to be used for game-play.
	 */
	@Override
	protected SpriteSet getLayers() {
		return null;
	}

}
