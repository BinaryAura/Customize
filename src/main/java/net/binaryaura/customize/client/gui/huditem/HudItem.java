package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.gui.Color;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

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
		this.name = name.toLowerCase();
		mc = Minecraft.getMinecraft();
		guiRenderer = new Gui();
	}
	
	protected void setHeightAndWidth() {
		log.info("HUDITEM");
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
		setHeightAndWidth();
	}
	
	public void rotateRight() {
		orientation = orientation.right();
		setHeightAndWidth();
	}
	
	public void renderHUDItem() {
		renderHUDItem(getX(), getY());
	}
	
	public void renderHUDItem(int x, int y) {}
	
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
	
	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
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
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	//	TODO: Set up Border Collision
	public void setPos(int x, int y) {
		log.info(this.x + " : " + this.y);
		log.info("anchorPos: " + anchor.getX() + " : " + anchor.getY());
		
		switch(anchor) {
			case TOPLEFT:
				break;
			case TOP:
				x += width / 2;
				break;
			case TOPRIGHT:
				x += width;
				break;
				
			case LEFT:
				y += height / 2;
				break;
			case CENTER:
				x += width / 2;
				y += height / 2;
				break;
			case RIGHT:
				x += width;
				y += height / 2;
				break;
				
			case BOTTOMLEFT:
				y += height;
				break;
			case BOTTOM:
				x += width / 2;
				y += height;
				break;
			case BOTTOMRIGHT:
				x += width;
				y += height;
				break;
		}
		
		this.x = x - anchor.getX();
		this.y = y - anchor.getY();
		log.info("Saved: " + anchor + ": (" + this.x + ":" + this.y + ")");
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
	
	public boolean guiBackground() {
		return guiBackground;
	}

	public boolean canMove() {
		return canMove;
	}
	
	public boolean canFlip() {
		return canFlip;
	}
	
	public boolean canRotate() {
		return canRotate;
	}
	
	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	
	protected boolean guiBackground = false;
	protected boolean canMove = true;
	protected boolean canFlip = false;
	protected boolean canRotate = false;
	protected boolean flip = false;
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
