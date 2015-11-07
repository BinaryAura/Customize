package net.binaryaura.customize.client.gui.huditem;

import javax.swing.event.CellEditorListener;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemManager.HudItemType;
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
		SpriteSet iconLayers;
		bind(layers.getLocation());
		switch(orientation) {
			case RIGHT:
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					int stacks = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() / maxPerRow));
					int stackSpace = Math.max(maxStackSpace - (stacks - 2), minStackSpace);
					int stack = MathHelper.ceiling_float_int((float)(i + 1) / layers.getAmount()) - 1;
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						// Fix
						int x = (res.getScaledWidth() / 2 - this.x) + space*i + getIconDeltaPara(i);
						int y = (res.getScaledHeight() / 2 - this.y) - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(x, y, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case DOWN:
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					int stacks = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() / maxPerRow));
					int stackSpace = Math.max(maxStackSpace - (stacks - 2), minStackSpace);
					int stack = MathHelper.ceiling_float_int((float)(i + 1) / layers.getAmount()) - 1;
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						// Fix
						int x = (res.getScaledWidth() / 2 - this.x) + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int y = (res.getScaledHeight() / 2 - this.y) + space*i + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(x, y, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case LEFT:
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					int stacks = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() / maxPerRow));
					int stackSpace = Math.max(maxStackSpace - (stacks - 2), minStackSpace);
					int stack = MathHelper.ceiling_float_int((float)(i + 1) / layers.getAmount()) - 1;
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						// Fix
						int x = (res.getScaledWidth() / 2 - this.x) - space*i + getIconDeltaPara(i);
						int y = (res.getScaledHeight() / 2 - this.y) + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(x, y, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case UP:
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					int stacks = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() / maxPerRow));
					int stackSpace = Math.max(maxStackSpace - (stacks - 2), minStackSpace);
					int stack = MathHelper.ceiling_float_int((float)(i + 1) / layers.getAmount()) - 1;
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						// Fix
						int x = (res.getScaledWidth() / 2 - this.x) - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int y = (res.getScaledHeight() / 2 - this.y) - space*i + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(x, y, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
		}
	}
	
	protected abstract SpriteSet getIconSpriteSet(int icon);
	
	protected abstract SpriteSet getDefaultSpriteSet();
	
	protected abstract int getIconDeltaPerp(int icon);
	
	protected abstract int getIconDeltaPara(int icon);
	
	protected abstract float getAmount();
	
	protected int shake(int icon) {
		return this.rand.nextInt(2);
	}
	
	protected int wave(int icon) {
		if(updateCounter % MathHelper.ceiling_float_int(getAmount() + 5) == icon) {
			animationFinished = false;
			if(MathHelper.ceiling_float_int(getAmount()) - 1 == icon) animationFinished = true;
			return 2;
		}
		return 0;
	}
	
	protected int movingHalfSinWave(int icon) {
		int leadIcon = updateCounter % MathHelper.ceiling_float_int(getAmount() + 5);
		if(leadIcon < icon && icon < leadIcon + 5) {
			return MathHelper.ceiling_double_int(5*Math.sin((icon-leadIcon)*5 / Math.PI));
		}
		return 0;
	}
	
	protected int sinWave(int icon) {
		
	}

	protected boolean animationFinished = true;
	protected int maxStackSpace;
	protected int minStackSpace;
	protected int space;
	protected int maxPerRow = 10;
	protected LayeredSprite layers;
}
