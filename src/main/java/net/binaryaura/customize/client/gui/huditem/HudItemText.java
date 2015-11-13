package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemText extends HudItem {

	public HudItemText(String name) {
		super(name);
		type = HudItemType.TEXT;
		fontRenderer = mc.fontRendererObj;
	}

	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		int x = res.getScaledWidth() / 2 + this.x;
		int y = res.getScaledHeight() / 2 + this.y;
		int bgColor = getBGColor() + (getBGAlpha() << 24);
		int color = getColor() + (getAlpha() << 24);
		Gui.drawRect(x + getDeltaX(), y + getDeltaY(), x + fontRenderer.getStringWidth(string), y + fontRenderer.FONT_HEIGHT, bgColor);
		if(isShadowed) {
			fontRenderer.drawStringWithShadow(string, x, y, color);
		} else {
			fontRenderer.drawString(string, x, y, color);
		}
	}
	
	protected abstract int getDeltaX();
	protected abstract int getDeltaY();
	protected abstract int getBGColor();
	protected abstract int getColor();
	protected abstract int getBGAlpha();
	protected abstract int getAlpha();
	
	protected boolean isShadowed;
	protected int bgColor;
	protected int color;
	protected String string;
	protected FontRenderer fontRenderer;
}
