package net.binaryaura.customize.client.gui;

import java.io.IOException;

import net.binaryaura.customize.common.util.Localization;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiHudItemMenu extends GuiScreen {
	
	public GuiHudItemMenu(GuiScreen parentScreen, GuiButtonHudItem hudItem) {
		this.selected = hudItem;
		setWorldAndResolution(parentScreen.mc, parentScreen.width, parentScreen.height);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		buttonList.clear();
		//	TODO: Setup HudItem Menus and their locations
		buttonList.add(new GuiButton(100, 10, 10, Localization.get("menu.test")));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
	}
	
	protected GuiButtonHudItem selected;

}
