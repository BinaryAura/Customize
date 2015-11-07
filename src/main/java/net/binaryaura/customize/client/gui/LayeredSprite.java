package net.binaryaura.customize.client.gui;

import java.util.Iterator;
import java.util.LinkedHashMap;

import net.binaryaura.customize.common.Customize;
import net.minecraft.util.ResourceLocation;

public class LayeredSprite {

	public LayeredSprite(SpriteSet ... layers) {
		addLayers(layers);
	}
	
	public void addLayer(SpriteSet layer) {
		if(amount == 0) {
			amount  = layer.getName().equalsIgnoreCase("background") || layer.getName().equals("bg") ? amount : layer.getAmount();
			height = layer.getHeight();
			width = layer.getWidth();
			location = layer.getLocation();
		} else if(amount != layer.getAmount() || height != layer.getHeight() || width != getWidth()) {
			Customize.log.error("Layer didn't match dimentions. Skipping Layer.");
			return;
		} else if(location.equals(layer.getLocation())) {
			Customize.log.error("Layer isn't in the correct location: " + location + ". Layer was in " + layer.getLocation() + ". Spipping Layer.");
			return;
		} else if(layers.containsKey(layer.getName())) Customize.log.error("Layer " + layer.getName() + " already exists. Overwriting Layer.");
		layers.put(layer.getName(), layer);
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
	
	public ResourceLocation getLocation() {
		return location;
	}
	
	public SpriteSet getLayer(String name) {
		return layers.get(name);
	}
	
	public int getWidth() {
		return width;
	}
	
	public void removeLayer(String name) {
		layers.remove(name);
	}
	
	public Iterator getIterator() {
		return layers.values().iterator();
		
	}
	
	private int height;
	private int width;
	private int amount;
	private ResourceLocation location;
	private LinkedHashMap<String, SpriteSet> layers;
	private Iterator it;
	
}
