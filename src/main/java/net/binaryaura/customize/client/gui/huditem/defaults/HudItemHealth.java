package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;

/**
 * Gauge that tracks the health of a player. The player dies when
 * this is depleted. HEALTH is a {@link HudItemIconGauge} and uses
 * its constructor and renderer.
 * 
 * @author 	BinaryAura
 * @see		HudItem
 * @see		HudItemIconGauge
 */
public class HudItemHealth extends HudItemIconGauge {
	
	/**
	 * Maximum value to be used when displaying the gauge in
	 * {@link GuiScreenAdjustHud}.
	 * 
	 * @see	GuiScreenAdjustHud
	 */
	private static final int DEMO_AMT = 24;
	
	/**
	 * Relative x-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_X = -45;
	
	/**
	 * Relative y-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = -39;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found. 
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	
	/**
	 * Constructs an instance of the HEALTH gauge with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the gauge.
	 * 
	 * @param name		The name of the HUDItem
	 */
	public HudItemHealth(String name) {
		super(name);
	}
	
	/**
	 * Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered then, calls the
	 * superclass's version of this method to render the gauge.
	 * 
	 * @param x			The x-value of the upper left corner.
	 * @param y			The y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		if(this.mc.playerController.shouldDrawHUD() && player != null || isInPreview())
			super.renderHUDItem(x, y);
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
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 16, 0, 9, 9), new Sprite(Gui.icons, 25, 0, 9, 9)));
		layers.addLayer(new SpriteSet("default", null, new Sprite(Gui.icons, 61, 0, 9, 9), new Sprite(Gui.icons, 52, 0, 9, 9)));
		layers.addLayer(new SpriteSet("defaultHL", null, new Sprite(Gui.icons, 79, 0, 9, 9), new Sprite(Gui.icons, 70, 0, 9, 9)));
		layers.addLayer(new SpriteSet("absorb", null, new Sprite(Gui.icons, 169, 0, 9, 9), new Sprite(Gui.icons, 160, 0, 9, 9)));
		layers.addLayer(new SpriteSet("poison", null, new Sprite(Gui.icons, 97, 0, 9, 9), new Sprite(Gui.icons, 88, 0, 9, 9)));
		layers.addLayer(new SpriteSet("poisonHL", null, new Sprite(Gui.icons, 115, 0, 9, 9), new Sprite(Gui.icons, 106, 0, 9, 9)));
		layers.addLayer(new SpriteSet("wither", null, new Sprite(Gui.icons, 133, 0, 9, 9), new Sprite(Gui.icons, 124, 0, 9, 9)));
		layers.addLayer(new SpriteSet("witherHL", null, new Sprite(Gui.icons, 151, 0, 9, 9), new Sprite(Gui.icons, 142, 0, 9, 9)));
		layers.addLayer(new SpriteSet("defaultHC", null, new Sprite(Gui.icons, 61, 45, 9, 9), new Sprite(Gui.icons, 52, 45, 9, 9)));
		layers.addLayer(new SpriteSet("defaultHCHL", null, new Sprite(Gui.icons, 79, 45, 9, 9), new Sprite(Gui.icons, 70, 45, 9, 9)));
		layers.addLayer(new SpriteSet("absorbHC", null, new Sprite(Gui.icons, 169, 45, 9, 9), new Sprite(Gui.icons, 160, 45, 9, 9)));
		layers.addLayer(new SpriteSet("poisonHC", null, new Sprite(Gui.icons, 97, 45, 9, 9), new Sprite(Gui.icons, 88, 45, 9, 9)));
		layers.addLayer(new SpriteSet("poisonHCHL", null, new Sprite(Gui.icons, 115, 45, 9, 9), new Sprite(Gui.icons, 106, 4, 9, 95)));
		layers.addLayer(new SpriteSet("witherHC", null, new Sprite(Gui.icons, 133, 45, 9, 9), new Sprite(Gui.icons, 124, 45, 9, 9)));
		layers.addLayer(new SpriteSet("witherHCHL", null, new Sprite(Gui.icons, 151, 45, 9, 9), new Sprite(Gui.icons, 142, 45, 9, 9)));
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
		// Parallel Movement rules
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
		// Perpendicular Movement rules
		int perp = 0;
		if(player.isPotionActive(Potion.regeneration))
			perp += -movingHalfSinWave(icon);		
		else if(player.getHealth() <= 4)
			perp += shake();
		return perp;
	}

	/**
	 * Gets the maximum value for during game-play. AIR max value never
	 * changes.
	 * 
	 * @return the maximum value of AIR (20 = 2 states per icon x 10 icons).
	 */
	@Override
	protected float getAmount() {
		// Total amount of icons
		return player.getMaxHealth() + player.getAbsorptionAmount();
	}
	
