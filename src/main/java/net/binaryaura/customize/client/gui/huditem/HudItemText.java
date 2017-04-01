package net.binaryaura.customize.client.gui.huditem;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

/**
 * This HUDItem is text. The text that is rendered has one of
 * three styles: NORMAL, SHADOWED, or OUTLINED. It takes on
 * {@link #color} font color and a {@link #bgColor} colored
 * background. Specific instances should extend this class.
 * Default values should be overridden in the extended class.
 * 
 * @author	BinaryAura
 * @see 	HudItem
 */
public abstract class HudItemText extends HudItem {
	
	/**
	 * Default style for all Text Objects.
	 */
	protected static final Style DFLT_STY = Style.NORMAL;
	
	/**
	 * Types of styles that the text can don.
	 * 
	 * 	NORMAL: Simple Text with no special features.
	 * 	SHADOWED: Simple Text with a shadow to its lower right.
	 * 	OUTLINED: Simple Text with a one pixel wide outline.
	 */
	protected enum Style {
		NORMAL, SHADOWED, OUTLINED;
	}
	
	/**
	 * Constructs an instance of a Text Object with the specified
	 * <code>name</code>. Text characteristics are set here along
	 * with the default values. The default values can be overridden
	 * by the specific object's class.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemText(String name) {
		super(name);
		guiBackground = true;
		fontRenderer = mc.fontRendererObj;
		style = DFLT_STY;
	}

	/**
	 * Height and Width are set and the string is rendered.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		setHeightAndWidth();
		int textX = x + getDeltaX();
		int textY = y + getDeltaY();
		int styleAdd = (style == Style.SHADOWED ? 1 : style == Style.OUTLINED ? 2 : 0);
		if(getBGAlpha() > 0) {
			int bgColor = getBGColor() + (getBGAlpha() << 24);
			Gui.drawRect(textX - (style == Style.OUTLINED ? 1 : 0), textY - (style == Style.OUTLINED ? 1 : 0), textX + fontRenderer.getStringWidth(string) + styleAdd, textY + fontRenderer.FONT_HEIGHT + styleAdd, bgColor);
			}
		if(getAlpha() > 0) {
			int color = getColor() + (getAlpha() << 24);
			switch(style) {
				case OUTLINED:
					fontRenderer.drawString(string, textX + 1, textY, 0);
					fontRenderer.drawString(string, textX - 1, textY, 0);
					fontRenderer.drawString(string, textX, textY + 1, 0);
					fontRenderer.drawString(string, textX, textY - 1, 0);
				case NORMAL:
					fontRenderer.drawString(string, textX, textY, color);
					break;
				case SHADOWED:
					fontRenderer.drawStringWithShadow(string, textX, textY, color);
					break;
			}
		}
	}
	
	/**
	 * Sets Height and Width of the bar based on the orientation.
	 */
	protected void setHeightAndWidth() {
		width = fontRenderer.getStringWidth(string);
		height = fontRenderer.FONT_HEIGHT;
	}
	
	/**
	 * This method calculates horizontal movement of the entire text.
	 * 
	 * @return the horizontal movement of the entire text.
	 */
	protected abstract int getDeltaX();
	
	/**
	 * This method calculates vertical movement of the entire text.
	 * 
	 * @return the vertical movement of the entire text.
	 */
	protected abstract int getDeltaY();
	
	/**
	 * This method calculates the background color of the entire text.
	 * 
	 * @return the background color of the entire text.
	 */
	protected abstract int getBGColor();
	
	/**
	 * This method calculates the color of the entire text.
	 * 
	 * @return the color of the entire text.
	 */
	protected abstract int getColor();
	
	/**
	 * This method calculates the background alpha of the entire text.
	 * 
	 * @return the background alpha of the entire text.
	 */
	protected abstract int getBGAlpha();
	
	/**
	 * This method calculates the alpha of the entire text.
	 * 
	 * @return the alpha of the entire text.
	 */
	protected abstract int getAlpha();
	
	/**
	 * The color and alpha of the background of the text.
	 */
	protected int bgColor;
	
	/**
	 * The color and alpha of the font text.
	 */
	protected int color;
	
	/**
	 * Text to be rendered.
	 */
	protected String string;
	
	/**
	 * How the text is to be rendered.
	 */
	protected Style style;
	
	/**
	 * Renderer for the text.
	 */
	protected FontRenderer fontRenderer;
}
