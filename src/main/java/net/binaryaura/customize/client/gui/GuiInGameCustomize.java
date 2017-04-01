package net.binaryaura.customize.client.gui;


import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.border.WorldBorder;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Replacement InGameGui for Minecraft to display the HUD.
 * Instead of locally displaying HudItems. GuiInGameCustomize
 * refers to HudItemManager.
 * 
 * @see 	HudItemManager
 * 
 * @author	BinaryAura
 */
public class GuiInGameCustomize {
	
	/**
	 * Flag to render the HUD
	 */
	public static boolean renderHUD = true;

	/**
	 * Constructs an instance of the GUI
	 * 
	 * @param mc		Instance of Minecraft
	 */
	public GuiInGameCustomize(Minecraft mc) {
		this.mc = mc;
        this.persistantChatGUI = new GuiNewChat(mc);
	}

	public void updateTick() {
		for (HudItem hudItem : HudItemManager.REGISTRY.getAll()) {
			if(hudItem != null) hudItem.updateTick();
		}
	}

	/**
	 * Render the Game HUD overlay
	 */
	public void renderGameOverlay(RenderGameOverlayEvent eventParent) {
		this.eventParent = eventParent;
		HudItem.setEvent(eventParent);

		mc.entityRenderer.setupOverlayRendering();
		GlStateManager.enableBlend();
		
		// PRE
		
		if (Minecraft.isFancyGraphicsEnabled()) {
			renderVignette(mc.thePlayer.getBrightness(eventParent.partialTicks), eventParent.resolution);
		} else {
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		}

		if (GuiIngameForge.renderHelmet)
			renderHelmet(eventParent.resolution, eventParent.partialTicks);

		if (GuiIngameForge.renderPortal && !mc.thePlayer.isPotionActive(Potion.confusion)) {
			renderPortal(eventParent.resolution, eventParent.partialTicks);
		}
		
		// Iterates through all Pre HudItems
		for (HudItem hudItem : HudItemManager.REGISTRY.getPre()) {
			GL11.glPushMatrix();
			if(hudItem != null) hudItem.renderHUDItem();
			GL11.glPopMatrix();
		}
		
		// NORMAL
		
		// Iterates through all Normal HudItems
		for (HudItem hudItem : HudItemManager.REGISTRY.getNormal()) {
			GL11.glPushMatrix();
			if(hudItem != null)	hudItem.renderHUDItem();
			GL11.glPopMatrix();
		}
		
		// POST
		
		// Iterates through all Post HudItems
		for (HudItem hudItem : HudItemManager.REGISTRY.getPost()) {
			GL11.glPushMatrix();
			if(hudItem != null) hudItem.renderHUDItem();
			GL11.glPopMatrix();
		}
		
		renderSleepFade(eventParent.resolution.getScaledWidth(), eventParent.resolution.getScaledHeight());
		
        renderChat(eventParent.resolution.getScaledWidth(), eventParent.resolution.getScaledHeight());
	}

