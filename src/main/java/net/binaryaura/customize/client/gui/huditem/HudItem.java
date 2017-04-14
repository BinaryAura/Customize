package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * An object making up the Heads UP Display. There are several
 * subclasses for HUDItems that behaves in different ways. Any
 * specific instance should extend one of those subclasses.
 * 
 * @author	BinaryAura
 *
 */
public abstract class HudItem implements Color{
	
	/**
	 * Reference Points for the x and y values for all HUDItems.
	 * 
	 *  <p><b><code>TOPLEFT</code>:</b> Upper Left corner of the screen. (0, 0)
	 *  <br><b><code>TOP</code>:</b> Middle of the Upper edge of the screen. (width/2, 0)
	 *  <br><b><code>TOPRIGHT</code>:</b> Upper Right corner of the screen. (width, 0)
	 *  <br><b><code>LEFT</code>:</b> Middle of the Left edge of the screen. (0, height/2)
	 *  <br><b><code>CENTER</code>:</b> Center of the screen. (width/2, height/2)
	 *  <br><b><code>RIGHT</code>:</b> Middle of the Right edge of the screen. (width, height/2)
	 *  <br><b><code>BOTTOMLEFT</code>:</b> Lower Left corner of the screen. (0, height)
	 *  <br><b><code>BOTTOM</code>:</b> Middle of the Lower edge of the screen. (width/2, height)
	 *  <br><b><code>BOTTOMRIGHT</code>:</b> Lower Right corner of the screen. (width, height)
	 *  <!--
	 *  ___________________________
	 *  |X           X           X|
	 *  |                         |
	 *  |X           X           X|
	 *  |                         |
	 *  |X___________X___________X|
	 *  
	 *  -->
	 */
	public static enum Anchor {
		TOPLEFT, TOP, TOPRIGHT, LEFT, CENTER, RIGHT, BOTTOMLEFT, BOTTOM, BOTTOMRIGHT;
		
		/**
		 * Calculates the x-value of the current Anchor
		 * 
		 * @return x-value of the current Anchor.
		 */
		public int getX() {
			switch(this) {
				case TOPLEFT:
				case LEFT:
				case BOTTOMLEFT:
					return 0;
				case TOP:
				case CENTER:
				case BOTTOM:
					return res.getScaledWidth() / 2;
				case TOPRIGHT:
				case RIGHT:
				case BOTTOMRIGHT:
					return res.getScaledWidth();
				default:
					return 0;
			}
		}
		
		/**
		 * Calculates the y-value of the current Anchor
		 * 
		 * @return y-value of the current Anchor
		 */
		public int getY() {
			switch(this) {
				case TOPLEFT:
				case TOP:
				case TOPRIGHT:
					return 0;
				case LEFT:
				case CENTER:
				case RIGHT:
					return res.getScaledHeight() / 2;
				case BOTTOMLEFT:
				case BOTTOM:
				case BOTTOMRIGHT:
					return res.getScaledHeight();
				default:
					return 0;
			}
		}		
	}
	
	/**
	 * <p>Directions of orientation for all applicable HUDItems.
	 * 
	 * <p><b><code>UP</code>:</b> Filling or stacking toward the upper edge of the screen.
	 * <br><b><code>RIGHT</code>:</b> Filling or stacking toward the right edge of the screen.
	 * <br><b><code>DOWN</code>:</b> Filling or stacking toward the lower edge of the screen.
	 * <br><b><code>LEFT</code>:</b> Filling or stacking toward the left edge of the screen.  
	 */
	public static enum Orientation {
		UP, RIGHT, DOWN, LEFT;
		
		/**
		 * Rotates the HUDItem counter-clockwise.
		 * 
		 * @return orientation counter-clockwise for the current orientation
		 */
		public Orientation left() {
			switch(this) {
				case LEFT:
					return UP;
				case UP:
					return RIGHT;
				case RIGHT:
					return DOWN;
				case DOWN:
					return LEFT;
				default:
					return null;
			}
		}
		
