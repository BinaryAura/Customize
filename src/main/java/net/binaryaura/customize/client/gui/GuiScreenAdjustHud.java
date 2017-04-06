package net.binaryaura.customize.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Screen for adjusting the appearance and location of HudItems. Each
 * of the HudItems have there own {@link GuiButtonHudItem} that govern
 * its display. When an HudItem is selected the menu screen is activated
 * using the selected HudItem's menu. 
 *  
 * @author	BinaryAura
 * @see 	GuiButtonHudItem
 * @see 	GuiHudItemMenu
 *
 */
public class GuiScreenAdjustHud extends GuiScreen {
	
	/**
	 * Constructs the Preview Screen
	 */
	public GuiScreenAdjustHud() {
		GuiInGameCustomize.renderHUD = false;
	}

	@Override
	public void initGui() {
		buttonList.clear();
        for (HudItem hudItem : HudItemManager.REGISTRY.getAll()) {
        		
                if(hudItem != null && !hudItem.isGraphic())	buttonList.add(new GuiButtonHudItem(hudItem));
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

	/**
	 * Called when the left mouse button is clicked
	 * 
	 * @param button 		Button clicked, null when no button is clicked.
	 */
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button instanceof GuiButtonHudItem) {
			if(menu == null || button != selected){
				menu = new GuiHudItemMenu(this, (GuiButtonHudItem) button);
				selectButton((GuiButtonHudItem) button);
			} else
				onMenuClosed();
		}
		super.actionPerformed(button);
	}
	
	/**
	 * Called when the right mouse button is clicked.
	 * 
	 * @param button			Button clicked, null when no button is clicked.
	 * @throws IOException
	 */
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
		
		//	TODO: Add Pages for different situations
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
	//	TODO: Fix Dragging Dual Click Bug
	//		Should Rotate the HudItem without dropping it
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		//	LEFT -- HUDITEM MENU (HOVERED) / CLOSE HUDITEM MENU
		//	RIGHT -- ROTATE HUDITEM
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
		if(selected != null && mouseHeld) {
			mouseHeld = false;
			selected.savePosition();
			deselectButton();
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
	
	/**
	 * Deselects the HudItem.
	 */
	protected void deselectButton() {
		selected = null;
	}
	
	/**
	 * Selects the HudItem.
	 * @param button		HudItem to be selected.
	 */
	protected void selectButton(GuiButtonHudItem button) {
		selected = button;
	}
	
	/**
	 * Called when the Menu screen is closed.
	 */
	protected void onMenuClosed() {
		menu.onGuiClosed();
		menu = null;
	}
	
	/**
	 * Flag for when the mouse is being held.
	 */
	private boolean mouseHeld = false;
	
	/**
	 * X-value at the start of the mouse drag.
	 */
	private int mouseStartX;
	
	/**
	 * Y-value at the start of the mouse drag.
	 */
	private int mouseStartY;
	
	/**
	 * The selected HudItem.
	 */
	protected GuiButtonHudItem selected;
	
	/**
	 * The Menu to be displayed.
	 */
	protected GuiHudItemMenu menu;
}