    /**
     * Renders a Vignette arount the entire screen that changes with light level.
     */
	@Deprecated
    protected void renderVignette(float p_180480_1_, ScaledResolution p_180480_2_)
    {
        p_180480_1_ = 1.0F - p_180480_1_;
        p_180480_1_ = MathHelper.clamp_float(p_180480_1_, 0.0F, 1.0F);
        WorldBorder worldborder = this.mc.theWorld.getWorldBorder();
        float f = (float)worldborder.getClosestDistance(this.mc.thePlayer);
        double d0 = Math.min(worldborder.getResizeSpeed() * (double)worldborder.getWarningTime() * 1000.0D, Math.abs(worldborder.getTargetSize() - worldborder.getDiameter()));
        double d1 = Math.max((double)worldborder.getWarningDistance(), d0);

        if ((double)f < d1)
        {
            f = 1.0F - (float)((double)f / d1);
        }
        else
        {
            f = 0.0F;
        }

        this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(p_180480_1_ - this.prevVignetteBrightness) * 0.01D);
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);

        if (f > 0.0F)
        {
            GlStateManager.color(0.0F, f, f, 1.0F);
        }
        else
        {
            GlStateManager.color(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
        }

        this.mc.getTextureManager().bindTexture(vignetteTexPath);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(0.0D, (double)p_180480_2_.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
        worldrenderer.pos((double)p_180480_2_.getScaledWidth(), (double)p_180480_2_.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos((double)p_180480_2_.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
    }

	@Deprecated
    protected void renderSleepFade(int width, int height)
    {
        if (mc.thePlayer.getSleepTimer() > 0)
        {
            mc.mcProfiler.startSection("sleep");
            GlStateManager.disableDepth();
            GlStateManager.disableAlpha();
            int sleepTime = mc.thePlayer.getSleepTimer();
            float opacity = (float)sleepTime / 100.0F;

            if (opacity > 1.0F)
            {
                opacity = 1.0F - (float)(sleepTime - 100) / 10.0F;
            }

            int color = (int)(220.0F * opacity) << 24 | 1052704;
            Gui.drawRect(0, 0, width, height, color);
            GlStateManager.enableAlpha();
            GlStateManager.enableDepth();
            mc.mcProfiler.endSection();
        }
    }
	
	/**
	 * Renders the Portal Effect. This effect is not part of the HUD.
	 */
	@Deprecated
	protected void renderPortal(ScaledResolution res, float partialTicks)
    {
        float f1 = mc.thePlayer.prevTimeInPortal + (mc.thePlayer.timeInPortal - mc.thePlayer.prevTimeInPortal) * partialTicks;

        if (f1 > 0.0F)
        {
            renderPortal(f1, res);
        }
    }

	@Deprecated
    protected void renderPortal(float p_180474_1_, ScaledResolution p_180474_2_)
    {
        if (p_180474_1_ < 1.0F)
        {
            p_180474_1_ = p_180474_1_ * p_180474_1_;
            p_180474_1_ = p_180474_1_ * p_180474_1_;
            p_180474_1_ = p_180474_1_ * 0.8F + 0.2F;
        }

        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, p_180474_1_);
        this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        TextureAtlasSprite textureatlassprite = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.portal.getDefaultState());
        float f = textureatlassprite.getMinU();
        float f1 = textureatlassprite.getMinV();
        float f2 = textureatlassprite.getMaxU();
        float f3 = textureatlassprite.getMaxV();
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(0.0D, (double)p_180474_2_.getScaledHeight(), -90.0D).tex((double)f, (double)f3).endVertex();
        worldrenderer.pos((double)p_180474_2_.getScaledWidth(), (double)p_180474_2_.getScaledHeight(), -90.0D).tex((double)f2, (double)f3).endVertex();
        worldrenderer.pos((double)p_180474_2_.getScaledWidth(), 0.0D, -90.0D).tex((double)f2, (double)f1).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -90.0D).tex((double)f, (double)f1).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
	
	/**
	 * Renders the Helmet (e.g. Pumpkin). This is not part of the HUD.
	 * 
	 * @param res				Resolution of the Minecraft Window
	 * @param partialTicks		
	 */
	@Deprecated
	private void renderHelmet(ScaledResolution res, float partialTicks) {
		ItemStack itemstack = this.mc.thePlayer.inventory.armorItemInSlot(3);

		if (this.mc.gameSettings.thirdPersonView == 0 && itemstack != null && itemstack.getItem() != null) {
			if (itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
				renderPumpkinOverlay(res);
			} else {
				itemstack.getItem().renderHelmetOverlay(itemstack, mc.thePlayer, res, partialTicks);
			}
		}
	}

	@Deprecated
    protected void renderPumpkinOverlay(ScaledResolution p_180476_1_)
    {
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableAlpha();
        this.mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(0.0D, (double)p_180476_1_.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
        worldrenderer.pos((double)p_180476_1_.getScaledWidth(), (double)p_180476_1_.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos((double)p_180476_1_.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
	
	/**
	 * Renders the Chat Screen. Chat is rendered as a screen and as a result, chat is not part of the HUD.
	 */
	@Deprecated
	protected void renderChat(int width, int height)
    {
        mc.mcProfiler.startSection("chat");

        RenderGameOverlayEvent.Chat event = new RenderGameOverlayEvent.Chat(eventParent, 0, height - 48);
        if (MinecraftForge.EVENT_BUS.post(event)) return;
        
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)event.posX, (float)event.posY, 0.0F);
        persistantChatGUI.drawChat(HudItemManager.updateCounter);
        GlStateManager.popMatrix();

        mc.mcProfiler.endSection();
    }
	
	@Deprecated
    protected static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
	@Deprecated
    protected static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");
	
	@Deprecated
	private float prevVignetteBrightness = 1.0F;
	
	private Minecraft mc;
	
	@Deprecated
	private GuiNewChat persistantChatGUI;
	
	/**
	 * RenderEvent that called the renderGameOverlay method
	 */
	private RenderGameOverlayEvent eventParent;
}
