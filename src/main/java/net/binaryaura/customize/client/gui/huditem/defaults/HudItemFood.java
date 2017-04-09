package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;

/**
 * Gauge that tracks the amount of hunger the player has. FOOD
 * is a {@link HudItemIconGauge} and uses its constructor and
 * renderer.
 * 
 * @author 	BinaryAura
 * @see		HudItem
 * @see		HudItemIconGauge
 */
public class HudItemFood extends HudItemIconGauge {
	
	/**
	 * Relative x-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_X = 50;
	
	/**
	 * Relative y-value where the bar will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_Y = -30;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found. 
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	
	/**
	 * The direction the bar will fill when rendered when no save
	 * entry is found.
	 * 
	 * @see HudItem.Orientation
	 */
	private static final Orientation DFLT_ORIEN = Orientation.LEFT;

	/**
	 * Constructs an instance of the FOOD gauge with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the gauge.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemFood(String name) {
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
		stats = player.getFoodStats();
		
		/**
		 * FOOD shouldn't be rendered if <code>mc.playerController.shouldDrawHUD</code>
		 * is false, <code>player</code> doesn't exist.
		 */
		if(mc.playerController.shouldDrawHUD() && player != null || isInPreview())
			super.renderHUDItem(x, y);
	}

	/**
	 * Initializes class specific fields such as location,
	 * orientation, and textures.
	 */
	@Override
	protected void init() {
		super.init();
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 16, 27, 9, 9), new Sprite(Gui.icons, 25, 27, 9, 9)));
		layers.addLayer(new SpriteSet("default", null, new Sprite(Gui.icons, 61, 27, 9, 9), new Sprite(Gui.icons, 52, 27, 9, 9)));
		layers.addLayer(new SpriteSet("defaultPrev", null, new Sprite(Gui.icons, 79, 27, 9, 9), new Sprite(Gui.icons, 52, 27, 9, 9)));
		layers.addLayer(new SpriteSet("hunger", new Sprite(Gui.icons, 133, 27, 9, 9), new Sprite(Gui.icons, 97, 27, 9, 9), new Sprite(Gui.icons, 88, 27, 9, 9)));
		layers.addLayer(new SpriteSet("hungerPrev", null, new Sprite(Gui.icons, 115, 27, 9, 9), new Sprite(Gui.icons, 106, 27, 9, 9)));		
	}
	
	@Override
	public void updateTick() {
		super.updateTick();
		player = (EntityPlayer)mc.getRenderViewEntity();
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
		int perp = 0;
		if(stats.getSaturationLevel() <= 0.0F && updateCounter % (stats.getFoodLevel() * 3 + 1) == 0)
			perp = bidirectionalShake();
		return perp;
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
			int level = stats.getFoodLevel();
			int levelLast = stats.getPrevFoodLevel();
			boolean prevFood = false;
			
			int index = 0;
			int indexPrev = 0;
			
			if(prevFood) {
				if(levelLast / (layers.getAmount() - 1) > icon)
					indexPrev = layers.getAmount() - 1;
				else if(levelLast / (layers.getAmount() - 1) == icon)
					indexPrev = level % (layers.getAmount() - 1);
			}
			
			if(level / (layers.getAmount() - 1) > icon)
				index = layers.getAmount() - 1;
			else if(level / (layers.getAmount() - 1) == icon)
				index = level % (layers.getAmount() - 1);
			
			iconLayers.addSprite(layers.getLayer("background").getSprite(0));
			if(player.isPotionActive(Potion.hunger)) {
				if(prevFood && indexPrev > index)
					iconLayers.addSprite(layers.getLayer("hungerPrev").getSprite(indexPrev));
				iconLayers.addSprite(layers.getLayer("hunger").getSprite(index));
			} else {
				if(prevFood && indexPrev > index)
					iconLayers.addSprite(layers.getLayer("defaultPrev").getSprite(indexPrev));
				iconLayers.addSprite(layers.getLayer("default").getSprite(index));
			}
		} else {
			iconLayers.addSprites(layers.getLayer("background").getSprite(0));
			if(icon < getAmount()/2)
				iconLayers.addSprite(layers.getLayer("default").getSprite(2));
		}
		return iconLayers;
	}

	/**
	 * A collection of values denoting the player's hunger state.
	 */
	private FoodStats stats;
	
	/**
	 * The player whose hunger this gauge tracks.
	 */
	private EntityPlayer player;
}
