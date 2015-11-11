package net.binaryaura.customize.client.gui.huditem;

import java.util.Random;

import org.apache.logging.log4j.Logger;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItem {
	
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
	
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		if(render) return;
	}
	
	public void updateTick() {
		++updateCounter;
		rand.setSeed((long)(updateCounter * 312871));
	}

	@Override
	public String toString() {
		return "HUDItem " + getName();
	}
	
	protected void bind(ResourceLocation res) {
		mc.getTextureManager().bindTexture(res);
	}
	

	protected boolean flip = false;
	protected boolean render = true;
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