		/**
		 * Rotates the HUDItem clockwise.
		 * 
		 * @return orientation clockwise for the current orientation
		 */
		public Orientation right() {
			switch(this) {
				case RIGHT:
					return DOWN;
				case DOWN:
					return LEFT;
				case LEFT:
					return UP;
				case UP:
					return RIGHT;
				default:
					return null;
			}
		}
	}
	
	/**
	 * <p>States when the HudItem should be rendered. The player still have
	 * control over each hudItem with the <code>NORMAL</code> priority to
	 * move up and down in the foreground. But, <code>PRE</code> and
	 * <code>POST</code> render orders are set in code.
	 * 
	 * <p><b><code>PRE</code>:</b> Marks the HudItem to be rendered prior to normal
	 * HudItems. These are HudItems that should be rendered beneath data
	 * oriented HudItems. These are graphics that do not block the data
	 * oriented HudItems (e.g. Vignette, Portal, Helmet).
	 * 
	 * <p><b><code>NORMAL</code>:</b> Marks the HudItem to be rendered normally.
	 * These are HudItems that show data to the player
	 * (e.g. Health, Armor, Air).
	 * 
	 * <p><b><code>POST</code>:</b> Marks the HudItems to be rendered after normal
	 * HudItems. These are HudItems that should be rendered beneath data
	 * oriented HudItems. These are graphics that block data oriented HudItems.
	 * These are typically event based graphics
	 * (e.g. Chat, Sleep Fade, PlayerList).
	 */
	public static enum RenderPriority {
		PRE, NORMAL, POST;
	}
	
	/**
	 * Default flip state for all HUDItems
	 */
	protected static final boolean DFLT_FLIP = false;
	
	/**
	 * Relative x-value where the gauge will be rendered if no save
	 * entry is found for all HUDItems.
	 * 
	 * @see	HudItem.Anchor
	 */
	protected static final int DFLT_X = 0;
	
	/**
	 * Relative xy-value where the gauge will be rendered if no save
	 * entry is found for all HUDItems.
	 * 
	 * @see	HudItem.Anchor
	 */
	protected static final int DFLT_Y = 0;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found for all HUDItems. 
	 * 
	 * @see	HudItem.Anchor
	 */
	protected static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	
	/**
	 * The direction the bar will fill when rendered when no save
	 * entry is found for all HUDItems.
	 * 
	 * @see HudItem.Orientation
	 */
	protected static final Orientation DFLT_ORIEN = Orientation.RIGHT;
	
	/**
	 * The priority of the HUDItem during the rendering process.
	 * 
	 * @see HudItem.RenderPriority
	 */
	protected static final RenderPriority DFLT_PRIO = RenderPriority.NORMAL;
	
	/**
	 * Random number generator for all HUDItems.
	 */
	protected static final Random rand = new Random();
	
	/**
	 * Partial Ticks passed by the event
	 */
	protected static float partialTicks;
	
	/**
	 * Event that governs this render.
	 */
	protected static RenderGameOverlayEvent eventParent;
	
	/**
	 * Resolution of the Minecraft Window
	 */
	protected static ScaledResolution res = new ScaledResolution(HudItemManager.getInstance().mc);
	
	/**
	 * Update counter for synchronized actions.
	 */
	protected static int updateCounter = 0;
	
	/**
	 * Last system time checked.
	 */
	protected static long lastSystemTime;
	
	/**
	 * Renderer for the HUDItem.
	 */
	protected static Gui guiRenderer = new Gui();
	
	/**
	 * Logger for the Mod.
	 */
	protected static final Logger log = Customize.log;
	
	/**
	 * Main loop for the game. This has many fields needed for HUDItems.
	 */
	protected static final Minecraft mc = HudItemManager.getInstance().mc;
	
	/**
	 * Sets the event for the HudItems for this renderTick.
	 * 
	 * @param eventParent		The event to use as the eventParent
	 */
	public static final void setEvent(RenderGameOverlayEvent eventParent) {
		HudItem.eventParent = eventParent;
		res = eventParent.resolution;
		partialTicks = eventParent.partialTicks;
	}
	
