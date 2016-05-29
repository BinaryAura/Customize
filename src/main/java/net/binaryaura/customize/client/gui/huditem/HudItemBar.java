package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.util.MathHelper;

/**
 * This HUDItem acts as a bar. Made of a single icon, it tracks
 * values by a fraction of its whole. By assigning more
 * than just the background and a single layer, the Bar can
 * be set to track more than one value.
 * 
 * Specific instances should extend this class. Default values
 * should be overridden in the extended class.
 * 
 * @author	BinaryAura
 * @see 	HudItem
 */
public abstract class HudItemBar extends HudItem {

	/**
	 * Constructs an instance of a Bar with the specified
	 * <code>name</code>. Bar characteristics are set here
	 * along with the default values. The default values
	 * can be overridden by the specific bar's class.
	 * 
	 * @see		HudItem
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemBar(String name) {
		super(name);
		canRotate = true;
		
	}
	
	/**
	 * Height and Width are set, then the bar is translated
	 * and rotated based on its orientation. The texture and
	 * specific attributes are obtained from the subclass.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		
		SpriteSet sprites = getLayers();
		setHeightAndWidth();
		
		GL11.glTranslated(getTranslateX(x, y), getTranslateY(x, y), 0.0F);
		GL11.glRotatef(getRotation(), 0.0F, 0.0F, 1.0F);
		
		bind(sprites.getLocation());
		for(int i = 0; i < layers.getAmount() + 1; i++) {
			int fill = MathHelper.ceiling_float_int(getAmount(i)*layers.getWidth());
			guiRenderer.drawTexturedModalRect(x, y, sprites.getSprite(i).getX(), sprites.getSprite(i).getY(), fill, layers.getHeight());
		}
		mc.mcProfiler.endSection();
	}
	
	/**
	 * Sets Height and Width of the bar based on the orientation.
	 */
	@Override
	protected void setHeightAndWidth() {
		switch(orientation) {
			case DOWN:
			case UP:
				width = layers.getHeight();
				height = layers.getWidth();
				break;
			case RIGHT:
			case LEFT:
				width = layers.getWidth();
				height = layers.getHeight();
				break;
		}
	}
	
	/**
	 * Determines the rotation in degrees to obtain the orientation
	 * on the screen.
	 * 
	 * @return rotation, in degrees, to obtain the orientation on the
	 * screen.
	 */
	private float getRotation() {
		switch(orientation) {
			case DOWN:
				return 90.0F;
			case LEFT:
				return 180.0F;
			case UP:
				return -90.0F;
			default:
				return 0.0F;
		}
	}
	
	/**
	 * Determines the horizontal translation to put the bar in the
	 * correct location on the screen.
	 * 
	 * @param x		The point in which the x and y values are
	 * 				relative to.
	 * @param y		The point in which the x and y values are
	 * 				relative to.
	 * 
	 * @return horizontal translation to put the bar in the correct location
	 */
	private int getTranslateX(int x, int y) {
		switch(orientation) {
			case DOWN:
				return x + y + layers.getHeight();
			case LEFT:
				return 2*x + layers.getWidth();
			case UP:
				return x - y;
			default:
				return 0;
		}
	}
	
	/**
	 * Determines the vertical translation to put the bar in the
	 * correct location on the screen.
	 * 
	 * @param x		The point in which the x and y values are
	 * 				relative to.
	 * @param y		The point in which the x and y values are
	 * 				relative to.
	 * 
	 * @return vertical translation to put the bar in the correct location
	 */
	private int getTranslateY(int x, int y) {
		switch(orientation) {
			case DOWN:
				return y - x;
			case LEFT:
				return 2*y + layers.getHeight();
			case UP:
				return x + y + layers.getWidth();
			default:
				return 0;
		}
	}

	/**
	 * Gets the fraction of the specified layer that should
	 * be displayed.
	 * 
	 * @param layer		The index of the layer in question.
	 * 
	 * @return the fraction of layer that should be displayed (between 0 and 1, inclusive).
	 */
	protected abstract float getAmount(int layer);
	
	/**
	 * Gets the textures to be used when displaying the bar
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	protected abstract SpriteSet getDemoSpriteSet();
	
	/**
	 * Get the textures to be used during game-play. These will
	 * be the same as {@link #getDemoSpriteSet()}.
	 * 
	 * @return the textures to be used for game-play.
	 */
	protected abstract SpriteSet getLayers();
	
	/**
	 * Textures for the bar. Multiple layers could be made
	 * to represent different situations or values for textures. 
	 */
	protected LayeredSprite layers;
}
