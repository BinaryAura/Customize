package net.binaryaura.customize.client.gui.huditem.defaults;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.binaryaura.customize.client.gui.huditem.HudItemIconGauge;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;

public class HudItemArmor extends HudItemIconGauge {
	
	private static final int DFLT_X = -45;
	private static final int DFLT_Y = -49;
	private static final Anchor DFLT_ANCH = Anchor.BOTTOM;

	public HudItemArmor(String name) {
		super(name);
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer) mc.getRenderViewEntity();
		if(this.mc.playerController.shouldDrawHUD() && player != null) {
			super.renderHUDItem(x, y);
		}
	}

	@Override
	protected void init() {
		anchor = DFLT_ANCH;
		x = DFLT_X;
		y = DFLT_Y;
		layers = new LayeredSprite(new SpriteSet("armor", new Sprite(Gui.icons, 16, 9, 9, 9), new Sprite(Gui.icons, 25, 9, 9, 9), new Sprite(Gui.icons, 34, 9, 9, 9)));
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
		return new SpriteSet(layers.getLayer("armor").getSprite(2));
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		
		if(!isInPreview()) {
			int level = ForgeHooks.getTotalArmorValue(player);
			int index = 0;
			if(level / (layers.getAmount() - 1) > icon)
				index = layers.getAmount() - 1;
			else if(level / (layers.getAmount() - 1) == icon)
				index = icon % (layers.getAmount() - 1);
		
			iconLayers.addSprite(layers.getLayer("armor").getSprite(index));
		} else
			iconLayers.addSprites(getDemoSpriteSet());
		return iconLayers;
	}

	EntityPlayer player;
}