	/**
	 * Constructs an instance of the HUDItem with the specified
	 * <code>name</code>. HUDItem characteristics are set here along
	 * with the default values. The default values can be overridden
	 * by the specific HUDItem type's class.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItem(String name){
		this.name = name.toLowerCase();
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		priority = DFLT_PRIO;
		x = DFLT_X;
		y = DFLT_Y;
		init();
		// preRender();
	}
	
	/**
	 * Getter for {@link #canMove}.
	 * 
	 * @return flag for whether the HUDItem can move.
	 */
	public final boolean canMove() {
		return canMove;
	}
	
	/**
	 * Getter for {@link #canRotate}.
	 * 
	 * @return flag for whether the HUDItem can rotate.
	 */
	public final boolean canRotate() {
		return canRotate;
	}	
	
	/**
	 * Getter for the HUDItem's Anchor
	 * 
	 * @see Anchor
	 * 
	 * @return	The anchor for this HUDItem
	 */
	public final Anchor getAnchor() {
		return anchor;
	}
	
	/**
	 * Calculates the x-value of this gauge's button counterpart.  
	 * 
	 * @return x-value of this gauge's button counterpart in {@link GuiScreenAdjustHud}.
	 */
	@Deprecated
	public int getButtonX() {
		return getX();
	}
	
	/**
	 * Calculates the y-value of this gauge's button counterpart.  
	 * 
	 * @return y-value of this gauge's button counterpart in {@link GuiScreenAdjustHud}.
	 */
	@Deprecated
	public int getButtonY() {
		return getY();
	}
	
	/**
	 * Getter for the HUDItem's height.
	 * 
	 * @return height of the HUDItem.
	 */
	public final int getHeight() {
		return height;
	}
	
	/**
	 * Getter for the HUDItem's ID.
	 * 
	 * @return Identification of {@link GuiScreenAdjustHud}.
	 */
	public final int getId() {
		return id;
	}
	
	/**
	 * Getter for the HUDItem's name.
	 * 
	 * @return		The name of the HUDItem.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Direction the HUDItem will fill or stack when rendered.
	 * 
	 * @see Orientation
	 * 
	 * @return direction the HUDItem will fill or stack when rendered.
	 */
	public final Orientation getOrientation() {
		return orientation;
	}
	
	/**
	 * Priority of the HUDItem during rendering.
	 * 
	 * @see RenderPriority
	 * 
	 * @return priority of the HUDItem.
	 */
	public final RenderPriority getPriority() {
		return priority;
	}
	
	/**
	 * Getter for the HUDItem's width.
	 * 
	 * @return width of the HUDItem. 
	 */
	public final int getWidth() {
		return width;
	}
	
	/**
	 * Calculates the absolute value for the HUDItem's upper left corner.
	 * 
	 * @return absolute value of the upper left corner.
	 */
	public final int getX() {
		int pxlX = anchor.getX() + x;
		switch(anchor) {
			case TOPLEFT:
			case LEFT:
			case BOTTOMLEFT:
				return pxlX;
			case TOP:
			case CENTER:
			case BOTTOM:
				return pxlX -= width / 2;
			case TOPRIGHT:
			case RIGHT:
			case BOTTOMRIGHT:
				return pxlX -= width;
			default:
				return pxlX;
		}
	}
	
	/**
	 * Calculates the absolute value for the HUDItem's upper left corner.
	 * 
	 * @return absolute value of the upper left corner.
	 */
	public final int getY() {
		int pxlY = anchor.getY() + y;
		switch(anchor) {
			case TOPLEFT:
			case TOP:
			case TOPRIGHT:
				return pxlY;
			case LEFT:
			case CENTER:
			case RIGHT:
				return pxlY -= height / 2;
			case BOTTOMLEFT:
			case BOTTOM:
			case BOTTOMRIGHT:
				return pxlY -= height;
			default:
				return pxlY;
		}
	}
	
