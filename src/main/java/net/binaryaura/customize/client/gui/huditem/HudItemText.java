package net.binaryaura.customize.client.gui.huditem;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public abstract class HudItemText extends HudItem {
	
	protected static final Style DFLT_STY = Style.NORMAL;
	
	protected enum Style {
		NORMAL, SHADOWED, OUTLINED;
	}
	
	public HudItemText(String name) {
		super(name);
		style = DFLT_STY;
		guiBackground = true;
		fontRenderer = mc.fontRendererObj;
	}

	@Override
	public void renderHUDItem(int x, int y) {
		setHeightAndWidth();
		int textX = x + getDeltaX();
		int textY = y + getDeltaY();
		int styleAdd = (style == Style.SHADOWED ? 1 : style == Style.OUTLINED ? 2 : 0);
		if(getBGAlpha() > 0) {
			int bgColor = getBGColor() + (getBGAlpha() << 24);
			Gui.drawRect(textX - (style == Style.OUTLINED ? 1 : 0), textY - (style == Style.OUTLINED ? 1 : 0), textX + fontRenderer.getStringWidth(string) + styleAdd, textY + fontRenderer.FONT_HEIGHT + styleAdd, bgColor);
			}
		if(getAlpha() > 0) {
			int color = getColor() + (getAlpha() << 24);
			switch(style) {
				case OUTLINED:
					fontRenderer.drawString(string, textX + 1, textY, 0);
					fontRenderer.drawString(string, textX - 1, textY, 0);
					fontRenderer.drawString(string, textX, textY + 1, 0);
					fontRenderer.drawString(string, textX, textY - 1, 0);
				case NORMAL:
					fontRenderer.drawString(string, textX, textY, color);
					break;
				case SHADOWED:
					fontRenderer.drawStringWithShadow(string, textX, textY, color);
					break;
			}
		}
	}
	
	protected void setHeightAndWidth() {
		width = fontRenderer.getStringWidth(string);
		height = fontRenderer.FONT_HEIGHT;
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
