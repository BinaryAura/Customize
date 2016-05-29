package net.binaryaura.customize.common;

import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

/**
 * Handles what to do every tick.
 * 
 * For this mod it's only purpose is to ensure the Customize in game gui
 * is being used. since their is no event defining this, a tick handler
 * must be used.
 * 
 * @author	BinaryAura
 */
public class TickHandler {

	Minecraft mc = Customize.mc;
	
	/**
	 * Checks if the in game GUI is Customize's gui. If not reset to the
	 * appropriate gui. If the mod is ever disabled, this would need to
	 * redefined back to Forge's in game gui.
	 * 
	 * @param event		Tick Event
	 */
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		GuiIngame gui = mc.ingameGUI;
		if(!(gui instanceof GuiInGameCustomize)) {
			Minecraft.getMinecraft().ingameGUI = new GuiInGameCustomize(mc);
			Customize.log.info("Gui Initialized");
		}
	}
}