	/**
	 * Getter for {@link #guiBackground}.
	 * 
	 * @return flag for whether the HUDItem should have a background in {@link GuiScreenAdjustHud}.
	 */
	public final boolean guiBackground() {
		return guiBackground;
	}
	
	/**
	 * Getter for {@link #graphic}
	 * @return 
	 * 
	 * @return flag for whether the HUDItem is a graphics object
	 */
	public final boolean isGraphic() {
		return graphic;
	}
	
	/**
	 * Moves the HUDItem up one in its priority.
	 */
	public final void moveback() {
		HudItemManager.REGISTRY.moveUp(this);
	}
	
	/**
	 * Moves the HUDItem down one in its priority.
	 */
	public final void moveforward() {
		HudItemManager.REGISTRY.moveUp(this);
	}
	
	/**
	 * Move the HUDItem to the given slot in its priority.
	 */
	public final void moveTo(int index) {
		HudItemManager.REGISTRY.moveTo(this, index);
	}
	
	/**
	 * Handles all values that must be reset before rendering
	 */
	public void preRender() {
		setHeightAndWidth();
	}
	
	/**
	 * <p>Renders the HUDItem in the saved location.
	 */
	public final void renderHUDItem() {
		preRender();
		renderHUDItem(getX(), getY());
	}
	
	/**
	 * <p>Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered based on the absolute
	 * values passed.
	 * 
	 * <p>The player can use renderIcon(...) to customize the rendering
	 * of the icons the themselves
	 * 
	 * @param x			The absolute x-value of the upper left corner.
	 * @param y			The absolute y-value of the upper left corner.
	 */
	public abstract void renderHUDItem(int x, int y);
	
	/**
	 * Rotate the HUDItem counter-clockwise.
	 * 
	 * @see Orientation
	 */
	public final void rotateLeft() {
		if(!canRotate) return;
		orientation = orientation.left();
		// preRender();
	}
	
	/**
	 * Rotate the HUDItem clockwise.
	 * 
	 * @see Orientation
	 */
	public final void rotateRight() {
		if(!canRotate) return;
		orientation = orientation.right();
		// preRender();
	}
	
	/**
	 * Setter for the HUDItem's Anchor
	 * 
	 * @see Anchor
	 * 
	 * @param anchor	New anchor for the HUDItem.
	 */
	public final void setAnchor(Anchor anchor) {
		if(!canMove) return;
		this.anchor = anchor;
		// preRender();
	}
	
	/**
	 * Setter for the ID.
	 * 
	 * @param id	Identification for {@link GuiScreenAdjustHud}.
	 */
	public final void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Sets the HUDItem to the given orientation
	 * 
	 * @param	orientation		The orientation to set the HUDItem to.
	 */
	public final void setOrientation(Orientation orientation) {
		if(!canRotate) return;
		this.orientation = orientation;
		// preRender();
	}
	
	/**
	 * Sets the absolute position to x, y.
	 * 
	 * @param x		The absolute x-value of the upper left corner.
	 * @param y		The absolute y-value of the upper left corner.
	 */
	public final void setPos(int x, int y) {
		if(!canMove) return;
		switch(anchor) {
			case TOPLEFT:
				break;
			case TOP:
				x += width / 2;
				break;
			case TOPRIGHT:
				x += width;
				break;
				
			case LEFT:
				y += height / 2;
				break;
			case CENTER:
				x += width / 2;
				y += height / 2;
				break;
			case RIGHT:
				x += width;
				y += height / 2;
				break;
				
			case BOTTOMLEFT:
				y += height;
				break;
			case BOTTOM:
				x += width / 2;
				y += height;
				break;
			case BOTTOMRIGHT:
				x += width;
				y += height;
				break;
		}
		
		this.x = x - anchor.getX();
		this.y = y - anchor.getY();
	}
	
	/**
	 * String for the HUDItem.
	 * 
	 * @return string to be used when used as a string.
	 */
	@Override
	public final String toString() {
		return "HUDItem " + getName() + (id == -1 ? "" : (" (" + getId() + ")"));
	}
	
