package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

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
		size = getSize();
		setHeightAndWidth();
	}
	
	protected void init() {
		style = DFLT_STY;
	}

	private static boolean print = true;
	
	/**
	 * Height and Width are set and the string is rendered.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		if(string == null || string.equals("")) return;
		GlStateManager.pushMatrix();
		if(size > 0 && size != 1.0)
			GL11.glScalef(size, size, size);
			GL11.glTranslated((float)(-x * (size - 1) / size), (float)(-y * (size - 1) / size), 0.0f);
		int textX = x + getDeltaX();
		int textY = y + getDeltaY();
		int styleAdd = (style == Style.SHADOWED ? 1 : style == Style.OUTLINED ? 2 : 0);
		if(getBGAlpha() > 0) {
			bgColor = getBGColor() + (getBGAlpha() << 24);
			Gui.drawRect(textX - (style == Style.OUTLINED ? 1 : 0), textY - (style == Style.OUTLINED ? 1 : 0), textX + fontRenderer.getStringWidth(string) + styleAdd, textY + fontRenderer.FONT_HEIGHT + styleAdd, bgColor);
			}
		if(getAlpha() > 0) {
			color = getColor() + (getAlpha() << 24);
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
		GlStateManager.popMatrix();
	}
	
	/**
	 * Sets Height and Width of the bar based on the orientation.
	 */
	protected void setHeightAndWidth() {
		width = (int) (fontRenderer.getStringWidth(string) * size);
		height = (int) (fontRenderer.FONT_HEIGHT * size);
	}
	
	/**
	 * Sets the text to be displayed and adjust the Height and width
	 */
	protected void setString(String string) {
		this.string = string;
		setHeightAndWidth();
		print = true;
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
	 * This method calculates the size of the entire text.
	 * 
	 * @return the size of the entire text.
	 */
	protected abstract float getSize();
	
	/**
	 * The color and alpha of the background of the text.
	 */
	protected int bgColor;
	
	/**
	 * The color and alpha of the font text.
	 */
	protected int color;
	
	/**
	 * The size of the text.
	 */
	protected float size;
	
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
	protected static FontRenderer fontRenderer = mc.fontRendererObj;
}
