package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconSet;

/**
 * Collection of objects in the HOTBAR. The HOTBAR has three parts:
 * 
 *  1. Background
 *  2. IconSet of ItemIcons
 *  3. Selection
 *  
 * HOTBAR is a {@link HudItemIconSet} and uses its constructor
 * and renderer. Most likely this object is a combination of
 * different objects. This would change its type.
 * 
 * @author 	BinaryAura
 * @see		HudItem
 * @see		HudItemIconGauge
 */
public class HudItemHotbar extends HudItemIconSet {
	
	/**
	 * Relative x-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see GuiScreenAdjustHud
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
	 * @see	HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	
	/**
	 * The direction the bar will fill when rendered when no save
	 * entry is found.
	 * 
	 * @see HudItem.Orientation
	 */
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;

	//	TODO: Make Hotbar HudItem
	//		Text for No. in stack
	//		Icon for Cursor
	//		IconGauge for Background and items
	
	/**
	 * Constructs an instance of the HOTBAR object with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the gauge.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemHotbar(String name) {
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
}
