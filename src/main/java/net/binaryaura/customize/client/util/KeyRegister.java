package net.binaryaura.customize.client.util;

import org.lwjgl.input.Keyboard;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

/**
 * The KeyRegister is a listener for the keyboard. Specifically,
 * it listens for the <code>hudConfig</code> hotkey, which brings
 * up the preview screen.
 * 
 * @author	BinaryAura
 * @see 	GuiScreenAdjustHud
 *
 */
public class KeyRegister {
	
	private static String categoryGen = "key.categories.customize.general";
	private static String categoryAnchor = categoryGen; // "key.categories.customize.anchor";
	private static String categoryConfig = categoryGen; // "key.categories.customize.config";
	private static String categoryPage = categoryGen; // "key.categories.customize.page";

	/**
	 * Hotkey for the Preview Screen.
	 */
	public static KeyBinding hudConfig = new KeyBinding("Adjust HUD", Keyboard.KEY_H, categoryGen);
	
	/**
	 * Hotkey for Preview Screen Help
	 */
	public static KeyBinding hudHelp = new KeyBinding("Help", Keyboard.KEY_F1, categoryGen);
	
	/**
	 * Hotkey to save and exit the Preview Screen.
	 */
	public static KeyBinding hudSaveExit = new KeyBinding("Save Changes", Keyboard.KEY_ESCAPE, categoryGen);
	
	/**
	 * Hotkey to exit the Preview Screen without saving.
	 */
	public static KeyBinding hudExit = new KeyBinding("Exit", Keyboard.KEY_BACK, categoryGen);
	
