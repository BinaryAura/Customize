package net.binaryaura.customize.client.gui.huditem;

import net.binaryaura.customize.client.gui.LayeredSprite;
import net.binaryaura.customize.client.gui.Sprite;
import net.binaryaura.customize.client.gui.SpriteSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HudItemHealth extends HudItemIconGauge {
	
	public HudItemHealth(String name) {
		super(name);
		orientation = Orientation.RIGHT;
		init();
	}

	@Override
	protected SpriteSet getIconSpriteSet(int icon) {
		// Which sprites and in what order.
		SpriteSet iconLayers = new SpriteSet();
		// Rules go here
		int health = MathHelper.ceiling_float_int(player.getHealth());
		float healthMax = player.getMaxHealth();
		float absorb = player.getAbsorptionAmount();
		
		String hc = mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? "HC" : "";
		boolean highlight = hlTime > (long)updateCounter && (hlTime - (long)updateCounter) / 3L % 2L == 1L;
		String hl = highlight ? "HL" : "";
		if(health < healthLastTick && player.hurtResistantTime > 0) {
			lastSystemTime = Minecraft.getSystemTime();
			hlTime = (long)updateCounter + 20;
		} else if(health > healthLastTick && player.hurtResistantTime > 0) {
			lastSystemTime = Minecraft.getSystemTime();
			hlTime = (long)updateCounter + 10;
		}
		if(Minecraft.getSystemTime() - lastSystemTime > 1000L) {
			lastSystemTime = Minecraft.getSystemTime();
		}
		healthLastTick = health;
		
		int index = 0;
		
		if((healthMax / 2) > icon) {
		// Is icon normal
			if(health / (layers.getAmount() - 1) > icon) {
			// Is icon less than health
				index = layers.getAmount() - 1;
			} else if(health / (layers.getAmount() - 1) == icon) {
				index = health % (layers.getAmount() - 1);
			}
		} else {
			if(MathHelper.ceiling_float_int(healthMax + absorb) / layers.getAmount() > icon) {
				index = layers.getAmount();
			} else if(MathHelper.ceiling_float_int(healthMax + absorb) / layers.getAmount() == icon) {
				index = MathHelper.ceiling_float_int(healthMax + absorb) % (layers.getAmount() - 1);
			}
		}
		
		iconLayers.addSprite(layers.getLayer("background").getSprite(highlight ? 1 : 0));
		if(player.isPotionActive(Potion.poison)) {
			iconLayers.addSprite(layers.getLayer("poison" + hc + hl).getSprite(index));
		} else if(player.isPotionActive(Potion.wither)) {
			iconLayers.addSprite(layers.getLayer("wither" + hc + hl).getSprite(index));
		} else if((healthMax / 2) <= icon) {
			iconLayers.addSprite(layers.getLayer("absorb" + hc + hl).getSprite(index));
		} else {
			iconLayers.addSprite(layers.getLayer("default" + hc + hl).getSprite(index));
		}
		
		return iconLayers;
	}
	
	@Override
	public void renderHUDItem(int x, int y) {
		player = (EntityPlayer)this.mc.getRenderViewEntity();
		render = this.mc.playerController.shouldDrawHUD() && player != null;
		super.renderHUDItem(x, y);
	}
	
	@Override
	protected SpriteSet getDefaultSpriteSet() {
		// Normal set of layers.
		return new SpriteSet(layers.getLayer("background").getSprite(0), layers.getLayer("default" + (mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? "HC" : "")).getSprite(2));
	}

	@Override
	protected int getIconDeltaPerp(int icon) {
		// Perpendicular Movement rules
		int perp = 0;
		if(player.isPotionActive(Potion.regeneration))
			perp += -movingHalfSinWave(icon);		
		else if(player.getHealth() <= 4)
			perp += shake(icon);
		return perp;
	}

	@Override
	protected int getIconDeltaPara(int icon) {
		// Parallel Movement rules
		return 0;
	}

	@Override
	protected float getAmount() {
		// Total amount of icons
		return player.getMaxHealth() + player.getAbsorptionAmount();
	}

	@Override
	protected void init() {
		maxStackSpace = 11;
		minStackSpace = 3;
		space = 8;
		anchor = Anchor.BOTTOM;
		x = -45;
		y = -39;
		layers = new LayeredSprite(new SpriteSet("background", new Sprite(Gui.icons, 9, 9, 16, 0), new Sprite(Gui.icons, 9, 9, 25, 0)));
		layers.addLayer(new SpriteSet("default", null, new Sprite(Gui.icons, 9, 9, 61, 0), new Sprite(Gui.icons, 9, 9, 52, 0)));
		layers.addLayer(new SpriteSet("defaultHL", null, new Sprite(Gui.icons, 9, 9, 79, 0), new Sprite(Gui.icons, 9, 9, 70, 0)));
		layers.addLayer(new SpriteSet("absorb", null, new Sprite(Gui.icons, 9, 9, 169, 0), new Sprite(Gui.icons, 9, 9, 160, 0)));
		layers.addLayer(new SpriteSet("poison", null, new Sprite(Gui.icons, 9, 9, 97, 0), new Sprite(Gui.icons, 9, 9, 88, 0)));
		layers.addLayer(new SpriteSet("poisonHL", null, new Sprite(Gui.icons, 9, 9, 115, 0), new Sprite(Gui.icons, 9, 9, 106, 0)));
		layers.addLayer(new SpriteSet("wither", null, new Sprite(Gui.icons, 9, 9, 133, 0), new Sprite(Gui.icons, 9, 9, 124, 0)));
		layers.addLayer(new SpriteSet("witherHL", null, new Sprite(Gui.icons, 9, 9, 151, 0), new Sprite(Gui.icons, 9, 9, 142, 0)));
		layers.addLayer(new SpriteSet("defaultHC", null, new Sprite(Gui.icons, 9, 9, 61, 45), new Sprite(Gui.icons, 9, 9, 52, 45)));
		layers.addLayer(new SpriteSet("defaultHCHL", null, new Sprite(Gui.icons, 9, 9, 79, 45), new Sprite(Gui.icons, 9, 9, 70, 45)));
		layers.addLayer(new SpriteSet("absorbHC", null, new Sprite(Gui.icons, 9, 9, 169, 45), new Sprite(Gui.icons, 9, 9, 160, 45)));
		layers.addLayer(new SpriteSet("poisonHC", null, new Sprite(Gui.icons, 9, 9, 97, 45), new Sprite(Gui.icons, 9, 9, 88, 45)));
		layers.addLayer(new SpriteSet("poisonHCHL", null, new Sprite(Gui.icons, 9, 9, 115, 45), new Sprite(Gui.icons, 9, 9, 106, 45)));
		layers.addLayer(new SpriteSet("witherHC", null, new Sprite(Gui.icons, 9, 9, 133, 45), new Sprite(Gui.icons, 9, 9, 124, 45)));
		layers.addLayer(new SpriteSet("witherHCHL", null, new Sprite(Gui.icons, 9, 9, 151, 45), new Sprite(Gui.icons, 9, 9, 142, 45)));
	}
	
	private int healthLastTick = 0;
	private long hlTime = 0;
	private EntityPlayer player;

}