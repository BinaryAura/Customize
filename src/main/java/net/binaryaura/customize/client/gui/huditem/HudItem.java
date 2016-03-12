package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.gui.Color;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItem implements Color{
	
	public static enum Anchor {
		TOPLEFT, TOP, TOPRIGHT, LEFT, CENTER, RIGHT, BOTTOMLEFT, BOTTOM, BOTTOMRIGHT;
		
		public int getX() {
			switch(this) {
				case TOPLEFT:
				case LEFT:
				case BOTTOMLEFT:
					return 0;
				case TOP:
				case CENTER:
				case BOTTOM:
					return HudItemManager.getRes().getScaledWidth() / 2;
				case TOPRIGHT:
				case RIGHT:
				case BOTTOMRIGHT:
					return HudItemManager.getRes().getScaledWidth();
				default:
					return 0;
			}
		}
		
		public int getY() {
			switch(this) {
				case TOPLEFT:
				case TOP:
				case TOPRIGHT:
					return 0;
				case LEFT:
				case CENTER:
				case RIGHT:
					return HudItemManager.getRes().getScaledHeight() / 2;
				case BOTTOMLEFT:
				case BOTTOM:
				case BOTTOMRIGHT:
					return HudItemManager.getRes().getScaledHeight();
				default:
					return 0;
			}
		}		
	}
	
	public static enum Orientation {
		UP, RIGHT, DOWN, LEFT;
		
		public Orientation right() {
			switch(this) {
				case RIGHT:
					return DOWN;
				case DOWN:
					return LEFT;
				case LEFT:
					return UP;
				case UP:
					return RIGHT;
				default:
					return null;
			}
		}
		
		public Orientation left() {
			switch(this) {
				case LEFT:
					return UP;
				case UP:
					return RIGHT;
				case RIGHT:
					return DOWN;
				case DOWN:
					return LEFT;
				default:
					return null;
			}
		}
	}
	
	protected static final Random rand = new Random();
	
	public HudItem(String name){
		this.name = name;
		mc = Minecraft.getMinecraft();
		guiRenderer = new Gui();
	}
	
	public String getName() {
		return name;
	}
	
	public HudItemType getType() {
		return type;
	}
	
	protected abstract void init();
	
	public void flip() {
		flip = !flip;
	}
	
	public void rotateLeft() {
		orientation = orientation.left();
	}
	
	public void rotateRight() {
		orientation = orientation.right();
	}
	
	public void renderHUDItem() {
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void updateTick() {
		++updateCounter;
		rand.setSeed((long)(updateCounter * 312871));
	}
	
	public int getX() {
		int pxlX = anchor.getX() + x;
		switch(anchor) {
			case TOPLEFT:
			case LEFT:
			case BOTTOMLEFT:
				return pxlX;
			case TOP:
			case CENTER:
			case BOTTOM:
				return pxlX -= width / 2;
			case TOPRIGHT:
			case RIGHT:
			case BOTTOMRIGHT:
				return pxlX -= width;
			default:
				return pxlX;
		}
	}
	
	public int getY() {
		int pxlY = anchor.getY() + y;
		switch(anchor) {
			case TOPLEFT:
			case TOP:
			case TOPRIGHT:
				return pxlY;
			case LEFT:
			case CENTER:
			case RIGHT:
				return pxlY -= height / 2;
			case BOTTOMLEFT:
			case BOTTOM:
			case BOTTOMRIGHT:
				return pxlY -= height;
			default:
				return pxlY;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	
	protected boolean canFlip = false;
	protected boolean canRotate = false;
	protected boolean flip = false;
	protected boolean render = true;
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected int id;
	protected int updateCounter = 0;
	protected long lastSystemTime;
	protected String name;
	protected HudItemType type;
	protected Minecraft mc;
	protected Anchor anchor;
	protected Orientation orientation;
	protected Gui guiRenderer;
	protected Logger log = Customize.log;
}
