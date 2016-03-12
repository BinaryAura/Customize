package net.binaryaura.customize.client.gui;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonHudItem extends GuiButton {

	public GuiButtonHudItem(int buttonId, int x, int y, int width, int height, String name) {
		super(buttonId, x, y, width, height, name);		
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(visible) {
			HudItem hudItem = HudItemManager.hudItems.get(displayString);
			hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
			FontRenderer fontrenderer = mc.fontRendererObj;
			if(hudItem != null) {
				GL11.glPushMatrix(); 
				hudItem.renderHUDItem();
				GL11.glPopMatrix();
			} else {
				Customize.log.warn("HudItem " + displayString + " doesn't exist.");
				return;
			}
			
			if(isMouseOver()) {
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0, 200F);
				this.drawCenteredString(fontrenderer, displayString.toUpperCase(), xPosition + width / 2, yPosition + (height - 8) / 2 + 1, Color.WHITE);
			}
		}
	}

}
