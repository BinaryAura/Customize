package net.binaryaura.customize.client.util;

import org.lwjgl.input.Keyboard;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyRegister {

	public static KeyBinding hudConfig = new KeyBinding("Adjust HUD", Keyboard.KEY_H, "key.categories.misc");
	
	static {
		ClientRegistry.registerKeyBinding(hudConfig);
	}
	
	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event) {
		if (hudConfig.isPressed() && Customize.mc.currentScreen == null) {
            Customize.mc.displayGuiScreen(new GuiScreenAdjustHud());
        }
	}

}
