package net.binaryaura.customize.client.gui.huditem;

import org.lwjgl.opengl.GL11;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemBar extends HudItem {

	public HudItemBar(String name) {
		super(name);
		type = HudItemType.BAR;
	}
	
	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		SpriteSet sprites = getLayers();
		int x = 10;//res.getScaledWidth() / 2 + this.x;
		int y = 10;//res.getScaledHeight() / 2 + this.y;
		switch(orientation) {
			case RIGHT:
				break;
			case DOWN:
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslated(x + 5, y, 0.0F);
				break;
			case LEFT:
				GL11.glTranslatef(x, y, 0.0F);
				GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				break;
			case UP:
				GL11.glTranslatef(x + 5, y, 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				break;
		}
		bind(sprites.getLocation());
		for(int i = 0; i < layers.getAmount() + 1; i++) {
			int fill = MathHelper.ceiling_float_int(getAmount(i)*layers.getWidth());
			guiRenderer.drawTexturedModalRect(x, y, sprites.getSprite(i).getX(), sprites.getSprite(i).getY(), fill, layers.getHeight());
		}
	}
	
	protected abstract float getAmount(int layer);
	protected abstract SpriteSet getLayers();
	protected abstract SpriteSet getDefaultSpriteSet();
	
	protected LayeredSprite layers;
}
