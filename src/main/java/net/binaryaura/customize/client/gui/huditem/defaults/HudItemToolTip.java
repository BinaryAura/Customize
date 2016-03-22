package net.binaryaura.customize.client.gui.huditem.defaults;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.binaryaura.customize.client.gui.huditem.HudItemText;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class HudItemToolTip extends HudItemText {
	
	private static final int DFLT_X = 0;
	private static final int DFLT_Y = -59;
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	private static final Style DFLT_STY = Style.SHADOWED;

	public HudItemToolTip(String name) {
		super(name);
	}

	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		x = DFLT_X;
		y = DFLT_Y;
		style = DFLT_STY;
	}
	
	@Override
	public void renderHUDItem(int x, int y) {
		if(mc.gameSettings.heldItemTooltips && !mc.playerController.isSpectator()) {
			if(remainingHighlightTicks > 0 && highlightingItemStack != null) {
				string = this.highlightingItemStack.getDisplayName();		
			    if (this.highlightingItemStack.hasDisplayName()) {
			        string = EnumChatFormatting.ITALIC + string;
			    }
			    
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
				
			    super.renderHUDItem(x, y);
			    
				GlStateManager.disableBlend();
			}
			//	Unfortunately the Spectator Menu is stuck where it is. Thank you protected fields.
		} else if(this.mc.thePlayer.isSpectator()) {
			GuiInGameCustomize guiInGame = (GuiInGameCustomize) mc.ingameGUI;
			guiInGame.getSpectatorGui().func_175263_a(hudManager.getRes());
		}
	}
	    
	@Override
	protected int getDeltaX() {
		return 0;
	}
	
	@Override
	protected int getDeltaY() {
		int delta = 0;
		if(!mc.playerController.shouldDrawHUD()) delta += 14;
		
		return delta;	
	}
	    
	@Override
	protected int getBGColor() {
		return BLACK;
	}

	@Override
	protected int getColor() {
		return WHITE;
	}

	@Override
	protected int getBGAlpha() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		int alpha = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
		
	    if (alpha > 255) alpha = 255;
	    
		return alpha;
	}
	
	@Override
	public void updateTick() {
		if (this.mc.thePlayer != null)
        {
            ItemStack itemstack = this.mc.thePlayer.inventory.getCurrentItem();

            if (itemstack == null)
            {
                this.remainingHighlightTicks = 0;
            }
            else if (this.highlightingItemStack != null && itemstack.getItem() == this.highlightingItemStack.getItem() && ItemStack.areItemStackTagsEqual(itemstack, this.highlightingItemStack) && (itemstack.isItemStackDamageable() || itemstack.getMetadata() == this.highlightingItemStack.getMetadata()))
            {
                if (this.remainingHighlightTicks > 0)
                {
                    --this.remainingHighlightTicks;
                }
            }
            else
            {
                this.remainingHighlightTicks = 40;
            }

            this.highlightingItemStack = itemstack;
        }
		super.updateTick();
	}
	
	private int remainingHighlightTicks;
	private ItemStack highlightingItemStack;

}
