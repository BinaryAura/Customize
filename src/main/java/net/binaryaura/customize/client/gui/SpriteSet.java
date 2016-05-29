package net.binaryaura.customize.client.gui;

import java.util.ArrayList;

import net.binaryaura.customize.client.gui.Sprite.Coordinate;
import net.binaryaura.customize.common.Customize;
import net.minecraft.util.ResourceLocation;

/**
 * Collection of several Sprites. Sprites within a SpriteSet are
 * able to easily be swapped with each other on the screen. This
 * means that all the Sprites within the SpriteSet have the same
 * location, height and width. Attempting to add a Sprite with a
 * different location, width, or height will fail and the sprite
 * will not be added.
 * 
 * @author	BinaryAura
 *
 */
public class SpriteSet {

	/**
	 * Constructs an empty SpriteSet 
	 */
	public SpriteSet() {
		this(null, (Sprite[])null);
	}
	
	/**
	 * Constructs a SpriteSet from <code>sprites</code>.
	 * 
	 * @param sprites		Sprites to be added.
	 */
	public SpriteSet(Sprite ... sprites) {
		this(null, sprites);
	}
	
	/**
	 * Constructs a SpriteSet from <code>sprites</code>.
	 * 
	 * @param name			Reference name for the SpriteSet
	 * @param sprites		Sprites to be added.
	 */
	public SpriteSet(String name, Sprite ... sprites) {
		this.name = name;
		if(sprites == null) return;
		addSprites(sprites);
	}

	/**
	 * Adds a sprite to the SpriteSet.
	 * 
	 * @param sprite	Sprite to be added.
	 */
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
	
	/**
	 * Adds multiple sprites to the SpriteSet.
	 * 
	 * @param sprites		Sprites to be added.
	 */
	public void addSprites(Sprite ... sprites) {
		for(Sprite sprite : sprites) {
			addSprite(sprite);
		}
	}
	
	/**
	 * Adds multiple sprites to the SpriteSet.
	 * 
	 * @param sprites		Sprites to be added.
	 */
	public void addSprites(SpriteSet sprites) {
		for(int i = 0; i < sprites.getAmount(); i++) {
			addSprite(sprites.getSprite(i));
		}
	}
	
	/**
	 * Gets the amount of sprites within the set.
	 * 
	 * @return		Amount of sprites.
	 */
	public int getAmount() {
		return amount;
	}
	
	/**
	 * Gets the Height of the Sprites.
	 * 
	 * @return		Height of the Sprites in this SpriteSet.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the Width of the Sprites.
	 * 	
	 * @return		Width of the Sprites in this SpriteSet.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the Reference name of this SpriteSet.
	 * 
	 * @return		Name of the SpriteSet.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Image Location of all Sprites within this SpriteSet.
	 * 
	 * @return		Image File location.
	 */
	public ResourceLocation getLocation() {
		return location;
	}
	
	/**
	 * Get Sprite with the given index.
	 * 
	 * @param index		Index of the Sprite to be returned.
	 * @return			Sprite with the given index.
	 */
	public Sprite getSprite(int index) {
		return this.sprites.get(index);
	}
	
	/**
	 * Creates a new Sprite with the same location, and size and
	 * adds it to the Set.
	 * 
	 * @param x			X pixel location of the sprite in the image.
	 * @param y			Y pixel location of the sprite in the image.
	 */
	public void newSprite(int x, int y) {
		if(amount == 0) {
			Customize.log.error("newSprite() must have a sprite already in the SpriteSet to get the new Sprites location and size. Skipping Sprite.");
			return;
		} else {
			addSprite(new Sprite(this.location, this.width, this.height, x, y));
		}
	}
	
	/**
	 * Removes a sprite from the Set.
	 * 
	 * @param index		Index of the sprite to be removed.
	 */
	public void removeSprite(int index) {
		this.sprites.remove(index);
		amount--;
	}
	
	/**
	 * Changes the Sprite to the given sprite at the given index.
	 * 
	 * @param index		Index of the sprite to be replaced.
	 * @param sprite	Sprite to place in the given index.
	 */
	public void setSprite(int index, Sprite sprite) {
		this.sprites.set(index, sprite);
	}
	
	/**
	 * Amount of Sprites in the Set.
	 */
	private int amount = 0;
	
	/**
	 * Height of the Sprites in this set.
	 */
	private int height;
	
	/**
	 * Width of the Sprites in this set.
	 */
	private int width;
	
	/**
	 * File location of the Sprites.
	 */
	private ResourceLocation location;
	
	/**
	 * Collection of the Sprites in this Set.
	 */
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	/**
	 * Reference name for this Set.
	 */
	private String name;
	
}
