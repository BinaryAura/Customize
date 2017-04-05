package net.binaryaura.customize.client.gui.huditem.defaults;

import org.lwjgl.opengl.GL11;

import javafx.scene.layout.BorderRepeat;
import net.binaryaura.customize.client.gui.GuiInGameCustomize;
import net.binaryaura.customize.client.gui.huditem.HudItemManager;
import net.binaryaura.customize.client.gui.huditem.HudItemText;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * Text designating information, usually regarding the
 * active HOTBAR item. TOOLTIP is a {@link HudItemText}
 * and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see 	HudItemText
 */
public class HudItemToolTip extends HudItemText {
	
	/**
	 * Relative x-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the text will be rendered if no save
	 * entry is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = -59;
	
	/**
	 * The reference point for the x and y values when no save entry
	 * is found.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	
	/**
	 * Text style for the object to be rendered.
	 * 
	 * @see HudItemText.Style
	 */
	private static final Style DFLT_STY = Style.SHADOWED;

	/**
	 * Constructs an instance of the TOOLTIP object with the specified
	 * <code>name</code>. This includes setting initial location values,
	 * orientation, and fetching the textures for the text.
	 * @param name
	 */
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
		log.info(isInPreview());
		if(mc.gameSettings.heldItemTooltips && !mc.playerController.isSpectator() || isInPreview()) {
			if(remainingHighlightTicks > 0 && highlightingItemStack != null || isInPreview()) {
			    
				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
				
				log.info(isInPreview());
			    super.renderHUDItem(x, y);
			    
				GlStateManager.disableBlend();
			}
		//	Unfortunately the Spectator Menu is stuck where it is. Thank you protected fields.
		} else if(this.mc.thePlayer.isSpectator()) {
//			GuiInGameCustomize guiInGame = (GuiInGameCustomize) mc.ingameGUI;
//			guiInGame.getSpectatorGui().func_175263_a(hudManager.getRes());
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
	protected String getString() {
		String string = this.highlightingItemStack.getDisplayName();		
	    if (this.highlightingItemStack.hasDisplayName()) {
	        string = EnumChatFormatting.ITALIC + string;
	    }
	    return string;
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
	
	/**
	 * Remaining ticks that the text is visible.
	 */
	private int remainingHighlightTicks;
	
	/**
	 * ItemStack of the active item in the HOTBAR.
	 */
	private ItemStack highlightingItemStack;

	/* (non-Javadoc)
	 * @see net.binaryaura.customize.client.gui.huditem.HudItemText#getSize()
	 */
	@Override
	protected float getSize() {
		return 0;
	}

}
