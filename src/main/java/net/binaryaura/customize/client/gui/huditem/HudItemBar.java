package net.binaryaura.customize.client.gui.huditem;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;

public abstract class HudItemBar extends HudItem {
	
	public HudItemBar(String name) {
		super(name);
		canRotate = true;
	}

	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		
		setHeightAndWidth();
		
		switch(orientation) {
			case RIGHT:
				for(int i = 0; i < layers.length; i++) {
					int fill = MathHelper.ceiling_float_int(getAmount(i)*width);
					Gui.drawRect(x, y, x + fill, y + height, getColor(i) + (getAlpha(i) << 24));
				}
				break;
			case DOWN:
				for(int i = 0; i < layers.length; i++) {
					int fill = MathHelper.ceiling_float_int(getAmount(i)*height);
					Gui.drawRect(x, y, x + width, y + fill, getColor(i) + (getAlpha(i) << 24));
				}
				break;
			case LEFT:
				for(int i = 0; i < layers.length; i++) {
					int fill = MathHelper.ceiling_float_int(getAmount(i)*width);
					Gui.drawRect(x + (width - fill), y, x + width, y + height, getColor(i) + (getAlpha(i) << 24));
				}
				break;
			case UP:
				for(int i = 0; i < layers.length; i++) {
					int fill = MathHelper.ceiling_float_int(getAmount(i)*height);
					Gui.drawRect(x, y + (height - fill), x + width, y + height, getColor(i) + (getAlpha(i) << 24));
				}
				break;
		}
		
		mc.mcProfiler.endSection();
	}

	@Override
	protected void setHeightAndWidth() {}
	
	protected abstract int getAlpha(int layer);
	protected abstract int getColor(int layer);
	protected abstract int getTotal();
	protected abstract float getAmount(int layer);
	
	int[][] layers;
}
