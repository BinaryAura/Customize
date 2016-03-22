package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemBar;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.gui.huditem.HudItem.Orientation;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

public class HudItemExperience extends HudItemBar {

	private static final int DFLT_X = 0;
	private static final int DFLT_Y = -29;
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	private static final Orientation DFLT_ORIEN = Orientation.RIGHT;
	
	public HudItemExperience(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		if(mc.playerController.gameIsSurvivalOrAdventure() && player != null)
			super.renderHUDItem(x, y);
	}

	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 0, 64, 182, 5)));
		layers.addLayer(new SpriteSet("experience", new Sprite(Gui.icons, 0, 69, 182, 5)));
	}

	@Override
	protected float getAmount(int layer) {
		if(layer == 0) {
			return 1;
		} else {
			return player.experience;
		}
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("experience").getSprite(0));
	}
	
	@Override
	protected SpriteSet getLayers() {
		SpriteSet sprites = new SpriteSet();
		sprites.addSprite(layers.getLayer("background").getSprite(0));
		sprites.addSprite(layers.getLayer("experience").getSprite(0));
		return sprites;
	}
	
	private EntityPlayer player;

}
