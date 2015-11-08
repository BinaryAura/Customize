package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemArmor extends HudItemIconGauge {

	public HudItemArmor(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(ScaledResolution res, RenderGameOverlayEvent eventParent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getIconDeltaPara(int icon) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected float getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

}
