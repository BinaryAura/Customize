package net.binaryaura.customize.client.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenAdjustHud extends GuiScreen {

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		buttonList.clear();
        for (HudItem hudItem : HudItemManager.hudItems.values()) {
        		Customize.log.info(hudItem.getId() + " : " + hudItem.getName());
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
		//	LEFT / RIGHT -- PREV / NEXT CONFIG (SAVES)
		//	DOWN / UP -- PREV / NEXT CONFIG PAGE (SAVES)
		//  0-9 -- LOAD CONFIG # (SAVES)
		//	DEL -- DELETE CONFIG (RESET TO DEFAULT) (CONFIRM) (SAVES)
		//	ESC -- EXIT (SAVES)
		//	BS -- CANCEL CHANGES
		//	HOLD CTRL -- SHOW ANCHORS
		//	F1 -- HELP
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		//	LEFT -- HUDITEM MENU (HOVERED) / CLOSE HUDITEM MENU
		//	RIGHT -- ROTATE HUDITEM
		Customize.log.info("CLICK");
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		//	LEFT -- MOVE HUDITEM
		Customize.log.info("MOVING");
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	

}
