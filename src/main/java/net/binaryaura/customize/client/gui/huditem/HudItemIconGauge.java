package net.binaryaura.customize.client.gui.huditem;

import java.util.LinkedHashSet;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
import net.binaryaura.customize.common.Customize;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class HudItemIconGauge extends HudItem {

	public HudItemIconGauge(String name) {
		super(name);
		type = HudItemType.ICON_GUAGE;
	}

	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		Sprite sprite;
		for(int i = MathHelper.ceiling_float_int(getAmount()/layers.getAmount()); i >= 0; --i) {
			// Which row
			SpriteSet spriteSet = getIconSprite(i);
			// Choose Sprite (i % Amount in set)
			switch(orientation) {
				case RIGHT:
					sprite = spriteSet.getSprite(i);
					for(int j = 0; j < spriteSet.amount; j++) {						
						guiRenderer.drawTexturedModalRect(x + space*i + getIconDeltaPerp(i), y + getIconDeltaPara(i), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
					}
					break;
				case DOWN:
					sprite = spriteSet.getSprite(i);
					for(int j = 0; j < spriteSet.amount; j++) {						
						guiRenderer.drawTexturedModalRect(x + getIconDeltaPerp(i), y + space*i + getIconDeltaPara(i), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
					}
					break;
				case LEFT:
					sprite = spriteSet.getSprite(i);
					for(int j = 0; j < spriteSet.amount; j++) {						
						guiRenderer.drawTexturedModalRect(x - space*i + getIconDeltaPerp(i), y + getIconDeltaPara(i), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
					}
					break;
				case UP:
					sprite = spriteSet.getSprite(i);
					for(int j = 0; j < spriteSet.amount; j++) {						
						guiRenderer.drawTexturedModalRect(x + getIconDeltaPerp(i), y - space*i + getIconDeltaPara(i), sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
					}
					break;
			}
		}
	}
	
	protected abstract SpriteSet getIconSprite(int icon);
	
	protected abstract int getIconDeltaPerp(int icon);
	
	protected abstract int getIconDeltaPara(int icon);
	
	protected abstract float getAmount();
	
	@Override
	public void renderLayer(int layer) {
		for(int i = 0; i < 10; i++) {
			switch(orientation) {
				case RIGHT:
					
			}
		}
	}

	protected int space;
	protected int maxPerRow = 10;
	protected LayeredSprite layers;
}
