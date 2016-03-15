package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemBoss extends HudItemBar {

	public HudItemBoss(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		
	}

	@Override
	protected void init() {
		
	}

	@Override
	protected float getAmount(int layer) {
		return 0;
	}

	@Override
	protected SpriteSet getLayers() {
		return null;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return null;
	}

}
