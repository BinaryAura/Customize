package net.binaryaura.customize.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenAdjustHud extends GuiScreen {

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		buttonList.clear();
        for (HudItem hudItem : HudItemManager.hudItems.values()) {
                buttonList.add(new GuiButtonHudItem(hudItem.getId(), hudItem.getX(), hudItem.getY(), hudItem.getWidth(), hudItem.getHeight(), hudItem.getName()));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		//	LEFT / RIGHT -- PREV / NEXT CONFIG
		//	DOWN / UP -- PREV / NEXT CONFIG PAGE
		//  0-9 -- LOAD CONFIG #
		//	DEL -- DELETE CONFIG (RESET TO DEFAULT) (CONFIRM)
		//	BS -- CANCEL CHANGES
		//	ESC -- EXIT
		//	ALL KEYS CAUSE A SAVE
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		//	LEFT -- HUDITEM MENU (HOVERED) / CLOSE HUDITEM MENU
		//	RIGHT -- ROTATE HUDITEM
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		//	LEFT -- MOVE HUDITEM
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	

}
