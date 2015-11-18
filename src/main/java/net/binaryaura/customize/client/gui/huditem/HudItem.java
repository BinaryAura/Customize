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
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

public abstract class HudItem implements Color{	
	
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public HudItemType getType() {
		return type;
	}
	
	protected abstract void init();
	
	public void flip() {
		if(canFlip) flip = !flip;
	}
	
	public void rotateLeft() {
		if(canRotate) {
			orientation = orientation.left();
			height ^= width;
			width ^= height;
			height ^= width;
		}
	}
	
	public void rotateRight() {
		if(canRotate) {
			orientation = orientation.right();
			height ^= width;
			width ^= height;
			height ^= width;
		}
	}
	
	public abstract void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent);
	
	public void updateTick() {
		++updateCounter;
		rand.setSeed((long)(updateCounter * 312871));
	}
	
	protected abstract void setHeightAndWidth();

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected boolean pre(ElementType type, RenderGameOverlayEvent eventParent) {
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
	}

	protected void post(ElementType type, RenderGameOverlayEvent eventParent) {
		MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(eventParent, type));
	}
	
	protected void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	
	protected boolean flip = false;
	protected boolean canRotate = true;
	protected boolean canFlip = true;
	protected int x;
	protected int y;
	protected int height;
	protected int width;
	protected int updateCounter = 0;
	protected long lastSystemTime;
	protected String name;
	protected HudItemType type;
	protected Minecraft mc;
	protected Orientation orientation;
	protected Gui guiRenderer;
	protected Logger log = Customize.log;
}