	/**
	 * Called every tick for basic upkeep for all HUDItems.
	 */
	public void updateTick() {
		++updateCounter;
		rand.setSeed((long)(updateCounter * 312871));
	}	
	
	/**
	 * Binds the texture for the {@link Gui#drawTexturedModalRect(int, int, int, int, int, int)}.
	 * 
	 * @param res		Resolution for the screen.
	 */
	protected final void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	
	/**
	 * Gets the delta X for animation and temporary adjust purposes for
	 * the entire HUDItem.
	 * 
	 * @return	The change in x relative to current coords for this tick
	 */
	protected abstract int getDeltaX();
	
	/**
	 * Gets the delta Y for animation and temporary adjustment purpose for
	 * the entire HUDItem.
	 * 
	 * @return	The change in y relative to the current coords for this tick
	 */
	protected abstract int getDeltaY();
	
	/**
	 * Initializes class specific fields such as location,
	 * orientation, and textures.
	 */
	protected abstract void init();
	
	/**
	 * Getter for {@link #isInPreview()}.
	 * 
	 * @return flag for whether the game <code>isInPreview</code>.
	 */
	protected final boolean isInPreview() {
		return mc.currentScreen instanceof GuiScreenAdjustHud;
	}
	
	/**
	 * <p>Draws the Icon as a part of the HUDItem.
	 * 
	 * <p>This method is meant to be used within the renderHUDItem() method.
	 * It allows for customized rendering of the icons themselves if the default
	 * is not sufficient.
	 * 
	 * @param x			absolute x value to print the upper left corner
	 * @param y			absolute y value to print the upper left corner
	 * @param sprites	sprite layers to render
	 */
	protected abstract void renderIcon(int x, int y, SpriteSet sprites);
	

	
	/**
	 * <p>Draws the Icon as a part of the HUDItem.
	 * 
	 * <p>This method is meant to be used within the renderHUDItem() method.
	 * It allows for customized rendering of the icons themselves if the default
	 * is not sufficient.
	 * 
	 * @see renderHUDItem()
	 * 
	 * @param x			absolute x value to print the upper left corner
	 * @param y			absolute y value to print the upper left corner
	 * @param sprite	sprite to render
	 */
	protected final void renderIcon(int x, int y, Sprite sprite) {
		renderIcon(x, y, new SpriteSet(sprite));
	}
	
	/**
	 * Sets Height and Width of the gauge based on the orientation.
	 * Also, sets Icon Gauge specific settings.
	 */
	protected abstract void setHeightAndWidth();
	
	/**
	 * Flag for whether the HUDItem can move.
	 */
	protected boolean canMove = true;
	
	/**
	 * Flag for whether the HUDItem can rotate.
	 */
	protected boolean canRotate = false;
	
	/**
	 * Flag for whether the HUDItem is a graphics object and should not be rendered for adjustment
	 */
	protected boolean graphic = false;
	
	/**
	 * Flag for whether the HUDItem should have a background on {@link GuiScreenAdjustHud}.
	 */
	protected boolean guiBackground = false;
	
	/**
	 * Height of the HUDItem.
	 */
	protected int height;
	
	/**
	 * HUDItem identification for {@link GuiScreenAdjustHud}
	 */
	protected int id = -1;
	
	/**
	 * Width of the HUDItem.
	 */
	protected int width;
	
	/**
	 * Relative x-value for the upper left corner of the HUDItem.
	 */
	protected int x;
	
	/**
	 * Relative y-value for the upper left corner of the HUDItem.
	 */
	protected int y;
	
	/**
	 * The reference point for the x and y values.
	 */
	protected Anchor anchor;
	
	/**
	 * Direction the HUDItem will fill when rendered.
	 */
	protected Orientation orientation;
	
	/**
	 * Priority for the HudItem.
	 */
	protected RenderPriority priority;
	
	/**
	 * Name of the HUDItem.
	 */
	protected String name;
}
