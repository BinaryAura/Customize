package net.binaryaura.customize.client.gui.huditem;

import javafx.scene.layout.Background;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;

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
	
	/**
	 * The structure that the Icon Set will be rendered as.
	 * This determines where the icons will be rendered.
	 * There are two types: <code>SOLID</code>, and
	 * <code>HOLLOW</code>.
	 * 
	 * <p><b><code>SOLID</code>:</b> Renders the icons in
	 * a rectangular block. This will build depending on the orientation
	 * and the max height and max width. Rotation changes which side the
	 * block is built from. If the block isn't square, then height and width
	 * are switched as well.
	 * 
	 * <p><b><code>HOLLOW</code>:</b> Renders the icons in a rectangular block
	 * with a space in the middle. Max height and Max width in this case will
	 * represent the size of the space in the middle. Rotation switches the height
	 * and width as well as the starting point for rendering the icons.
	 * 
	 * <p>For maxHeight and maxWidth, a value of -1, means unlimited. A value of
	 * 0 in either will cause the icon set not to be rendered. A icon will not be
	 * rendered if it cannot fit in the maxHeight and maxWidth. 
	 */
	public static enum Structure {
		SOLID, HOLLOW;
		
		protected int maxHeight = -1;
		protected int maxWidth = -1;
	}
	
	/**
	 * Default value for the structure of this Icon Set.
	 */
	protected static final Structure DFLT_STR = Structure.SOLID;
	
	/**
	 * Default value of the structure maxHeight Value.
	 */
	protected static final int DFLT_MAX_HGT = 1;
	
	/**
	 * Default value of the structure maxWidth Value. This is unlimited.
	 */
	protected static final int DFLT_MAX_WDTH = -1;

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
	 * Toggles the <code>flip</code> state.
	 */
	public void flip() {
		flip = !flip;
	}

	/**
	 * Setter for <code>structure</code>.
	 * 
	 * @return	The structure of the HUDItem
	 */
	public Structure getStructure() {
		return structure;
	}
	
	/**
	 * Getter for the maxHeight of the structure.
	 * 
	 * @return	The maximum height for the structure, or the height of the space.
	 */
	public int getStructureHeight() {
		return structure.maxHeight;
	}
	
	/**
	 * Getter for the maxWidth of the structure.
	 * 
	 * @return	The maximum width for the structure, or the width of the space.
	 */
	public int getStructureWidth() {
		return structure.maxWidth;
	}
	
	/**
	 * Getter for <code>flip</code>
	 */
	public boolean isFlipped() {
		return flip;
	}
	
	/**
	 * Height and Width are set.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		if(structure.maxHeight != 0 && structure.maxWidth != 0) return;
		mc.mcProfiler.startSection(name);
		bind(icons.getLocation());
		for(int i = 0; i < getAmount(); i++) {
			switch(structure) {
				case SOLID:
					switch(orientation) {
						case LEFT:
							break;
						case RIGHT:
							break;
						case DOWN:
							break;
						case UP:
							break;
					}
					break;
				case HOLLOW:
					switch(orientation) {
						case LEFT:
							break;
						case RIGHT:
							break;
						case DOWN:
							break;
						case UP:
							break;
					}
					break;
			}
			renderIcon(x, y, new SpriteSet(backgrounds.getSprite(i), icons.getSprite(i)));
		}
		mc.mcProfiler.endSection();
	}
	
	@Override
	protected void renderIcon(int x, int y, SpriteSet sprites) {
		Sprite bg = sprites.getSprite(0);
		Sprite sprite = sprites.getSprite(1);
		if(bg != null)
			guiRenderer.drawTexturedModalRect(x, y, bg.getX(), bg.getY(), sprites.getWidth(), sprites.getHeight());
		if(sprite != null)
			guiRenderer.drawTexturedModalRect(x, y, sprite.getX(), sprite.getY(), sprites.getWidth(), sprites.getHeight());
	}
	
	/**
	 * Set structure height. This sets the maximum height in icons
	 * that will be rendered before moving to the next column for a
	 * <code>SOLID</code> Icon Set or the height of the space in the
	 * center of a <code>HOLLOW</code> Icon Set.
	 * 
	 * @see Structure
	 * 
	 * @param	width		The width of the space.
	 */
	public void setStructureHeight(int height) {
		structure.maxHeight = height;
	}
	
	/**
	 * Set structure width. This sets the maximum width in icons
	 * that will be rendered before moving to the next row for a
	 * <code>SOLID</code> Icon Set or the width of the space in the
	 * center of a <code>HOLLOW</code> Icon Set.
	 * 
	 * @see Structure
	 * 
	 * @param	width		The width of the space.
	 */
	public void setStructureWidth(int width) {
		structure.maxWidth = width;
	}
	
	/**
	 * Gets the amount of icons to be rendered.
	 * 
	 * @return The number of icons in the icon set.
	 */
	protected abstract int getAmount();
	
	/**
	 * Gets the textures to be used when displaying these position
	 * in the structure.
	 * 
	 * @param icon	Position in the structure.
	 * 
	 * @return	the textures to rendered in this position. Expected: 1 or 2 sprites
	 */
	protected abstract SpriteSet getIconSpriteSet(int icon);
	
	@Override
	protected void init() {
		canRotate = true;
		structure = DFLT_STR;
		structure.maxHeight = DFLT_MAX_HGT;
		structure.maxWidth = DFLT_MAX_WDTH;
	}
	
	/**
	 * Sets Height and Width of the bar based on the orientation.
	 */
	@Override
	protected void setHeightAndWidth() {
	}
	
	/**
	 * Setter for <code>structure</code>.
	 * 
	 * @param structure		structure to set the HUDItem to.
	 */
	protected void setStructure(Structure structure) {
		
	}
	
	/**
	 * Textures for the Icon Set.
	 */
	protected SpriteSet icons;
	
	/**
	 * Texture for the background set
	 */
	protected SpriteSet backgrounds;
	
	/**
	 * Structure of the Icon Set
	 */
	protected Structure structure;
	
	/**
	 * Flip state for the Icon Set
	 */
	protected boolean flip;
}