	/**
	 * Gets the maximum value to be used when displaying the gauge
	 * in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the maximum value of AIR as a preview.
	 */
	@Override
	protected float getDemoAmount() {
		return DEMO_AMT;
	}
	
	/**
	 * Gets the textures to be used when displaying the specific
	 * <code>icon</code> of the gauge in {@link GuiScreenAdjustHud}.
	 * 
	 * @return the textures to be used for preview.
	 */
	@Override
	protected SpriteSet getDemoSpriteSet() {
		// Demo set of layers.
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("default" + (mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? "HC" : "")).getSprite(2));
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
		// Which sprites and in what order.
		SpriteSet iconLayers = new SpriteSet();
		
		// Rules go here
			
		if(!isInPreview()) {
			int health = MathHelper.ceiling_float_int(player.getHealth());
			float healthMax = player.getMaxHealth();
			float absorb = player.getAbsorptionAmount();
			String hc = mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? "HC" : "";
			boolean highlight = hlTime > (long)updateCounter && (hlTime - (long)updateCounter) / 3L % 2L == 1L;
			String hl = highlight ? "HL" : "";
			if(health < healthLastTick && player.hurtResistantTime > 0) {
				lastSystemTime = Minecraft.getSystemTime();
				hlTime = (long)updateCounter + 20;
			} else if(health > healthLastTick && player.hurtResistantTime > 0) {
				lastSystemTime = Minecraft.getSystemTime();
				hlTime = (long)updateCounter + 10;
			}
			if(Minecraft.getSystemTime() - lastSystemTime > 1000L) {
				lastSystemTime = Minecraft.getSystemTime();
			}
			healthLastTick = health;
			
			int index = 0;
			
			if((healthMax / 2) > icon) {
			// Is icon normal
				if(health / (layers.getAmount() - 1) > icon) {
				// Is icon less than health
					index = layers.getAmount() - 1;
				} else if(health / (layers.getAmount() - 1) == icon) {
					index = health % (layers.getAmount() - 1);
				}
			} else {
				if(MathHelper.ceiling_float_int(healthMax + absorb) / layers.getAmount() > icon) {
					index = layers.getAmount() - 1;
				} else if(MathHelper.ceiling_float_int(healthMax + absorb) / layers.getAmount() == icon) {
					index = MathHelper.ceiling_float_int(healthMax + absorb) % (layers.getAmount() - 1);
				}
			}
			
			iconLayers.addSprite(layers.getLayer("background").getSprite(highlight ? 1 : 0));
			if(player.isPotionActive(Potion.poison)) {
				iconLayers.addSprite(layers.getLayer("poison" + hc + hl).getSprite(index));
			} else if(player.isPotionActive(Potion.wither)) {
				iconLayers.addSprite(layers.getLayer("wither" + hc + hl).getSprite(index));
			} else if((healthMax / 2) <= icon) {
				iconLayers.addSprite(layers.getLayer("absorb" + hc + hl).getSprite(index));
			} else {
				iconLayers.addSprite(layers.getLayer("default" + hc + hl).getSprite(index));
			}
		} else {
			iconLayers.addSprites(getDemoSpriteSet());
		}
		
		return iconLayers;
	}
	
	/**
	 * The health <code>player</code> had last tick.
	 */
	private int healthLastTick = 0;
	
	/**
	 * Remaining ticks for the gauge to be highlighted.
	 */
	private long hlTime = 0;
	
	/**
	 * The player whose health this gauge tracks.
	 */
	private EntityPlayer player;
}