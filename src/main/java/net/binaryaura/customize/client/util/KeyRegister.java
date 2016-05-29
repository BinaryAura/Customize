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

	/**
	 * Hotkey for the Preview Screen.
	 */
	public static KeyBinding hudConfig = new KeyBinding("Adjust HUD", Keyboard.KEY_H, "key.categories.misc");
	
	/**
	 * Registers the Preview Screen Hotkey.
	 */
	static {
		ClientRegistry.registerKeyBinding(hudConfig);
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
