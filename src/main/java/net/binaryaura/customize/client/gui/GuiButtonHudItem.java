package net.binaryaura.customize.client.gui;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
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
		mc = Minecraft.getMinecraft();
		hudItem = HudItemManager.REGISTRY.get(name);
		updateValues();
		originalPriority = HudItemManager.REGISTRY.indexOf(hudItem);
		originalAnchor = hudItem.getAnchor();
		originalOrientation = hudItem.getOrientation();
		originalX = x;
		originalY = y;
	}
	
	//	Button needs to be adjusted to extend in the direction of the button 
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		if(visible) {
			int x = xPosition + deltaX + moveX;
			int y = yPosition + deltaY + moveY;
			hovered = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
			FontRenderer fontrenderer = mc.fontRendererObj;
			if(hudItem != null) {
				GL11.glPushMatrix();
				if(hovered || hudItem.guiBackground())
					drawRect(x, y, x + width, y + height, Color.WHITE + (0x22 << 24));
				hudItem.renderHUDItem(x, y);
				GL11.glPopMatrix();
			} else {
				Customize.log.warn(hudItem + " doesn't exist.");
				return;
			}
			
			//	TODO: Fix Name Rendering so only one name shows			
			if(hovered) {
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0, 200F);
				this.drawCenteredString(fontrenderer, displayString.toUpperCase(), mouseX, mouseY - fontrenderer.FONT_HEIGHT, Color.WHITE);
				GL11.glPopMatrix();
			}
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		int x = xPosition + deltaX;
		int y = yPosition + deltaY;
		return enabled && visible && mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
	}
	
	/**
	 * Resets the HudItem's Anchor to <code>anchor</code>.
	 * 
	 * @param anchor		New anchor for the HudItem.
	 */
	public void setAnchor(Anchor anchor) {
		hudItem.setAnchor(anchor);
		updateValues();
		editPosition(0, 0);
	}
	
	/**
	 * Rotates the HudItem.
	 */
	public void rotate() {
		if(hudItem.canRotate()) {
			hudItem.rotateLeft();
			updateValues();
			editPosition(0,0);
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
	
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		deltaX += moveX;
		deltaY += moveY;
		moveX = moveY = 0;
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
		deltaX = MathHelper.clamp_int(deltaX, -xPosition - this.deltaX, screenWidth - width - xPosition - this.deltaX);
		deltaY = MathHelper.clamp_int(deltaY, -yPosition - this.deltaY, screenHeight - height - yPosition - this.deltaY);
		this.moveX = deltaX;
		this.moveY = deltaY;
	}
	
	public void revert() {
		hudItem.setOrientation(originalOrientation);
		hudItem.setAnchor(originalAnchor);
		hudItem.moveTo(originalPriority);
	}
	
	private void updateValues() {
		hudItem.preRender();
		xPosition = hudItem.getX();
		yPosition = hudItem.getY();
		width = hudItem.getWidth();
		height = hudItem.getHeight();
	}
	
	/**
	 * Horizontal change of position of the HUDItem.
	 */
	protected int moveX;
	
	/**
	 * Vertical change of position of the HUDItem.
	 */
	protected int moveY;
	
	/**
	 * Horizontal change of position saved as of the last release of the mouse
	 */
	protected int deltaX;
	
	/**
	 * Vertical change of position saved as of the last release of the mouse
	 */
	protected int deltaY;
	
	/**
	 * HUDItem that this button represents.
	 */
	private HudItem hudItem;
	
	/**
	 * Original x value for reversion
	 */
	private int originalX;
	
	/**
	 * Original y value for reversion
	 */
	private int originalY;
	
	/**
	 * Original orientation for reversion
	 */
	private HudItem.Orientation originalOrientation;
	
	/**
	 * Original anchor for reversion
	 */
	private HudItem.Anchor originalAnchor;
	
	/**
	 * Original priority for reversion
	 */
	private int originalPriority;
	
	/**
	 * Minecraft
	 */
	private Minecraft mc;
}
