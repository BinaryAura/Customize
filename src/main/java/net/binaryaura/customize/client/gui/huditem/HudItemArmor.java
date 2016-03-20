package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;

public class HudItemArmor extends HudItemIconGauge {

	public HudItemArmor(String name) {
		super(name);
		orientation = Orientation.RIGHT;
		init();
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer) mc.getRenderViewEntity();
		if(this.mc.playerController.shouldDrawHUD() && player != null) {
			super.renderHUDItem(x, y);
		}
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		
		int level = ForgeHooks.getTotalArmorValue(player);
		int index = 0;
		if(level / (layers.getAmount() - 1) > icon)
			index = layers.getAmount() - 1;
		else if(level / (layers.getAmount() - 1) == icon)
			index = icon % (layers.getAmount() - 1);
		
		iconLayers.addSprite(layers.getLayer("armor").getSprite(index));
		return iconLayers;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return new SpriteSet(layers.getLayer("armor").getSprite(2));
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
		x = -91;
		y = -49;
		layers = new LayeredSprite(new SpriteSet("armor", new Sprite(Gui.icons, 16, 9, 9, 9), new Sprite(Gui.icons, 25, 9, 9, 9), new Sprite(Gui.icons, 34, 9, 9, 9)));
	}

	@Override
	protected float getDefaultAmount() {
		return 20;
	}

	EntityPlayer player;
}
