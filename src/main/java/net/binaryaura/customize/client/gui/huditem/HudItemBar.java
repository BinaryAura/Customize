package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.util.MathHelper;

public abstract class HudItemBar extends HudItem {

	public HudItemBar(String name) {
		super(name);
		canRotate = true;
		
	}
	
	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		
		SpriteSet sprites = getLayers();
		setHeightAndWidth();
		
		GL11.glTranslated(getTranslateX(x, y), getTranslateY(x, y), 0.0F);
		GL11.glRotatef(getRotation(), 0.0F, 0.0F, 1.0F);
		
		bind(sprites.getLocation());
		for(int i = 0; i < layers.getAmount() + 1; i++) {
			int fill = MathHelper.ceiling_float_int(getAmount(i)*layers.getWidth());
			guiRenderer.drawTexturedModalRect(x, y, sprites.getSprite(i).getX(), sprites.getSprite(i).getY(), fill, layers.getHeight());
		}
		mc.mcProfiler.endSection();
	}
	
	@Override
	protected void setHeightAndWidth() {
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
	
	private float getRotation() {
		switch(orientation) {
			case DOWN:
				return 90.0F;
			case LEFT:
				return 180.0F;
			case UP:
				return -90.0F;
			default:
				return 0.0F;
		}
	}
	
	private int getTranslateX(int x, int y) {
		switch(orientation) {
			case DOWN:
				return x + y + layers.getHeight();
			case LEFT:
				return 2*x + layers.getWidth();
			case UP:
				return x - y;
			default:
				return 0;
		}
	}
	
	private int getTranslateY(int x, int y) {
		switch(orientation) {
			case DOWN:
				return y - x;
			case LEFT:
				return 2*y + layers.getHeight();
			case UP:
				return x + y + layers.getWidth();
			default:
				return 0;
		}
	}

	protected abstract float getAmount(int layer);
	protected abstract SpriteSet getLayers();
	protected abstract SpriteSet getDefaultSpriteSet();
	
	protected LayeredSprite layers;
}
