package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemBar extends HudItem {

	public HudItemBar(String name) {
		super(name);
		type = HudItemType.BAR;
		canFlip = true;
		canRotate = true;
	}
	
	@Override
	protected void setHeightAndWidth() {
		log.info("BAR");
		switch(orientation) {
			case DOWN:
			case UP:
				width = layers.getHeight();
				height = layers.getWidth();
				break;
			case RIGHT:
			case LEFT:
				width = layers.getWidth();
				height = layers.getHeight();
				break;
		}
	}
	
	@Override
	public void renderHUDItem() {
		mc.mcProfiler.startSection(name);
		
		SpriteSet sprites = getLayers();
		setHeightAndWidth();
		int x = getX();
		int y = getY();
		
		log.info(x + " : " + y);
		
		switch(orientation) {
			case RIGHT:
				break;
			case DOWN:
				GL11.glTranslated(x + y + layers.getHeight(), y - x, 0.0F);
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				break;
			case LEFT:
				GL11.glTranslatef(2*x + layers.getWidth(), 2*y + layers.getHeight(), 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				break;
			case UP:
				GL11.glTranslatef(x - y, y + x + layers.getWidth(), 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				break;
		}
		bind(sprites.getLocation());
		for(int i = 0; i < layers.getAmount() + 1; i++) {
			int fill = MathHelper.ceiling_float_int(getAmount(i)*layers.getWidth());
			guiRenderer.drawTexturedModalRect(x, y, sprites.getSprite(i).getX(), sprites.getSprite(i).getY(), fill, layers.getHeight());
		}
		mc.mcProfiler.endSection();
	}
	
	protected abstract float getAmount(int layer);
	protected abstract SpriteSet getLayers();
	protected abstract SpriteSet getDefaultSpriteSet();
	
	protected LayeredSprite layers;
}
