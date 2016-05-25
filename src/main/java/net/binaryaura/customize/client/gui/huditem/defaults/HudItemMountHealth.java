package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;

/**
 * Gauge that tracks the health of the player's mount. The mount
 * dies when this is depleted. MOUNTHEALTH is a {@link HudItemIconGauge}
 * and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HUDItem
 * @see		HUDItemIconGauge
 */
public class HudItemMountHealth extends HudItemIconGauge {
	
	/**
	 * Maximum value to be used when displaying the gauge in
	 * {@link GuiScreenAdjustHud}.
	 * 
	 * @see GuiScreenAdjustHud
	 */
	private static final int DEMO_AMT = 20;
	
	/**
	 * Relative x-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the gauge will be rendered if no save
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
	 * @see HudItem.Orientation
	 */
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Mount Health Gauge HudItem
	
	/**
	 * Constructs an instance of the MOUNTHEALTH gauge with the
	 * specified <code>name</code>. This includes setting initial
	 * location values, orientation, and fetching the textures
	 * for the gauge.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemMountHealth(String name) {
		super(name);
	}

	/**
	 * Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered then, calls the
	 * superclass's version of this method to render the gauge.
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
	 * Calculates parallel movement of each icon of the gauge.							 <3 <- <3 -> <3
	 * 
	 * @param icon		The index of the icon.  <3 0 <3 1 <3 2 <3 3 <3 4 <3 5 etc
	 * 
	 * @return parallel movement direction and distance. 
	 */
	@Override
	protected int getIconDeltaPara(int icon) {
		return 0;
	}

	/**																						   /\
	 * Calculates perpendicular movement of each icon of the gauge.		 				 <3    <3    <3
	 * 																						   \/
	 * @param icon		The index of the icon.
	 * 
	 * @return perpendicular movement direction and distance.
	 */
	@Override
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}

	/**
	 * Gets the maximum value for during game-play. AIR max value never
	 * changes.
	 * 
	 * @return the maximum value of MOUNTHEALTH.
	 */
	@Override
	protected float getAmount() {
		return 0;
	}

	/**
	 * Gets the maximum value to be used when displaying the gauge
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the maximum value of AIR as a preview.
	 */
	@Override
	protected float getDemoAmount() {
		return DEMO_AMT;
	}

	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	@Override
	protected SpriteSet getDemoSpriteSet() {
		return null;
	}
	
	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge during game-play.
	 * 
	 * @param icon		The index of the icon.		
	 * 
	 * @return the textures to be used in <code>icon</code>.
	 */
	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		return null;
	}
}
