package net.binaryaura.customize.client.gui;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;

public class GuiButtonHudItem extends GuiButton {

	public GuiButtonHudItem(HudItem hudItem) {
		this(hudItem.getId(), hudItem.getX(), hudItem.getY(), hudItem.getWidth(), hudItem.getHeight(), hudItem.getName());
	}
	
	public GuiButtonHudItem(int buttonId, int x, int y, int width, int height, String name) {
		super(buttonId, x, y, width, height, name);
		hudItem = HudItemManager.getHudItem(name);
//		hudItemX = x;
//		hudItemY = y;
//		xPosition = hudItem.getButtonX();
//		yPosition = hudItem.getButtonY();	
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(visible) {
			
			hovered = mouseX >= xPosition + deltaX && mouseY >= yPosition + deltaY && mouseX < xPosition + deltaX + width && mouseY < yPosition + deltaY + height;
			FontRenderer fontrenderer = mc.fontRendererObj;
			if(hudItem != null) {
				GL11.glPushMatrix();
				if(isMouseOver() || hudItem.guiBackground())
					drawRect(xPosition + deltaX, yPosition + deltaY, xPosition + deltaX + width, yPosition + deltaY + height, Color.WHITE + (0x22 << 24));
				hudItem.renderHUDItem(xPosition + deltaX, yPosition + deltaY);
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
	
	//	TODO: Fix Graphical Rotate Bug with Bar HudItems
	public void rotate() {
		if(hudItem.canRotate()) {
			hudItem.rotateLeft();
			editPosition((width - height) / 2, (height - width) / 2);
			width = hudItem.getWidth();
			height = hudItem.getHeight();
			savePosition();
		}
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
		Customize.log.info("SAVE");
		xPosition += deltaX;
		yPosition += deltaY;
		editPosition(0, 0);
		hudItem.setPos(xPosition, yPosition);
	}
	
	public void editPosition(int deltaX, int deltaY) {
		int screenHeight = HudItemManager.getRes().getScaledHeight();
		int screenWidth = HudItemManager.getRes().getScaledWidth();
		deltaX = MathHelper.clamp_int(deltaX, -xPosition, screenWidth - width - xPosition);
		deltaY = MathHelper.clamp_int(deltaY, -yPosition, screenHeight - height - yPosition);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	private int deltaX;
	private int deltaY;
	private HudItem hudItem;
}
