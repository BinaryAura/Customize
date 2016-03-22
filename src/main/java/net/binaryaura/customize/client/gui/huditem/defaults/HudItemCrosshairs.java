package net.binaryaura.customize.client.gui.huditem.defaults;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemIcon;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public class HudItemCrosshairs extends HudItemIcon {
	
	public static final ResourceLocation crosshairs = new ResourceLocation(Customize.MODID, "textures/gui/crosshairs.png");

	private static final int DFLT_X = -8;
	private static final int DFLT_Y = -8;
	private static final int DFLT_ICO = 9;
	private static final Anchor DFLT_ANCH = Anchor.CENTER;
	
	public HudItemCrosshairs(String name) {
		super(name);
		canMove = false;
	}
	
	@Override
	public void changeIcon(int index) {
		if(index == 61) {
			x = -3;
			y = 1;
		} else if(index == 62) {
			x = -6;
			y = 1;
		} else if(index == 63) {
			x = 1;
			y = 1;
		} else {
			x = DFLT_X;
			y = DFLT_Y;
		}
		super.changeIcon(index);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		if(showCrosshairs()) {
			GlStateManager.tryBlendFuncSeparate(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR, 1, 0);
			super.renderHUDItem(x, y);
			GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
		}
	}
	
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
	
	private boolean showCrosshairs() {
		if (mc.gameSettings.showDebugInfo && !mc.thePlayer.hasReducedDebug() && !mc.gameSettings.reducedDebugInfo) {
            return false;
        } else if (mc.playerController.isSpectator()) {
            if (mc.pointedEntity != null) {
                return true;
            } else {
                if (mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    BlockPos blockpos = this.mc.objectMouseOver.getBlockPos();

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
