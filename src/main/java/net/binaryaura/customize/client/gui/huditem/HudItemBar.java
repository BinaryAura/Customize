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
	
	private int alignmentTransX() {
		switch(anchor) {
			case TOP:
			case CENTER:
			case BOTTOM:
				return -width / 2;
			case TOPRIGHT:
			case RIGHT:
			case BOTTOMRIGHT:
				return -width;
			default:
				return 0;
		}
	}
	
	private int alignmentTransY() {
		switch(anchor) {
			case LEFT:
			case CENTER:
			case RIGHT:
				return -height / 2;
			case BOTTOMLEFT:
			case BOTTOM:
			case BOTTOMRIGHT:
				return -height;
			default:
				return 0;
		}
	}
	
	private int getTranslateX(int x, int y) {
		switch(orientation) {
			case DOWN:
				return x + y + (layers.getWidth() + layers.getHeight()) / 2;
			case LEFT:
				return 2*x + layers.getWidth();
			case UP:
				return x - y + (layers.getWidth() + layers.getHeight()) / 2;
			default:
				return 0;
		}
	}
	
	private int getTranslateY(int x, int y) {
		switch(orientation) {
			case DOWN:
				return y - x + (layers.getHeight() - layers.getWidth()) / 2;
			case LEFT:
				return 2*y + layers.getHeight();
			case UP:
				return x + y + (layers.getWidth() + layers.getHeight()) / 2;
			default:
				return 0;
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

	@Override
	public int getButtonX(int x, int y) {
		switch(getOrientation()) {
			case DOWN:
				return getX() + (height - width) / 2;
			case UP:
				return getX() + (height + width) / 2;
			default:
				return getX();
		}
	}

	@Override
	public int getButtonY(int x, int y) {
		switch(getOrientation()) {
			case DOWN:				
				return getY() - (height - width) / 2;
			case UP:
				return getY() - (int)Math.ceil((height - width) / 2.0);
			default:
				return getY();
		}
	}

	protected abstract float getAmount(int layer);
	protected abstract SpriteSet getLayers();
	protected abstract SpriteSet getDefaultSpriteSet();
	
	protected LayeredSprite layers;
}
