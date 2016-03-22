package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.util.MathHelper;

public abstract class HudItemIconGauge extends HudItem {

	protected static final int DFLT_DEMO_AMT = 20;
	protected static final int DFLT_MAX_PER_ROW = 10;
	protected static final int DFLT_MAX_STK_SPC = 11;
	protected static final int DFLT_MIN_STK_SPC = 3;
	protected static final int DFLT_SPC = 8;
	
	//	TODO: Fix Button when Rotating with IconGauge Stacks
	//	TODO: Fix Border Collision with IconGauge
	public HudItemIconGauge(String name) {
		super(name);
		canFlip = true;
		canRotate = true;
		maxPerRow = DFLT_MAX_PER_ROW;
		maxStackSpace = DFLT_MAX_STK_SPC;
		minStackSpace = DFLT_MIN_STK_SPC;
		space = DFLT_SPC;
	}

	@Override
	public void renderHUDItem(int x, int y) {
		mc.mcProfiler.startSection(name);
		SpriteSet iconLayers;
		bind(layers.getLocation());
		if(amount != getAmount()) {
			amount = getAmount();
			setHeightAndWidth();
		}
			
		switch(orientation) {
			case RIGHT:
				for(int i = MathHelper.ceiling_float_int(amount / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i+1) / maxPerRow) - 1;
						int iconX = x + space*(i % maxPerRow) + getIconDeltaPara(i);
						int iconY = y - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case DOWN:
				space = layers.getHeight();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int iconY = y + space*(i % maxPerRow) + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case LEFT:
				x += width - layers.getWidth();
				space = layers.getWidth();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x - space*(i % maxPerRow) + getIconDeltaPara(i);
						int iconY = y + (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
			case UP:
				y += height - layers.getHeight();
				space = layers.getHeight();
				for(int i = MathHelper.ceiling_float_int(getAmount() / (layers.getAmount() - 1) - 1); i >= 0; --i) {
					iconLayers = getIconSpriteSet(i);
					for(int j = 0; j < iconLayers.getAmount(); j++) {
						int stack = MathHelper.ceiling_float_int((float)(i + 1) / maxPerRow) - 1;
						int iconX = x - (flip ? -1 : 1)*stack*stackSpace + getIconDeltaPerp(i);
						int iconY = y - space*(i % maxPerRow) + getIconDeltaPara(i);
						Sprite sprite = iconLayers.getSprite(j);
						if (sprite == null) continue;
						guiRenderer.drawTexturedModalRect(iconX, iconY, sprite.getX(), sprite.getY(), layers.getWidth(), layers.getHeight());
					}
				}
				break;
		}
		mc.mcProfiler.endSection();
	}
 
	public void setMaxPerRow(int max) {
		maxPerRow = max;
		width = (maxPerRow - 1)*space + layers.getWidth();
		int stacks = MathHelper.ceiling_float_int(getAmount() / ((layers.getAmount() - 1) / maxPerRow));
		int stackSpace = Math.max(maxStackSpace - (stacks - 1), minStackSpace);
		height = (stacks - 1)*stackSpace + layers.getHeight();
	}
	
	@Override
	public int getButtonX() {
		return super.getButtonX();
	}

	@Override
	public int getButtonY() {
		return super.getButtonY();
	}
	
	@Override
	protected void setHeightAndWidth() {
					stacks = MathHelper.ceiling_float_int(amount / (layers.getAmount() - 1) / maxPerRow);
					stackSpace = Math.max(maxStackSpace - (stacks - 1), minStackSpace);
		switch(orientation) {
			case RIGHT:
					height = (stacks - 1)*stackSpace + layers.getHeight();
					width = space*maxPerRow;
				break;
			case DOWN:
					height = space*maxPerRow;
					width = (stacks - 1)*stackSpace + layers.getWidth();
				break;
			case LEFT:
					height = (stacks - 1)*stackSpace + layers.getHeight();
					width = space*maxPerRow;
				break;
			case UP:
					height = space*maxPerRow;
					width = (stacks - 1)*stackSpace + layers.getWidth();
				break;
		}
	}

	protected int bidirectionalShake() {
		return rand.nextInt(3) - 1;
	}
	
	protected int movingHalfSinWave(int icon) {
		int leadIcon = updateCounter % MathHelper.ceiling_float_int(getAmount() + 5);
		if(leadIcon < icon && icon < leadIcon + 5) {
			return MathHelper.ceiling_double_int(5*Math.cos((icon - leadIcon)*5 / Math.PI));
		}
		return 0;
	}
	
	protected int shake() {
		return 2*rand.nextInt(2);
	}
	
	protected int sinWave(int icon) {
		int leadIcon = updateCounter % MathHelper.ceiling_float_int(getAmount());
		return MathHelper.ceiling_double_int(5*Math.sin(leadIcon*5 / Math.PI));
	}
	
	// TODO: Fix IconGauge Animation Methods
	protected int wave(int icon) {
		if(updateCounter % MathHelper.ceiling_float_int(getAmount() + 5) == icon) {
			animationFinished = false;
			if(MathHelper.ceiling_float_int(getAmount()) - 1 == icon)
				animationFinished = true;
			return 2;
		}
		return 0;
	}
	
	protected abstract int getIconDeltaPara(int icon);	
	protected abstract int getIconDeltaPerp(int icon);	
	protected abstract float getAmount();	
	protected abstract float getDemoAmount();	
	protected abstract SpriteSet getDemoSpriteSet();
	protected abstract SpriteSet getIconSpriteSet(int icon);
	
	@Deprecated
	protected boolean animationFinished = true;
	
	// Defaults Based on the default vitals bars
	protected int maxPerRow;
	protected int maxStackSpace;
	protected int minStackSpace;
	protected int space;
	
	protected LayeredSprite layers;

	private int stacks = 0;
	private int stackSpace = 0;
	private float amount = 0;
}
