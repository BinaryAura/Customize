package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemExperience extends HudItemBar {

	public HudItemExperience(String name) {
		super(name);
		orientation = Orientation.RIGHT;
		init();
	}

	@Override
	protected void init() {
		x = -20;
		y = 10;
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 182, 5, 0, 64)));
		layers.addLayer(new SpriteSet("experience", new Sprite(Gui.icons, 182, 5, 0, 69)));
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
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		render = mc.playerController.gameIsSurvivalOrAdventure() && player != null;
		super.renderHUDItem(res, eventParent);
	}
	
	private EntityPlayer player;

	@Override
	protected SpriteSet getLayers() {
		SpriteSet sprites = new SpriteSet();
		sprites.addSprite(layers.getLayer("background").getSprite(0));
		sprites.addSprite(layers.getLayer("experience").getSprite(0));
		return sprites;
	}
	
	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("experience").getSprite(0));
	}

}
