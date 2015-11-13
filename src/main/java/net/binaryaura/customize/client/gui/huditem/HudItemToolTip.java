package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemToolTip extends HudItemText {

	public HudItemToolTip(String name) {
		super(name);
		canFlip = false;
		canRotate = false;
	}

	@Override
	protected void init() {
		x = 0;
		y = 0;
	}
	
	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
				
		if(this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
			String s = this.highlightingItemStack.getDisplayName();
			
		    if (this.highlightingItemStack.hasDisplayName()) {
		        s = EnumChatFormatting.ITALIC + s;
		    }
		    super.renderHUDItem(res, eventParent);
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
		return HudItem.Color.BLACK;
	}

	@Override
	protected int getColor() {
		return HudItem.Color.WHITE;
	}

	@Override
	protected int getBGAlpha() {
		return 0;
	}

	@Override
	protected int getAlpha() {
		int k = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
		
	    if (k > 255) k = 255;
	    
		return k;
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
