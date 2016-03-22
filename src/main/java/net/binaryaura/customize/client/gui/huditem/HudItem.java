package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

public abstract class HudItem implements Color{
	
	protected static final boolean DFLT_FLIP = false;
	protected static final int DFLT_X = 0;
	protected static final int DFLT_Y = 0;
	protected static final Anchor DFLT_ANCH = Anchor.TOPLEFT;
	protected static final Orientation DFLT_ORIEN = Orientation.RIGHT;
	
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
					return hudManager.getRes().getScaledWidth() / 2;
				case TOPRIGHT:
				case RIGHT:
				case BOTTOMRIGHT:
					return hudManager.getRes().getScaledWidth();
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
					return hudManager.getRes().getScaledHeight() / 2;
				case BOTTOMLEFT:
				case BOTTOM:
				case BOTTOMRIGHT:
					return hudManager.getRes().getScaledHeight();
				default:
					return 0;
			}
		}		
	}
	
	public static enum Orientation {
		UP, RIGHT, DOWN, LEFT;
		
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
	}
	
	protected static HudItemManager hudManager = Customize.hudManager;
	protected static final Random rand = new Random();
	
	private static boolean inPreview = false;
	
	public static void setInPreview() {
		inPreview = true;
	}
	
	public HudItem(String name){
		this.name = name.toLowerCase();
		mc = Minecraft.getMinecraft();
		guiRenderer = new Gui();
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		flip = DFLT_FLIP;
		x = DFLT_X;
		y = DFLT_Y;
		init();
	}
	
	public void flip() {
		flip = !flip;
	}
	
	public void renderHUDItem() {
		renderHUDItem(getX(), getY());
	}
	
	public void rotateLeft() {
		if(!canRotate) return;
		orientation = orientation.left();
		setHeightAndWidth();
	}
	
	public void rotateRight() {
		if(!canRotate) return;
		orientation = orientation.right();
		setHeightAndWidth();
	}	
	
	public void setAnchor(Anchor anchor) {
		if(!canMove) return;
		this.anchor = anchor;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPos(int x, int y) {
		if(!canMove) return;
		
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
	}
	
	public void updateTick() {
		++updateCounter;
		rand.setSeed((long)(updateCounter * 312871));
		if(inPreview) inPreview = false;
	}
	
	public boolean canFlip() {
		return canFlip;
	}
	
	public boolean canMove() {
		return canMove;
	}
	
	public boolean canRotate() {
		return canRotate;
	}
	
	public boolean guiBackground() {
		return guiBackground;
	}
	
	@Deprecated
	public int getButtonX() {
		return getX();
	}
	
	@Deprecated
	public int getButtonY() {
		return getY();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return id;
	}
	
	public int getWidth() {
		return width;
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
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	protected void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	
	protected boolean isInPreview() {
		return inPreview;
	}
	
	public abstract void renderHUDItem(int x, int y);
	protected abstract void init();	
	protected abstract void setHeightAndWidth();
	
	protected boolean canMove = true;
	protected boolean canFlip = false;
	protected boolean canRotate = false;
	protected boolean flip;
	protected boolean guiBackground = false;
	protected int height;
	protected int id;
	protected int updateCounter = 0;
	protected int width;
	protected int x;
	protected int y;
	protected long lastSystemTime;
	protected Anchor anchor;
	protected Gui guiRenderer;
	protected Logger log = Customize.log;
	protected Minecraft mc;
	protected Orientation orientation;
	protected String name;
}