	/**
	 * Hotkey to change anchor to the top left in the Preview Screen 
	 */
	public static KeyBinding hudAnchorTopLeft = new KeyBinding("Set Anchor Top Left", Keyboard.KEY_NUMPAD7, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the top in the Preview Screen
	 */
	public static KeyBinding hudAnchorTop = new KeyBinding("Set Anchor Top", Keyboard.KEY_NUMPAD8, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the top right in the Preview Screen
	 */
	public static KeyBinding hudAnchorTopRight = new KeyBinding("Set Anchor Top Right", Keyboard.KEY_NUMPAD9, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the left in the Preview Screen
	 */
	public static KeyBinding hudAnchorLeft = new KeyBinding("Set Anchor Left", Keyboard.KEY_NUMPAD4, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the center in the Preview Screen
	 */
	public static KeyBinding hudAnchorCenter = new KeyBinding("Set Anchor Center", Keyboard.KEY_NUMPAD5, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the right in the Preview Screen
	 */
	public static KeyBinding hudAnchorRight = new KeyBinding("Set Anchor Right", Keyboard.KEY_NUMPAD6, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the bottom left in the Preview Screen
	 */
	public static KeyBinding hudAnchorBottomLeft = new KeyBinding("Set Anchor Bottom Left", Keyboard.KEY_NUMPAD1, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the bottom in the Preview Screen
	 */
	public static KeyBinding hudAnchorBottom = new KeyBinding("Set Anchor Bottom", Keyboard.KEY_NUMPAD2, categoryAnchor);
	
	/**
	 * Hotkey to change anchor to the bottom right in the Preview Screen
	 */
	public static KeyBinding hudAnchorBottomRight = new KeyBinding("Set Anchor Bottom Right", Keyboard.KEY_NUMPAD3, categoryAnchor);
	
	/**
	 * Hotkey to switch to the next configuration
	 */
	public static KeyBinding hudNextConfig = new KeyBinding("Next Config", Keyboard.KEY_DOWN, categoryConfig);
	
	/**
	 * Hotkey to switch to the previous configuration
	 */
	public static KeyBinding hudPrevConfig = new KeyBinding("Previous Config", Keyboard.KEY_UP, categoryConfig);
	
	/**
	 * Hotkey to delete current configuration in the Preview Screen
	 */
	public static KeyBinding hudDelConfig = new KeyBinding("Delete Config", Keyboard.KEY_DELETE, categoryConfig);
	
	// Config keys?
	
	/**
	 * Hotkey to switch to the next page
	 */
	public static KeyBinding hudNextPage = new KeyBinding("Next Page", Keyboard.KEY_RIGHT, categoryPage);
	
	/**
	 * Hotkey to switch to the previous page
	 */
	public static KeyBinding hudPrevPage = new KeyBinding("Previous Page", Keyboard.KEY_LEFT, categoryPage);
	
	/**
	 * Hotkey to switch to the first page
	 */
	public static KeyBinding hudPage1 = new KeyBinding("Page 1", Keyboard.KEY_1, categoryPage);
	
	/**
	 * Hotkey to switch to the second page
	 */
	public static KeyBinding hudPage2 = new KeyBinding("Page 2", Keyboard.KEY_2, categoryPage);
	
	/**
	 * Hotkey to switch to the third page
	 */
	public static KeyBinding hudPage3 = new KeyBinding("Page 3", Keyboard.KEY_3, categoryPage);
	
	/**
	 * Hotkey to switch to the forth page
	 */
	public static KeyBinding hudPage4 = new KeyBinding("Page 4", Keyboard.KEY_4, categoryPage);
	
	/**
	 * Hotkey to switch to the fifth page
	 */
	public static KeyBinding hudPage5 = new KeyBinding("Page 5", Keyboard.KEY_5, categoryPage);
	
	/**
	 * Hotkey to switch to the sixth page
	 */
	public static KeyBinding hudPage6 = new KeyBinding("Page 6", Keyboard.KEY_6, categoryPage);
	
	/**
	 * Hotkey to switch to the seventh page
	 */
	public static KeyBinding hudPage7 = new KeyBinding("Page 7", Keyboard.KEY_7, categoryPage);
	
	/**
	 * Hotkey to switch to the eighth page
	 */
	public static KeyBinding hudPage8 = new KeyBinding("Page 8", Keyboard.KEY_8, categoryPage);
	
	/**
	 * Hotkey to switch to the ninth page
	 */
	public static KeyBinding hudPage9 = new KeyBinding("Page 9", Keyboard.KEY_9, categoryPage);
	
	/**
	 * Hotkey to switch to the tenth page
	 */
	public static KeyBinding hudPage10 = new KeyBinding("Page 10", Keyboard.KEY_0, categoryPage);
	
	/**
	 * Registers the Preview Screen Hotkey.
	 */
	static {
		ClientRegistry.registerKeyBinding(hudConfig);
		ClientRegistry.registerKeyBinding(hudAnchorTopLeft);
		ClientRegistry.registerKeyBinding(hudAnchorTop);
		ClientRegistry.registerKeyBinding(hudAnchorTopRight);
		ClientRegistry.registerKeyBinding(hudAnchorLeft);
		ClientRegistry.registerKeyBinding(hudAnchorCenter);
		ClientRegistry.registerKeyBinding(hudAnchorRight);
		ClientRegistry.registerKeyBinding(hudAnchorBottomLeft);
		ClientRegistry.registerKeyBinding(hudAnchorBottom);
		ClientRegistry.registerKeyBinding(hudAnchorBottomRight);
		ClientRegistry.registerKeyBinding(hudNextConfig);
		ClientRegistry.registerKeyBinding(hudPrevConfig);
		ClientRegistry.registerKeyBinding(hudNextPage);
		ClientRegistry.registerKeyBinding(hudPrevPage);
		ClientRegistry.registerKeyBinding(hudPage1);
		ClientRegistry.registerKeyBinding(hudPage2);
		ClientRegistry.registerKeyBinding(hudPage3);
		ClientRegistry.registerKeyBinding(hudPage4);
		ClientRegistry.registerKeyBinding(hudPage5);
		ClientRegistry.registerKeyBinding(hudPage6);
		ClientRegistry.registerKeyBinding(hudPage7);
		ClientRegistry.registerKeyBinding(hudPage8);
		ClientRegistry.registerKeyBinding(hudPage9);
		ClientRegistry.registerKeyBinding(hudPage10);
		ClientRegistry.registerKeyBinding(hudSaveExit);
		ClientRegistry.registerKeyBinding(hudExit);
	}
	
	/**
	 * Brings up the Preview Screen when the Preview Screen Hotkey is pressed.
	 * 
	 * @param event			event for the keyboard
	 */
	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		if (hudConfig.isPressed() && Customize.mc.currentScreen == null) {
            Customize.mc.displayGuiScreen(new GuiScreenAdjustHud());
        }
	}
}