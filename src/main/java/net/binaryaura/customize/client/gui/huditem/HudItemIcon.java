package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;

/**
 * This HUDItem is simply as image. It can have a background and
 * a foreground.
 * 
 * @author	BinaryAura
 * @see		HUDItem
 */
public abstract class HudItemIcon extends HudItem{
	
	/**
	 * Constructs an instance of an Icon with the specified
	 * <code>name</code>. Icon characteristics are set here
	 * along with the default values. The default values can
	 * be overridden by the specific icon's class.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemIcon(String name) {
		super(name);
	}
	
	/**
	 * Height and Width are reset. Then the icon is rendered.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		
		bind(icon.getLocation());
		guiRenderer.drawTexturedModalRect(x, y, icon.getX(), icon.getY(), width, height);
		mc.mcProfiler.endSection();
	}
	
	/**
	 * Sets Height and Width of the gauge based on the orientation.
	 */
	@Override
	protected void setHeightAndWidth() {
		width = icons.getWidth();
		height = icons.getHeight();
	}
	
	/**
	 * Texture for image.
	 */
	protected Sprite icon;
	
	/**
	 * Collection of textures for the image.
	 */
	protected SpriteSet icons;
}
