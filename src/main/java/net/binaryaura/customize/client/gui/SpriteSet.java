package net.binaryaura.customize.client.gui;

import java.util.ArrayList;

import net.binaryaura.customize.common.Customize;

public class SpriteSet {

	public SpriteSet(String name, Sprite ... sprites) {
		setName(name);
		addSprites(sprites);
	}

	public void addSprite(Sprite sprite) {
		if(amount == 0) {
			height = sprite.getHeight();
			width = sprite.getWidth();
		} else if(height != sprite.getHeight() || width != sprite.getWidth()) {
			Customize.log.error("Sprite didn't match dimentions. Skipping Sprite");
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
	
	public Sprite getSprite(int index) {
		return this.sprites.get(index);
	}
	
	public void removeSprite(int index) {
		this.sprites.remove(index);
		amount--;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSprite(int index, Sprite sprite) {
		this.sprites.set(index, sprite);
	}
	
	private int amount = 0;
	private int height = 0;
	private int width = 0;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private String name;
	
}
