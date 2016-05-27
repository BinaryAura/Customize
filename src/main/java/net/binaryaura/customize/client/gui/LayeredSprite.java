package net.binaryaura.customize.client.gui;

import java.util.Iterator;
import java.util.LinkedHashMap;

import net.binaryaura.customize.common.Customize;
import net.minecraft.util.ResourceLocation;

/**
 * Layered Sprite is a Collection of SpriteSets. All SpriteSets
 * within the Layered Sprite have the same location, width, height
 * and amount. The only exception to this is if the first layer's name
 * is "background" or "bg." The Background layer must be added first to
 * the Layered Sprite. All subsequent layers will have identical diminsions.
 * 
 * @author	BinaryAura
 * @see 	SpriteSet
 *
 */
public class LayeredSprite {

	/**
	 * Constructs a LayeredSprite from <code>layers</code>.
	 * 
	 * @param layers		Layers to be added.
	 */
	public LayeredSprite(SpriteSet ... layers) {
		this.layers = new LinkedHashMap<String, SpriteSet>();
		addLayers(layers);
	}
	
	/**
	 * Adds a SpriteSet layer to the LayeredSprite.
	 * 
	 * @param layer		SpriteSet to be added as a layer.
	 */
	public void addLayer(SpriteSet layer) {
		if(layer == null) {
			layers.put(null, layer);
			return;
		}
		if(amount == 0) {
			amount  = layer.getName().equalsIgnoreCase("background") || layer.getName().equals("bg") ? amount : layer.getAmount();
			height = layer.getHeight();
			width = layer.getWidth();
			location = layer.getLocation();
		} else if(amount != layer.getAmount() || height != layer.getHeight() || width != getWidth()) {
			Customize.log.error("Layer didn't match dimentions. Skipping Layer.");
			return;
		} else if(!location.equals(layer.getLocation())) {
			Customize.log.error("Layer isn't in the correct location: " + location + ". Layer was in " + layer.getLocation() + ". Spipping Layer.");
			return;
		} else if(layers.containsKey(layer.getName())) Customize.log.error("Layer " + layer.getName() + " already exists. Overwriting Layer.");
		layers.put(layer.getName(), layer);
		Customize.log.info("Added layer: " + layer.getName());
	}
	
	/**
	 * Adds multiple SpriteSet layers to the LayeredSprite.
	 * 
	 * @param layers		SpriteSets to be added as layers.
	 */
	public void addLayers(SpriteSet[] layers) {
		for(SpriteSet layer : layers) {
			addLayer(layer);
		}
	}
	
	/**
	 * Gets the amount of Sprites in each SpriteSet.
	 * 
	 * @return		Amount of Sprites in each SpriteSet.
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Gets the height of all Sprites in the SpriteSets.
	 * 
	 * @return		Height of all Sprites in the SpriteSets.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the File location of Sprites.
	 * 
	 * @return		File location of Sprites.
	 */
	public ResourceLocation getLocation() {
		return location;
	}
	
	/**
	 * Gets the layers with the given name.
	 * 
	 * @param name		Reference name of the layer to be returned.
	 * @return			Layer with the given name.
	 */
	public SpriteSet getLayer(String name) {
		return layers.get(name);
	}
	
	/**
	 * Gets the width of all Sprites in the SpriteSets.
	 * 
	 * @return		Width of all Sprites in the SpriteSets.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Removes the layer with the given name from this
	 * LayeredSprite.
	 * 
	 * @param name		Name of the Layer to be removed.
	 */
	public void removeLayer(String name) {
		layers.remove(name);
	}
	
	/**
	 * Gets an iterator for the layers. This is so the LayeredSprite
	 * can be used as a collection (i.e. with for(x:y), and so forth)
	 * 
	 * @return		An Iterable collection of this LayeredSprite's layers.
	 */
	public Iterator<SpriteSet> getIterator() {
		return layers.values().iterator();
		
	}
	
	/**
	 * Height of all Sprites.
	 */
	private int height;
	
	/**
	 * Width of all Sprites.
	 */
	private int width;
	
	/**
	 * Amount of Sprites in all layers except the background layer.
	 */
	private int amount;
	
	/**
	 * File location of the sprites.
	 */
	private ResourceLocation location;
	
	/**
	 * Collection of all layers.
	 */
	private LinkedHashMap<String, SpriteSet> layers;
	
}
