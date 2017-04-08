package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

/**
 * Gauge that tracks the air a player has and is lowered when the
 * player is submerged in water. When this depletes health begins
 * to deplete; the player starts to drown.AIR is a {@link HudItemIconGauge}
 * and uses its constructor and renderer.
 * 
 * @author 	BinaryAura
 * @see		HudItem
 * @see		HudItemIconGauge
 */
public class HudItemAir extends HudItemIconGauge {

	/**
	 * Relative x-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_X = 50;
	
	/**
	 * Relative y-value where the gauge will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
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
	 * The direction the bar will fill when rendered when no save
	 * entry is found.
	 * 
	 * @see HudItem.Orientation
	 */
	private static final Orientation DFLT_ORIEN = Orientation.LEFT;
	
	/**
	 * Constructs an instance of the AIR gauge with the specified
	 * <code>name</code>. This includes setting initial location
	 * values, orientation, and fetching the textures for the gauge.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemAir(String name) {
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
		
		/**
		 * AIR shouldn't be rendered if <code>mc.playerController.shouldDrawHUD()</code>
		 * is false, <code>player</code> doesn't exist, or if the <code>player</code>
		 * isn't in water.
		 */
		if(mc.playerController.shouldDrawHUD() && player != null && player.isInsideOfMaterial(Material.water) || isInPreview())
			super.renderHUDItem(x, y);
	}
	
	@Override
	public void updateTick() {
		super.updateTick();
		player = (EntityPlayer)mc.getRenderViewEntity();
	}
	
	@Override
	protected void init() {
		super.init();
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("air", null, new Sprite(Gui.icons, 25, 18, 9, 9), new Sprite(Gui.icons, 16, 18, 9, 9)));
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		if(!isInPreview()) {
			int air = player.getAir();
			int full = MathHelper.ceiling_double_int((air - 2) * 10.0 / 300.0);
			int partial = MathHelper.ceiling_double_int(air * 10.0 / 300.0) - full;
			
			int index = 0;
			if(icon < full)
				index = 2;
			else if(icon < full + partial)
				index = 1;
			iconLayers.addSprite(layers.getLayer("air").getSprite(index));
		} else
			if(icon < getAmount()/2)
				iconLayers.addSprites(layers.getLayer("air").getSprite(2));
		return iconLayers;
	}

	/**
	 * The player whose air this gauge tracks.
	 */
	private EntityPlayer player;
}
