package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.FontRenderer;
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
		for(int line = 0; line < strings.length; line++) {
			int bgColor = getBGColor(line);
			bgColor += getBGAlpha(line) << 24;
			for(int string = 0; string < strings[line].length; string++) {
				int color = getColor(line, string);
				color = getAlpha(line, string) << 24;
			}
		}		
	}

	protected abstract int getBGColor(int line);
	protected abstract int getBGAlpha(int line);
	protected abstract int getColor(int line, int string);
	protected abstract int getAlpha(int line, int string);
	
	protected String[][] strings;
	protected FontRenderer fontRenderer;
}
