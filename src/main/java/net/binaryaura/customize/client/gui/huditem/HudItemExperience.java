package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class HudItemExperience extends HudItemBar {
	
	public enum LevelLocation{
		BOTTOM, MIDDLE, TOP;
	}
	
	private class HudItemXPLevel extends HudItemText {

		public HudItemXPLevel(String name) {
			super(name);
			canFlip = false;
			canRotate = false;
			init();
		}

		@Override
		public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
			xpLevel = mc.thePlayer.experienceLevel;
			boolean render = xpLevel > 0;
			if(!render) return;
			string = "" + xpLevel;
			setHeightAndWidth();
			x = middleX - fontRenderer.getStringWidth(string) / 2;
			super.renderHUDItem(res, eventParent);
		}
		
		@Override
		protected int getDeltaX() {
			return 0;
		}

		@Override
		protected int getDeltaY() {
			return 0;
		}

		@Override
		protected int getBGColor() {
			return 0;
		}

		@Override
		protected int getColor() {
			return 0x80FF20;
		}

		@Override
		protected int getBGAlpha() {
			return 0;
		}

		@Override
		protected int getAlpha() {
			return 0xFF;
		}

		@Override
		protected void init() {
			setX(0);
			setY(0);
			style = Style.OUTLINED;
		}
		
		@Override
		protected void setHeightAndWidth() {
			height = fontRenderer.FONT_HEIGHT;
			width = fontRenderer.getStringWidth(string);
		}
		
		int xpLevel;
		int middleX;
	}
	
	public HudItemExperience(String name) {
		super(name);
		orientation = Orientation.UP;
		init();
	}

	@Override
	protected void init() {
		setX(-20);
		setY(10);
		levelLoc = LevelLocation.MIDDLE;
		xpLevel = new HudItemXPLevel("xpLevel");
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
		boolean render = mc.playerController.gameIsSurvivalOrAdventure() && player != null;
		if(!render) return;
		if(pre(ElementType.EXPERIENCE, eventParent)) return;
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		
		super.renderHUDItem(res, eventParent);
		
		GlStateManager.popMatrix();
		
		switch(orientation) {
			case RIGHT:
				xpLevel.middleX = this.x + this.width / 2;
				xpLevel.y = this.y - 6;
				break;
			case LEFT:
				xpLevel.middleX = this.x + this.height - this.width / 2;
				xpLevel.y = this.y - 6;
				break;
			case UP:
				xpLevel.middleX = this.x + MathHelper.ceiling_float_int(this.width / 2.0F);
				switch(levelLoc) {
					case BOTTOM:
						xpLevel.y = this.y + 6;
						break;
					case MIDDLE:
						xpLevel.y = this.y - this.height / 2 - 6;
						break;
					case TOP:
						xpLevel.y = this.y - this.height - 6;
						break;
				}
				break;
			case DOWN:
				xpLevel.middleX = this.x + MathHelper.ceiling_float_int(this.width / 2.0F);
				switch(levelLoc) {
					case BOTTOM:
						xpLevel.y = this.y + this.height + 6;
						break;
					case MIDDLE:
						xpLevel.y = this.y + this.height / 2 - 6;
						break;
					case TOP:
						xpLevel.y = this.y - 6;
						break;
				}
				break;
		}
		
		GlStateManager.pushMatrix();
		
		xpLevel.renderHUDItem(res, eventParent);
		
		GlStateManager.enableBlend();
		post(ElementType.EXPERIENCE, eventParent);
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
	
	private LevelLocation levelLoc;
	private HudItemXPLevel xpLevel;
}
