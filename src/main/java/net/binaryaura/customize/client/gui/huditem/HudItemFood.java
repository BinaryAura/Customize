package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;

public class HudItemFood extends HudItemIconGauge {

	public HudItemFood(String name) {
		super(name);
		orientation = Orientation.RIGHT;
		init();
	}

	//	TODO: Fix Incorrect Sprite Order when Hunger is Active
	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		SpriteSet iconLayers = new SpriteSet();
		int level = stats.getFoodLevel();
		int levelLast = stats.getPrevFoodLevel();
		boolean prevFood = false;
		
		int index = 0;
		int indexPrev = 0;
		
		if(prevFood) {
			if(levelLast / (layers.getAmount() - 1) > icon)
				indexPrev = layers.getAmount() - 1;
			else if(levelLast / (layers.getAmount() - 1) == icon)
				indexPrev = level % (layers.getAmount() - 1);
		}
		
		if(level / (layers.getAmount() - 1) > icon)
			index = layers.getAmount() - 1;
		else if(level / (layers.getAmount() - 1) == icon)
			index = level % (layers.getAmount() - 1);
		
		iconLayers.addSprite(layers.getLayer("background").getSprite(0));
		if(player.isPotionActive(Potion.hunger)) {
			if(prevFood && indexPrev > index)
				iconLayers.addSprite(layers.getLayer("hungerPrev").getSprite(indexPrev));
			iconLayers.addSprite(layers.getLayer("hunger").getSprite(index));
		} else {
			if(prevFood && indexPrev > index)
				iconLayers.addSprite(layers.getLayer("defaultPrev").getSprite(indexPrev));
			iconLayers.addSprite(layers.getLayer("default").getSprite(index));
		}
		
		return iconLayers;
	}

	@Override
	protected SpriteSet getDefaultSpriteSet() {
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("default").getSprite(2));
	}
	
	protected float getDefaultAmount() {
		return 20;
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		int perp = 0;
		if(stats.getSaturationLevel() <= 0.0F && updateCounter % (stats.getFoodLevel() * 3 + 1) == 0)
			perp = bidirectionalShake();
		return perp;
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
		y = -39;
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 16, 27, 9, 9), new Sprite(Gui.icons, 25, 27, 9, 9)));
		layers.addLayer(new SpriteSet("default", null, new Sprite(Gui.icons, 61, 27, 9, 9), new Sprite(Gui.icons, 52, 27, 9, 9)));
		layers.addLayer(new SpriteSet("defaultPrev", null, new Sprite(Gui.icons, 79, 27, 9, 9), new Sprite(Gui.icons, 52, 27, 9, 9)));
		layers.addLayer(new SpriteSet("hunger", new Sprite(Gui.icons, 133, 27, 9, 9), new Sprite(Gui.icons, 97, 27, 9, 9), new Sprite(Gui.icons, 106, 27, 9, 9)));
		layers.addLayer(new SpriteSet("hungerPrev", null, new Sprite(Gui.icons, 115, 27, 9, 9), new Sprite(Gui.icons, 106, 27, 9, 9)));		
	}

	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		stats = player.getFoodStats();
		if(this.mc.playerController.shouldDrawHUD() && player != null)
			super.renderHUDItem(x, y);
	}

	private FoodStats stats;
	private EntityPlayer player;
}
