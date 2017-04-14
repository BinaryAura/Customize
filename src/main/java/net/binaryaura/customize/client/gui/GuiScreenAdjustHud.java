package net.binaryaura.customize.client.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import jline.internal.Log;
import net.binaryaura.customize.client.util.Color;
import net.binaryaura.customize.client.util.KeyRegister;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItem.Anchor;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
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
        		
                if(hudItem != null && !hudItem.isGraphic()) {
                	buttonList.add(new GuiButtonHudItem(hudItem));
                }
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
		
		if (keyCode == KeyRegister.hudPrevConfig.getKeyCode()) {
			Customize.log.info("PREVIOUS CONFIGURATION");
			//	SAVE
		} else if (keyCode == KeyRegister.hudNextConfig.getKeyCode()) {
			Customize.log.info("NEXT CONFIGURATION");
			//	SAVE
		} else if (keyCode == KeyRegister.hudPrevPage.getKeyCode()) {
			Customize.log.info("PREVIOUS PAGE");
			// SAVE
		} else if (keyCode == KeyRegister.hudNextPage.getKeyCode()) {
			Customize.log.info("NEXT PAGE");
			// 	SAVE
		} else if (keyCode == KeyRegister.hudDelConfig.getKeyCode()) {
			Customize.log.info("DELETE CONFIGURATION");
		} else if(keyCode == KeyRegister.hudSaveExit.getKeyCode()) {
			Customize.log.info("EXIT");
			//	SAVE
			for(GuiButton button : buttonList) {
				if(button instanceof GuiButtonHudItem) {
					GuiButtonHudItem hudButton = (GuiButtonHudItem) button;
					hudButton.savePosition();
				}
			}
			onClosed();
		} else if(keyCode == KeyRegister.hudExit.getKeyCode()) {
			Customize.log.info("CANCEL CHANGES");
			for (GuiButton button : buttonList) {
	            if(button instanceof GuiButtonHudItem) {
	            	GuiButtonHudItem hudButton = (GuiButtonHudItem) button;
	            	hudButton.revert();
	            }
	        }
			onClosed();
		} else if(keyCode == KeyRegister.hudHelp.getKeyCode()) {
			Customize.log.info("HELP");
		} else if(keyCode == KeyRegister.hudAnchorTopLeft.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.TOPLEFT);
		} else if(keyCode == KeyRegister.hudAnchorTop.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.TOP);
		} else if(keyCode == KeyRegister.hudAnchorBottomRight.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.TOPRIGHT);
		} else if(keyCode == KeyRegister.hudAnchorLeft.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.LEFT);
		} else if(keyCode == KeyRegister.hudAnchorCenter.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.CENTER);
		} else if(keyCode == KeyRegister.hudAnchorRight.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.RIGHT);
		} else if(keyCode == KeyRegister.hudAnchorBottomLeft.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.BOTTOMLEFT);
		} else if(keyCode == KeyRegister.hudAnchorBottom.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.BOTTOM);
		} else if(keyCode == KeyRegister.hudAnchorBottomRight.getKeyCode()) {
			if(selected != null) selected.setAnchor(Anchor.BOTTOMRIGHT);
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
			GuiButton guibutton = (GuiButton) buttonList.get(l);
			
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
			selected.mouseReleased(mouseX, mouseY);
			mouseHeld = false;
			deselectButton();
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
	 * Closes the screen in Minecraft
	 */
	private void onClosed() {
		GuiInGameCustomize.renderHUD = true;
		
		mc.displayGuiScreen((GuiScreen)null);
		if (mc.currentScreen == null)
			mc.setIngameFocus();		
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
