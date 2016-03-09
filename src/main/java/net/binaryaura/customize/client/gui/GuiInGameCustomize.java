package net.binaryaura.customize.client.gui;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.ALL;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.CHAT;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.HELMET;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.PORTAL;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

public class GuiInGameCustomize extends GuiIngameForge {

	public GuiInGameCustomize(Minecraft mc) {
		super(mc);
		hudManager = Customize.hudManager;
	}

	@Override
	public void updateTick() {
		super.updateTick();
		for (HudItem hudItem : hudManager.hudItems.values()) {
			if(hudItem != null) hudItem.updateTick();
		}
	}

	@Override
	public void renderGameOverlay(float partialTicks) {
		res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		eventParent = new RenderGameOverlayEvent(partialTicks, res);
		HudItem.Anchor.updateRes(res);

		if (pre(ALL)) return;

		mc.entityRenderer.setupOverlayRendering();
		GlStateManager.enableBlend();

		if (Minecraft.isFancyGraphicsEnabled()) {
			func_180480_a(mc.thePlayer.getBrightness(partialTicks), res);
		} else {
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		}

		if (renderHelmet)
			renderHelmet(res, partialTicks);

		if (renderPortal && !mc.thePlayer.isPotionActive(Potion.confusion)) {
			renderPortal(res, partialTicks);
		}
		
		renderSleepFade(res.getScaledWidth(), res.getScaledHeight());
		
		for (HudItem hudItem : hudManager.hudItems.values()) {
			GL11.glPushMatrix();
			if(hudItem != null) hudItem.renderHUDItem(res, eventParent);
			GL11.glPopMatrix();
		}
		
        renderChat(res.getScaledWidth(), res.getScaledHeight());
		
		post(ALL);
	}
	
	protected void renderPortal(ScaledResolution res, float partialTicks)
    {
        if (pre(PORTAL)) return;

        float f1 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * partialTicks;

        if (f1 > 0.0F)
        {
            func_180474_b(f1, res);
        }

        post(PORTAL);
    }

	@Override
	public ScaledResolution getResolution() {
		return res;
	}

	private void renderHelmet(ScaledResolution res, float partialTicks) {
		if (pre(HELMET))
			return;

		ItemStack itemstack = this.mc.thePlayer.inventory.armorItemInSlot(3);

		if (this.mc.gameSettings.thirdPersonView == 0 && itemstack != null && itemstack.getItem() != null) {
			if (itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
				func_180476_e(res);
			} else {
				itemstack.getItem().renderHelmetOverlay(itemstack, mc.thePlayer, res, partialTicks);
			}
		}

		post(HELMET);
	}
	
	protected void renderChat(int width, int height)
    {
        mc.mcProfiler.startSection("chat");

        RenderGameOverlayEvent.Chat event = new RenderGameOverlayEvent.Chat(eventParent, 0, height - 48);
        if (MinecraftForge.EVENT_BUS.post(event)) return;

        GlStateManager.pushMatrix();
        GlStateManager.translate((float)event.posX, (float)event.posY, 0.0F);
        persistantChatGUI.drawChat(updateCounter);
        GlStateManager.popMatrix();

        post(CHAT);

        mc.mcProfiler.endSection();
    }

	private boolean pre(ElementType type) {
		return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
	}

	private void post(ElementType type) {
		MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(eventParent, type));
	}

	private ScaledResolution res = null;
	private RenderGameOverlayEvent eventParent;
	private HudItemManager hudManager = null;
}
