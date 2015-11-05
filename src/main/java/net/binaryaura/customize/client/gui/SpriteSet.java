package net.binaryaura.customize.client.gui;

import java.util.ArrayList;

public class SpriteSet {

	public SpriteSet(Sprite ... sprites) {
		addSprites(sprites);
	}

	public void addSprite(Sprite sprite) {
		this.sprites.add(sprite);
	}
	
	public void addSprites(Sprite ... sprites) {
		for(Sprite sprite : sprites) {
			addSprite(sprite);
		}
	}
	
	public void removeSprite(int index) {
		this.sprites.remove(index);
	}
	
	public void setSprite(int index, Sprite sprite) {
		this.sprites.set(index, sprite);
	}
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
}
