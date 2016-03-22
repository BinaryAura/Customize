package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class HudItemAir extends HudItemIconGauge {

	private static final int DFLT_X = 45;
	private static final int DFLT_Y = -49;
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;
	private static final Orientation DFLT_ORIEN = Orientation.LEFT;
	
	public HudItemAir(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)mc.getRenderViewEntity();
		if(mc.playerController.shouldDrawHUD() && player != null && player.isInsideOfMaterial(Material.water) || isInPreview())
			super.renderHUDItem(x, y);
	}

	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		orientation = DFLT_ORIEN;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("air", null, new Sprite(Gui.icons, 25, 18, 9, 9), new Sprite(Gui.icons, 16, 18, 9, 9)));
	}

	@Override
	protected int getIconDeltaPara(int icon) {
		return 0;
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		return 0;
	}

	@Override
	protected float getAmount() {
		return getDemoAmount();
	}

	@Override
	protected float getDemoAmount() {
		return DFLT_DEMO_AMT;
	}

	@Override
	protected SpriteSet getDemoSpriteSet() {
		return new SpriteSet(layers.getLayer("air").getSprite(2));
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		if(!isInPreview()) {
			int air = player.getAir();
			int full = MathHelper.ceiling_double_int((air - 2) * 10.0 / 300.0);
			int partial = MathHelper.ceiling_double_int(air * 10.0 / 300.0) - full;
			
			int index = 0;
			if(icon < full)
				index = 2;
			else if(icon < full + partial)
				index = 1;
			iconLayers.addSprite(layers.getLayer("air").getSprite(index));
		} else
			iconLayers.addSprites(getDemoSpriteSet());
		return iconLayers;
	}

	EntityPlayer player;
}
