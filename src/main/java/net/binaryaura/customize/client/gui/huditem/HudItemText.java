package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
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
	 * Default demo string for the preview screen.
	 */
	protected final String DFLT_STR = Character.toUpperCase(name.charAt(0)) + name.substring(1);
	
	/**
	 * Default size of the string to be printed.
	 */
	protected final float DFLT_SIZE = 1.0f;
	
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
	
	/**
	 * Height and Width are set and the string is rendered.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		if(isInPreview()) {
			string = getDemoString();
		} else {
			string = getString();
		}
		if(string == null || string.equals("")) return;
		GlStateManager.pushMatrix();
		if(size > 0 && size != 1.0)
			GL11.glScalef(size, size, size);
			GL11.glTranslated((float)(-x * (size - 1) / size), (float)(-y * (size - 1) / size), 0.0f);
		int textX = x + getDeltaX();
		int textY = y + getDeltaY();
		bgColor = getBGColor() + (getBGAlpha() << 24);
		color = getColor() + (getAlpha() << 24);

		renderIcon(textX, textY, (Sprite) null);
		GlStateManager.popMatrix();
	}
	
	
	@Override
	protected void renderIcon(int x, int y, SpriteSet sprites) {
		int styleAdd = (style == Style.SHADOWED ? 1 : style == Style.OUTLINED ? 2 : 0);
		Gui.drawRect(x - (style == Style.OUTLINED ? 1 : 0), y - (style == Style.OUTLINED ? 1 : 0), x + fontRenderer.getStringWidth(string) + styleAdd, y + fontRenderer.FONT_HEIGHT + styleAdd, bgColor);
	
		switch(style) {
			case OUTLINED:
				fontRenderer.drawString(string, x + 1, y, 0);
				fontRenderer.drawString(string, x - 1, y, 0);
				fontRenderer.drawString(string, x, y + 1, 0);
				fontRenderer.drawString(string, x, y - 1, 0);
			case NORMAL:
				fontRenderer.drawString(string, x, y, color);
				break;
			case SHADOWED:
				fontRenderer.drawStringWithShadow(string, x, y, color);
				break;
		}		
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
		if(string != null && (this.string != null && !this.string.equals(string) || this.string == null)) {
			this.string = string;
			setHeightAndWidth();
		}
	}
	
	/**
	 * This method calculates horizontal movement of the entire text.
	 * 
	 * @return the horizontal movement of the entire text.
	 */
	protected int getDeltaX() {
		return 0;
	}
	
	/**
	 * This method calculates vertical movement of the entire text.
	 * 
	 * @return the vertical movement of the entire text.
	 */
	protected int getDeltaY() {
		return 0;
	}
	
	/**
	 * This method calculates the background color of the entire text.
	 * 
	 * @return the background color of the entire text.
	 */
	protected int getBGColor() {
		return WHITE;
	}
	
	/**
	 * This method calculates the color of the entire text.
	 * 
	 * @return the color of the entire text.
	 */
	protected int getColor() {
		return BLACK;
	}
	
	/**
	 * This method calculates the background alpha of the entire text.
	 * 
	 * @return the background alpha of the entire text.
	 */
	protected int getBGAlpha() {
		return 0x0;
	}
	
	/**
	 * This method calculates the alpha of the entire text.
	 * 
	 * @return the alpha of the entire text.
	 */
	protected int getAlpha() {
		return 0xFF;
	}
	
	/**
	 * This method calculates the size of the entire text.
	 * 
	 * @return the size of the entire text.
	 */
	protected float getSize() {
		return DFLT_SIZE;
	}
	
	/**
	 * This method formulates the string to be printed.
	 * 
	 * @return the string to be printed.
	 */
	protected abstract String getString();
	
	/**
	 * This method formulates the string to be printed while in the
	 * preview screen.
	 * 
	 * @return the string to be printed during the preview screen.
	 */
	protected String getDemoString() {
		return DFLT_STR;
	}
	
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
	private String string;
	
	/**
	 * How the text is to be rendered.
	 */
	protected Style style;
	
	/**
	 * Renderer for the text.
	 */
	protected static FontRenderer fontRenderer = mc.fontRendererObj;
}
