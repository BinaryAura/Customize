package net.binaryaura.customize.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class GuiInGameCustomize extends GuiIngameForge {

	public GuiInGameCustomize(Minecraft mc) {
		super(mc);
	}

	@Override
	public void renderGameOverlay(float partialTicks) {
		res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
	}

	@Override
	public ScaledResolution getResolution() {
		// TODO Auto-generated method stub
		return res;
	}
	
	private ScaledResolution res = null;
    private RenderGameOverlayEvent eventParent;
}
