package net.binaryaura.customize.client.gui.huditem;

/**
 * This HUDItem is a collection of icons on the screen. 
 * Specific instances should extend this class. Default
 * values should be overridden in the extended class.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemIcon
 */
public abstract class HudItemIconSet extends HudItem {

	//	TODO: Set up IconSet HudItem
	
	/**
	 * Constructs an instance of a Icon Set with the specified
	 * <code>name</code>. Icon Set characteristics are set here
	 * along with the default values. The default values can be
	 * overridden by the specific bar's class.
	 * 
	 * @see		HudItem
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemIconSet(String name) {
		super(name);
	}

	/**
	 * Height and Width are set.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {

	}
	
	/**
	 * Sets Height and Width of the bar based on the orientation.
	 */
	@Override
	protected void setHeightAndWidth() {
		
	}
}
