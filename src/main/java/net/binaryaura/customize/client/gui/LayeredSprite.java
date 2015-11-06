package net.binaryaura.customize.client.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import net.binaryaura.customize.client.gui.Sprite.Coordinate;
import net.binaryaura.customize.common.Customize;
import net.minecraft.util.ResourceLocation;

public class LayeredSprite {

	public LayeredSprite(SpriteSet ... layers) {
		addLayers(layers);
	}
	
	public void addLayer(SpriteSet layer) {
		if(amount == 0) {
			amount = layer.getAmount();
			height = layer.getHeight();
			width = layer.getWidth()
		}
		sprites.put(layer.getName(), layer);
		it = sprites.values().iterator();
	}
	
	public void addLayers(SpriteSet[] layers) {
		for(SpriteSet layer : layers) {
			addLayer(layer);
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
	
	public SpriteSet getNext() {
		return it.hasNext() ? (SpriteSet) it.next() : null;
		
	}
	
	private int height;
	private int width;
	private int amount;
	private LinkedHashMap<String, SpriteSet> sprites;
	private Iterator it;
	
}
