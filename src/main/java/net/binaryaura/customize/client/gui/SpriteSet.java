package net.binaryaura.customize.client.gui;

import java.util.ArrayList;

import net.binaryaura.customize.common.Customize;
import net.minecraft.util.ResourceLocation;

public class SpriteSet {

	public SpriteSet() {
		this(null, (Sprite[])null);
	}
	
	public SpriteSet(Sprite ... sprites) {
		this(null, sprites);
	}
	
	public SpriteSet(String name, Sprite ... sprites) {
		this.name = name;
		if(sprites == null) return;
		addSprites(sprites);
	}

	public void addSprite(Sprite sprite) {
		if(sprite == null) {
			this.sprites.add(sprite);
			amount++;
			return;
		}
		if(location == null) {
			height = sprite.getHeight();
			width = sprite.getWidth();
			location = sprite.getLocation();
		} else if(height != sprite.getHeight() || width != sprite.getWidth()) {
			Customize.log.error("Sprite didn't match dimentions. Skipping Sprite.");
			return;
		} else if(!location.equals(sprite.getLocation())) {
			Customize.log.error("Sprite isn't in the correct location: " + location + ". Sprite was in " + sprite.getLocation() + ". Skipping Sprite.");
			return;
		}
		this.sprites.add(sprite);
		amount++;
	}
	
	public void addSprites(Sprite ... sprites) {
		for(Sprite sprite : sprites) {
			addSprite(sprite);
		}
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getName() {
		return name;
	}
	
	public ResourceLocation getLocation() {
		return location;
	}
	
	public Sprite getSprite(int index) {
		return this.sprites.get(index);
	}
	
	public void newSprite(int x, int y) {
		if(amount == 0) {
			Customize.log.error("newSprite() must have a sprite already in the SpriteSet to get the new Sprites location and size. Skipping Sprite.");
			return;
		} else {
			addSprite(new Sprite(this.location, this.width, this.height, x, y));
		}
	}
	
	public void removeSprite(int index) {
		this.sprites.remove(index);
		amount--;
	}
	
	public void setSprite(int index, Sprite sprite) {
		this.sprites.set(index, sprite);
	}
	
	private int amount = 0;
	private int height;
	private int width;
	private ResourceLocation location;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private String name;
	
}
