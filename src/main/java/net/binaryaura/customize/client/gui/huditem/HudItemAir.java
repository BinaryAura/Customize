package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemAir extends HudItemIconGauge {

	public HudItemAir(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		return null;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return null;
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}

	@Override
	protected int getIconDeltaPara(int icon) {
		return 0;
	}

	@Override
	protected float getAmount() {
		return 0;
	}

	@Override
	protected void init() {
		
	}

}
