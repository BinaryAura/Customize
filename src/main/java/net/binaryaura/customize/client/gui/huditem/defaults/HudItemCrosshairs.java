package net.binaryaura.customize.client.gui.huditem.defaults;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.GuiScreenAdjustHud;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItem;
import net.binaryaura.customize.client.gui.huditem.HudItemIcon;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

/**
 * Icon denoting where a block will be placed (right click) or
 * which block will be hit/destroyed. The CROSSHAIRS are placed
 * in the center of the screen and cannot be moved. There are
 * multiple textures for the CROSSHAIRS that can be selected in
 * the {@link GuiScreenAdjustHud}. CROSSHAIRS is a {@link HudItemIcon}
 * and uses its constructor and renderer.
 * 
 * @author	BinaryAura
 * @see		HudItem
 * @see		HudItemIcon
 */
public class HudItemCrosshairs extends HudItemIcon {
	
	/**
	 * The location of the the CROSSHAIRS textures.
	 */
	public static final ResourceLocation crosshairs = new ResourceLocation(Customize.MODID, "textures/gui/crosshairs.png");

	/**
	 * Relative x-value where the icon will be rendered. This value
	 * is -8 to center the image on the center of the screen. This
	 * value cannot be changed.
	 * 
	 * @see	HudItem.Anchor
	 */
	private static final int DFLT_X = 0;
	
	/**
	 * Relative y-value where the icon will be rendered. This value
	 * is -8 to center the image on the center of the screen. This
	 * value cannot be changed.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final int DFLT_Y = 0;
	
	/**
	 * CROSSHAIRS texture that is used to render the image when no
	 * save entry is found.
	 */
	private static final int DFLT_ICO = 9;
	
	/**
	 * The reference point for the x and y values. CROSSHAIRS uses
	 * the Center Anchor. This cannot be changed.
	 * 
	 * @see HudItem.Anchor
	 */
	private static final Anchor DFLT_ANCH = Anchor.CENTER;
	
	/**
	 * Constructs an instance of the CROSSHAIRS icon with the specified
	 * <code>name</code>. This includes setting initial location values,
	 * orientation, and fetching the textures for the bar.
	 * 
	 * @param name		The name of the HUDItem.
	 */
	public HudItemCrosshairs(String name) {
		super(name);
		canMove = false;
	}
	
	/**
	 * Changes the texture used for rendering the CROSSHAIRS to <code>index</code>
	 * texture. 
	 * 
	 * @param			Index of the icon.
	 */
	public void changeIcon(int index) {
		switch(index) {
			case 61:
				x = 4;
				y = 6;
				break;
			case 62:
				x = 2;
				y = 6;
				break;
			case 63:
				x = 6;
				y = 7;
				break;
			default:
				x = DFLT_X;
				y = DFLT_Y;
				break;
		}	

		if(index >= 0 && index < icons.getAmount())
			icon = icons.getSprite(index);
		else
			log.warn("No icon with index of " + index + "for " + name);
	}

	/**
	 * Redefines anything that is used for rendering and decides
	 * whether the HUDItem should be rendered.
	 * 
	 * @param x			The relative x-value of the upper left corner.
	 * @param y			The relative y-value of the upper left corner.
	 */
	@Override
	public void renderHUDItem(int x, int y) {
		if(showCrosshairs()) {
			GlStateManager.tryBlendFuncSeparate(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR, 1, 0);
			super.renderHUDItem(x, y);
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		}
	}
	
	/**
	 * Initializes class specific fields such as location,
	 * orientation, and textures.
	 */
	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		x = DFLT_X;
		y = DFLT_Y;
		icons = new SpriteSet();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 16; j++) {
				icons.addSprite(new Sprite(crosshairs, j * 16, i * 16, 16, 16));
			}
		}
		icon = icons.getSprite(DFLT_ICO);
	}
	
	/**
	 * Determines whether the CROSSHAIRS should be rendered.
	 * 
	 * @return whether the CROSSHAIRS should be rendered.
	 */
	private boolean showCrosshairs() {
		if (mc.gameSettings.showDebugInfo && !mc.thePlayer.hasReducedDebug() && !mc.gameSettings.reducedDebugInfo) {
            return false;
        } else if (mc.playerController.isSpectator()) {
            if (mc.pointedEntity != null) {
                return true;
            } else {
                if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    BlockPos blockpos = mc.objectMouseOver.getBlockPos();

                    if (mc.theWorld.getTileEntity(blockpos) instanceof IInventory) {
                        return true;
                    }
                }

                return false;
            }
        } else {
            return true;
        }
	}
}
