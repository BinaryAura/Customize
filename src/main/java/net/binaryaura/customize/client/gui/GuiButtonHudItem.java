package net.binaryaura.customize.client.gui;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.MathHelper;

/**
 * Representation of the HudItem in the Adjustment screen.
 * 
 * @author	BinaryAura
 *
 */
public class GuiButtonHudItem extends GuiButton {

	/**
	 * Constructs a HUDItem Button using the given HudItem.
	 * 
	 * @param hudItem		HudItem for the button.
	 */
	public GuiButtonHudItem(HudItem hudItem) {
		this(hudItem.getId(), hudItem.getX(), hudItem.getY(), hudItem.getWidth(), hudItem.getHeight(), hudItem.getName());
	}
	
	/**
	 * Constructs a HUDItem Button using the Button Constructor.
	 * 
	 * @param buttonId		The HudItem ID
	 * @param x				X-value of the upper left corner
	 * @param y				Y-value of the upper left corner
	 * @param width			Width of the HudItem
	 * @param height		Height of the HudItem
	 * @param name			Name of the HudItem
	 */
	public GuiButtonHudItem(int buttonId, int x, int y, int width, int height, String name) {
		super(buttonId, x, y, width, height, name);
		hudItem = HudItemManager.REGISTRY.get(name);
	}

	
	//	Button needs to be adjusted to extend in the direction of the button 
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(visible) {
			hudItem.preRender();
			xPosition = hudItem.getX();
			yPosition = hudItem.getY();
			
			hovered = mouseX >= xPosition + deltaX && mouseY >= yPosition + deltaY && mouseX < xPosition + deltaX + width && mouseY < yPosition + deltaY + height;
			FontRenderer fontrenderer = mc.fontRendererObj;
			
			//	TODO: Fix Name Rendering so only one name shows
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
				this.drawCenteredString(fontrenderer, displayString.toUpperCase(), mouseX, mouseY - fontrenderer.FONT_HEIGHT, Color.WHITE);
				GL11.glPopMatrix();
			}
		}
	}

	/**
	 * Resets the HudItem's Anchor to <code>anchor</code>.
	 * 
	 * @param anchor		New anchor for the HudItem.
	 */
	public void setAnchor(Anchor anchor) {
		hudItem.setAnchor(anchor);
		savePosition();
	}
	
	/**
	 * Rotates the HudItem.
	 */
	public void rotate() {
		if(hudItem.canRotate()) {
			hudItem.rotateLeft();
			width = hudItem.getWidth();
			height = hudItem.getHeight();
			editPosition((height - width) / 2, (width - height) / 2);
			savePosition();
		}
	}	
	
	@Deprecated
	public void recover() {
		int screenHeight = HudItemManager.getInstance().getRes().getScaledHeight();
		int screenWidth = HudItemManager.getInstance().getRes().getScaledWidth();
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
	
	/**
	 * Saves the new position and orientation to the HUDItem.
	 */
	public void savePosition() {
		xPosition += deltaX;
		yPosition += deltaY;
		editPosition(0, 0);
		hudItem.setPos(xPosition, yPosition);
	}
	
	/**
	 * Edits the position of the HUDItem in the preview screen. This is done
	 * by recording the distance from the starting location.
	 * 
	 * @param deltaX		Horizontal change of position of the HUDItem.
	 * @param deltaY		Vertical change of position of the HUDItem.
	 */
	public void editPosition(int deltaX, int deltaY) {
		int screenHeight = HudItemManager.getInstance().getRes().getScaledHeight();
		int screenWidth = HudItemManager.getInstance().getRes().getScaledWidth();
		deltaX = MathHelper.clamp_int(deltaX, -xPosition, screenWidth - width - xPosition);
		deltaY = MathHelper.clamp_int(deltaY, -yPosition, screenHeight - height - yPosition);
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	/**
	 * Horizontal change of position of the HUDItem.
	 */
	private int deltaX;
	
	/**
	 * Vertical change of position of the HUDItem.
	 */
	private int deltaY;
	
	/**
	 * HUDItem that this button represents.
	 */
	private HudItem hudItem;
}
