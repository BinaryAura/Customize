package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemText extends HudItem {

	protected enum Style {
		NORMAL, SHADOWED, OUTLINED;
	}
	
	public HudItemText(String name) {
		super(name);
		flip = false;
		orientation = Orientation.RIGHT;
		canFlip = false;
		canRotate = false;
		type = HudItemType.TEXT;
		fontRenderer = mc.fontRendererObj;
	}

	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		int x = res.getScaledWidth() / 2 + this.x + getDeltaX();
		int y = res.getScaledHeight() / 2 + this.y + getDeltaY();
		int styleAdd = (style == Style.SHADOWED ? 1 : style == Style.OUTLINED ? 2 : 0);
		if(getBGAlpha() > 0) {
			int bgColor = getBGColor() + (getBGAlpha() << 24);
			Gui.drawRect(x - (style == Style.OUTLINED ? 1 : 0), y - (style == Style.OUTLINED ? 1 : 0), x + fontRenderer.getStringWidth(string) + styleAdd, y + fontRenderer.FONT_HEIGHT + styleAdd, bgColor);
			}
		if(getAlpha() > 0) {
			int color = getColor() + (getAlpha() << 24);
			switch(style) {
				case OUTLINED:
					fontRenderer.drawString(string, x + 1, y, 0);
					fontRenderer.drawString(string, x - 1, y, 0);
					fontRenderer.drawString(string, x, y + 1, 0);
					fontRenderer.drawString(string, x, y - 1, 0);
				case NORMAL:
					fontRenderer.drawString(string, x, y, color);
					break;
				case SHADOWED:
					fontRenderer.drawStringWithShadow(string, x, y, color);
					break;
			}
		}
	}
	
	protected abstract int getDeltaX();
	protected abstract int getDeltaY();
	protected abstract int getBGColor();
	protected abstract int getColor();
	protected abstract int getBGAlpha();
	protected abstract int getAlpha();
	
	protected int bgColor;
	protected int color;
	protected String string;
	protected Style style;
	protected FontRenderer fontRenderer;
}
