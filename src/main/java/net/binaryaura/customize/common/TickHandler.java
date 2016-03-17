package net.binaryaura.customize.common;

import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class TickHandler {

	Minecraft mc = Customize.mc;
	
	@SubscribeEvent
	public void renderTick(RenderTickEvent event) {
		GuiIngame gui = mc.ingameGUI;
		if(!(gui instanceof GuiInGameCustomize)) {
			Minecraft.getMinecraft().ingameGUI = new GuiInGameCustomize(mc);
			Customize.log.info("Gui Initialized");
		}
	}
}
