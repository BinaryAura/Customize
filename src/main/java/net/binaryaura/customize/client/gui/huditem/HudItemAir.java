package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class HudItemAir extends HudItemIconGauge {

	public HudItemAir(String name) {
		super(name);
		orientation = Orientation.RIGHT;
		init();
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)mc.getRenderViewEntity();
		if(mc.playerController.shouldDrawHUD() && player != null && player.isInsideOfMaterial(Material.water))
			super.renderHUDItem(x, y);
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		int air = player.getAir();
		int full = MathHelper.ceiling_double_int((air - 2) * 10.0 / 300.0);
		int partial = MathHelper.ceiling_double_int(air * 10.0 / 300.0) - full;
		
		int index = 0;
		if(icon < full)
			index = 2;
		else if(icon < full + partial)
			index = 1;
		iconLayers.addSprite(layers.getLayer("air").getSprite(index));
		return iconLayers;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return new SpriteSet(layers.getLayer("air").getSprite(2));
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
		return getDefaultAmount();
	}

	@Override
	protected void init() {
		anchor = Anchor.BOTTOM;
		x = 91;
		y = -49;
		layers = new LayeredSprite(new SpriteSet("air", null, new Sprite(Gui.icons, 25, 18, 9, 9), new Sprite(Gui.icons, 16, 18, 9, 9)));
	}

	@Override
	protected float getDefaultAmount() {
		return 20;
	}

	EntityPlayer player;
}
