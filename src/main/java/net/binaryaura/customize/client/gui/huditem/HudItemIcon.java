package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;

public abstract class HudItemIcon extends HudItem{
	
	public HudItemIcon(String name) {
		super(name);
	}
	
	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		
		setHeightAndWidth();
		
		bind(icon.getLocation());
		guiRenderer.drawTexturedModalRect(x, y, icon.getX(), icon.getY(), width, height);
		mc.mcProfiler.endSection();
	}
	
	@Override
	protected void setHeightAndWidth() {
		width = icons.getWidth();
		height = icons.getHeight();
	}
	
	protected Sprite icon;
	protected SpriteSet icons;
}
