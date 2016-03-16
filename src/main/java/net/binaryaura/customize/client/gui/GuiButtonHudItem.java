package net.binaryaura.customize.client.gui;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonHudItem extends GuiButton {

	public GuiButtonHudItem(HudItem hudItem) {
		this(hudItem.getId(), hudItem.getX(), hudItem.getY(), hudItem.getWidth(), hudItem.getHeight(), hudItem.getName());
	}
	
	public GuiButtonHudItem(int buttonId, int x, int y, int width, int height, String name) {
		super(buttonId, x, y, width, height, name);
		hudItem = HudItemManager.getHudItem(name);
		hudItemX = x;
		hudItemY = y;
		xPosition = hudItem.getButtonX();
		yPosition = hudItem.getButtonY();	
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(visible) {
			
			hovered = mouseX >= xPosition + deltaX && mouseY >= yPosition + deltaY && mouseX < xPosition + deltaX + width && mouseY < yPosition + deltaY + height;
			FontRenderer fontrenderer = mc.fontRendererObj;
			if(hudItem != null) {
				GL11.glPushMatrix();
				if(isMouseOver())
					drawRect(xPosition + deltaX, yPosition + deltaY, xPosition + deltaX + width, yPosition + deltaY + height, Color.WHITE + (0x22 << 24));
				hudItem.renderHUDItem(hudItemX + deltaX, hudItemY + deltaY);
				GL11.glPopMatrix();
			} else {
				Customize.log.warn("HudItem " + displayString + " doesn't exist.");
				return;
			}
			
			if(hovered && deltaX == 0 && deltaY == 0) {
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0, 200F);
//				this.drawCenteredString(fontrenderer, displayString.toUpperCase(), xPosition + width / 2, yPosition + (height - 8) / 2 + 1, Color.WHITE);
				this.drawCenteredString(fontrenderer, displayString.toUpperCase(), mouseX, mouseY - fontrenderer.FONT_HEIGHT, Color.WHITE);
				GL11.glPopMatrix();
			}
		}
	}

	public void setAnchor(Anchor anchor) {
		hudItem.setAnchor(anchor);
		savePosition();
	}
	
	public void rotate() {
		hudItem.rotateLeft();
	}	
	
	@Deprecated
	public void recover() {
		int screenHeight = HudItemManager.getRes().getScaledHeight();
		int screenWidth = HudItemManager.getRes().getScaledWidth();
		int deltaX = 0;
		int deltaY = 0;
		if(xPosition < 5)
			deltaX = 25 - xPosition;
		if(xPosition > screenWidth - 5)
			deltaX = screenWidth - 25 - xPosition;
		if(yPosition < 5)
			deltaY = 25 - yPosition;
		if(yPosition > screenHeight - 5)
			deltaY = screenHeight - 25 - yPosition;
		editPosition(deltaX, deltaY);
		savePosition();
	}
	
	public void savePosition() {		
		hudItemX += deltaX;
		hudItemY += deltaY;
		editPosition(0, 0);
		hudItem.setPos(hudItemX, hudItemY);
	}
	
	public void editPosition(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	private int deltaX;
	private int deltaY;
	private int hudItemX;
	private int hudItemY;
	private HudItem hudItem;
}
