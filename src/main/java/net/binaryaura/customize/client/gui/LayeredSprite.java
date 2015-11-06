package net.binaryaura.customize.client.gui;

import java.util.Iterator;
import java.util.LinkedHashMap;

import net.binaryaura.customize.common.Customize;

public class LayeredSprite {

	public LayeredSprite(SpriteSet ... layers) {
		addLayers(layers);
	}
	
	public void addLayer(SpriteSet layer) {
		if(amount == 0) {
			amount = layer.getAmount();
			height = layer.getHeight();
			width = layer.getWidth();
		} else if(amount != layer.getAmount() || height != layer.getHeight() || width != getWidth()) {
			Customize.log.error("Layer didn't match dimentions. Skipping Layer");
			
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
	
	public Iterator getIterator() {
		return it;
		
	}
	
	private int height;
	private int width;
	private int amount;
	private LinkedHashMap<String, SpriteSet> sprites;
	private Iterator it;
	
}
