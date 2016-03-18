package net.binaryaura.customize.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiScreenAdjustHud extends GuiScreen {
	
	public GuiScreenAdjustHud() {
		GuiInGameCustomize.renderHUD = false;
	}

	@Override
	public void initGui() {
		buttonList.clear();
        for (HudItem hudItem : HudItemManager.getHudItems()) {
                if(hudItem != null)	buttonList.add(new GuiButtonHudItem(hudItem));
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(selected != null) {
			drawDefaultBackground();
			selected.drawButton(mc, mouseX, mouseY);
		}	
		if(menu != null) {
			menu.drawScreen(mouseX, mouseY, partialTicks);
		}
		if(isCtrlKeyDown()) drawString(mc.fontRendererObj, mouseX + " : " + mouseY, 10, 10, Color.WHITE);
		
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof GuiButtonHudItem) {
			Customize.log.info(button == selected);
			if(menu == null || button != selected){
				menu = new GuiHudItemMenu(this, (GuiButtonHudItem) button);
				selectButton((GuiButtonHudItem) button);
			} else
				onMenuClosed();
		}
		super.actionPerformed(button);
	}
	
	protected void secondaryActionPerformed(GuiButton button) throws IOException {
		if(button instanceof GuiButtonHudItem) {
			
		    if (button != null) {
		    	selectButton((GuiButtonHudItem) button);
		    	selected.rotate();
		    	initGui();
		    	deselectButton();
		    }
		    if(menu != null) {
		    	onMenuClosed();
		    }
		 	super.actionPerformed(button);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		// 	TODO: Set up Key Controls
		//		LEFT / RIGHT -- PREV / NEXT CONFIG (SAVES)
		//		DOWN / UP -- PREV / NEXT CONFIG PAGE (SAVES)
		//  	0-9 -- LOAD CONFIG # (SAVES)
		//		DEL -- DELETE CONFIG (RESET TO DEFAULT) (CONFIRM) (SAVES)
		//		ESC -- EXIT (SAVES)
		//		BS -- CANCEL CHANGES
		//		HOLD CTRL -- SHOW ANCHORS
		//		F1 -- HELP
		
		if(keyCode >= Keyboard.KEY_1 && keyCode <= Keyboard.KEY_0) {
			Customize.log.info("CONFIGURATION " + typedChar);
		}
		//	TODO: Create config class
		switch(keyCode) {
			case Keyboard.KEY_LEFT:
				Customize.log.info("PREVIOUS CONFIGURATION");
				//	SAVE
				break;
			case Keyboard.KEY_RIGHT:
				Customize.log.info("NEXT CONFIGURATION");
				//	SAVE
				break;
			case Keyboard.KEY_DOWN:
				Customize.log.info("PREVIOUS PAGE");
				//	SAVE
				break;
			case Keyboard.KEY_UP:
				Customize.log.info("NEXT PAGE");
				// 	SAVE
				break;
			case Keyboard.KEY_DELETE:
				Customize.log.info("DELETE CONFIGURATION");
				break;
			case Keyboard.KEY_ESCAPE:
				Customize.log.info("EXIT");
				//	SAVE
				GuiInGameCustomize.renderHUD = true;
				
				this.mc.displayGuiScreen((GuiScreen)null);
	            if (this.mc.currentScreen == null) 
	                this.mc.setIngameFocus();
				break;
			case Keyboard.KEY_BACK:
				Customize.log.info("CANCEL CHANGES");
				break;
			case Keyboard.KEY_F1:
				Customize.log.info("HELP");
				break;
			case Keyboard.KEY_NUMPAD7:
				if(selected != null) selected.setAnchor(Anchor.TOPLEFT);
				break;
			case Keyboard.KEY_NUMPAD8:
				if(selected != null) selected.setAnchor(Anchor.TOP);
				break;
			case Keyboard.KEY_NUMPAD9:
				if(selected != null) selected.setAnchor(Anchor.TOPRIGHT);
				break;
			case Keyboard.KEY_NUMPAD4:
				if(selected != null) selected.setAnchor(Anchor.LEFT);
				break;
			case Keyboard.KEY_NUMPAD5:
				if(selected != null) selected.setAnchor(Anchor.CENTER);
				break;
			case Keyboard.KEY_NUMPAD6:
				if(selected != null) selected.setAnchor(Anchor.RIGHT);
				break;
			case Keyboard.KEY_NUMPAD1:
				if(selected != null) selected.setAnchor(Anchor.BOTTOMLEFT);
				break;
			case Keyboard.KEY_NUMPAD2:
				if(selected != null) selected.setAnchor(Anchor.BOTTOM);
				break;
			case Keyboard.KEY_NUMPAD3:
				if(selected != null) selected.setAnchor(Anchor.BOTTOMRIGHT);
				break;
			case Keyboard.KEY_HOME:
				GuiButtonHudItem button;
				for (int k = 0; k < this.buttonList.size(); ++k) {
		            if(buttonList.get(k) instanceof GuiButtonHudItem) {
		            	button = (GuiButtonHudItem) buttonList.get(k);
		            	button.recover();
		            }
		        }
				initGui();
				break;
		}
	}
	//	TODO: Fix Draging Dual Click Bug
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		//	LEFT -- HUDITEM MENU (HOVERED) / CLOSE HUDITEM MENU
		//	RIGHT -- ROTATE HUDITEM
		Customize.log.info("CLICK");
		if(menu != null) {
			menu.mouseClicked(mouseX, mouseY, mouseButton);
		}
		for (int l = 0; l < this.buttonList.size(); ++l) {
			GuiButton guibutton = (GuiButton)this.buttonList.get(l);
			
			if(guibutton.mousePressed(mc, mouseX, mouseY)) {
				ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, buttonList);
				if(MinecraftForge.EVENT_BUS.post(event))
					break;
				event.button.playPressSound(mc.getSoundHandler());
				
				//	Mouse Buttons
				switch(mouseButton) {
					case 0:
						actionPerformed(event.button);
						mouseStartX = mouseX;
						mouseStartY = mouseY;
						break;
					case 1:
						secondaryActionPerformed(event.button);
						break;
				}
				
				if(this.equals(mc.currentScreen))
					MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, buttonList));
				return;
			}
		}
		
		//	Clicked Nothing
		if(menu != null)	onMenuClosed();
		deselectButton();
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		Customize.log.info("RELEASE");
		if(selected != null && mouseHeld) {
			mouseHeld = false;
			selected.savePosition();
			deselectButton();
			Customize.log.info("MOUSE: (" + mouseX + ":" + mouseY + ")");
			initGui();
		}
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		//	LEFT -- MOVE HUDITEM
		if(menu != null)	onMenuClosed();
		if(selected != null && clickedMouseButton == 0) {
			if(!mouseHeld)	mouseHeld = true;
			selected.editPosition(mouseX - mouseStartX, mouseY - mouseStartY);
		}
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	protected void deselectButton() {
		Customize.log.info("Deselected Button");
		selected = null;
	}
	
	protected void selectButton(GuiButtonHudItem button) {
		Customize.log.info("Selected Button");
		selected = button;
	}
	
	protected void onMenuClosed() {
		Customize.log.info("Closing Menu");
		menu.onGuiClosed();
		menu = null;
	}
	
	private boolean mouseHeld = false;
	private int mouseStartX;
	private int mouseStartY;
	protected GuiButtonHudItem selected;
	protected GuiHudItemMenu menu;
}
