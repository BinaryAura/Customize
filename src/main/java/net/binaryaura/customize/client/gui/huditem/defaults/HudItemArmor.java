package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;

/**
 * Gauge that tracks the amount of armored protection the player
 * is wearing. ARMOR is a {@link HudItemIconGauge} and uses its
 * constructor and renderer.
 * 
 * @author 	BinaryAura
 * @see		HudItem
 * @see		HudItemIconGauge
 */
public class HudItemArmor extends HudItemIconGauge {
	
	/**
	 * Relative x-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_X = -45;
	
	/**
	 * Relative y-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_Y = -49;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found. 
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;

	/**
	 * Constructs an instance of the ARMOR gauge with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the gauge.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemArmor(String name) {
		super(name);
	}

	/**
	 * Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered then, calls the
	 * superclass's version of this method to render the gauge.
	 * 
	 * @see HudItemIconGauge#renderHUDItem(Anchor, int, int)
	 * 
	 * @param x			The x-value of the upper left corner.
	 * @param y			The y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer) mc.getRenderViewEntity();
		
		if(this.mc.playerController.shouldDrawHUD() && player != null || isInPreview()) {
			super.renderHUDItem(x, y);
		}
	}

	/**
	 * Initializes class specific fields such as location,
	 * orientation, and textures.
	 */
	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("armor", new Sprite(Gui.icons, 16, 9, 9, 9), new Sprite(Gui.icons, 25, 9, 9, 9), new Sprite(Gui.icons, 34, 9, 9, 9)));
	}

	/**
	 * Calculates parallel movement of each icon of the gauge.							 <3 <- <3 -> <3
	 * 
	 * @param icon		The index of the icon.  <3 0 <3 1 <3 2 <3 3 <3 4 <3 5 etc
	 * 
	 * @return parallel movement direction and distance. 
	 */
	@Override
	protected int getIconDeltaPara(int icon) {
		return 0;
	}

	/**																						   /\
	 * Calculates perpendicular movement of each icon of the gauge.		 				 <3    <3    <3
	 * 																						   \/
	 * @param icon		The index of the icon.
	 * 
	 * @return perpendicular movement direction and distance.
	 */
	@Override
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}

	/**
	 * Gets the maximum value for during game-play. AIR max value never
	 * changes.
	 * 
	 * @return the maximum value of AIR (20 = 2 states per icon x 10 icons).
	 */
	@Override
	protected float getAmount() {
		return getDemoAmount();
	}

	/**
	 * Gets the maximum value to be used when displaying the gauge
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the maximum value of AIR as a preview.
	 */
	@Override
	protected float getDemoAmount() {
		return DFLT_DEMO_AMT;
	}

	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	@Override
	protected SpriteSet getDemoSpriteSet() {
		return new SpriteSet(layers.getLayer("armor").getSprite(2));
	}

	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge during game-play.
	 * 
	 * @param icon		The index of the icon.		
	 * 
	 * @return the textures to be used in <code>icon</code>.
	 */
	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		
		if(!isInPreview()) {
			int level = ForgeHooks.getTotalArmorValue(player);
			int index = 0;
			if(level / (layers.getAmount() - 1) > icon)
				index = layers.getAmount() - 1;
			else if(level / (layers.getAmount() - 1) == icon)
				index = icon % (layers.getAmount() - 1);
		
			iconLayers.addSprite(layers.getLayer("armor").getSprite(index));
		} else
			iconLayers.addSprites(getDemoSpriteSet());
		return iconLayers;
	}

	/**
	 * The player whose armor level this gauge tracks.
	 */
	private EntityPlayer player;
}
