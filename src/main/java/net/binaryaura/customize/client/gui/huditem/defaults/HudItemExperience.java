package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemBar;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Bar that tracks the experience of the player. EXPERIENCE
 * is gained by killing entities, mining, and smelting.
 * EXPERIENCE is a {@link HudItemBar} and uses its constructor
 * and renderer.
 * 
 * EXPERIENCE may also contain text which would change its type.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemBar
 */
public class HudItemExperience extends HudItemBar {

	/**
	 * Relative x-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = -24;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	
	/**
	 * The direction the bar will fill when rendered when no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;
	
	/**
	 * Constructs an instance of the EXPERIENCE bar with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the bar.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemExperience(String name) {
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
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		
		/**
		 * EXPERIENCE shouldn't be rendered if <code>player</code>
		 * doesn't exist or if <code>player</code> is in Survival
		 * or Adventure.
		 */
		if(mc.playerController.gameIsSurvivalOrAdventure() && player != null)
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
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 0, 64, 182, 5)));
		layers.addLayer(new SpriteSet("experience", new Sprite(Gui.icons, 0, 69, 182, 5)));
	}

	/**
	 * Gets the fraction of the specified layer that should
	 * be displayed.
	 * 
	 * @param layer		The index of the layer in question.
	 * 
	 * @return the fraction of layer that should be displayed (between 0 and 1, inclusive).
	 */
	@Override
	protected float getAmount(int layer) {
		if(layer == 0) {
			return 1;
		} else {
			
			// player.experience returns a value between 0 and 1
			return player.experience;
		}
	}

	/**
	 * Gets the textures to be used when displaying the bar
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	@Override
	protected SpriteSet getDemoSpriteSet() {
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("experience").getSprite(0));
	}
	
	/**
	 * Get the textures to be used during game-play. These will
	 * be the same as {@link #getDemoSpriteSet()}.
	 * 
	 * @return the textures to be used for game-play.
	 */
	@Override
	protected SpriteSet getLayers() {
		return getDemoSpriteSet();
	}
	
	/**
	 * The player whose experience this bar tracks.
	 */
	private EntityPlayer player;
}
