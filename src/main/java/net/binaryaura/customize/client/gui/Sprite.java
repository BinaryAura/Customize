package net.binaryaura.customize.client.gui;

import net.minecraft.util.ResourceLocation;

/**
 * Representation of texture. Used for the rendering of
 * objects.
 * 
 * @author	BinaryAura
 *
 */
public class Sprite {
	
	/**
	 * The x,y coordinate of the image where the sprite can
	 * be found.
	 * 
	 * @author	BinaryAura
	 *
	 */
	public class Coordinate {
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * X-value of the image where the sprite can be found.
		 */
		private int x;
		
		/**
		 * Y-value of the image where the sprite can be found.
		 */
		private int y;
	}
	
	/**
	 * Constructs an instance of a Sprite using a Coordinate.
	 * 
	 * @see 	Coordinate
	 * 
	 * @param location			Directory location of the image file.
	 * @param coord				Pixel location of the sprite.
	 * @param width				Width of the sprite.
	 * @param height			Height of the sprite.
	 */
	public Sprite(ResourceLocation location, Coordinate coord, int width, int height) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.coord = coord;
	}

	/**
	 * Constructs an instance of a Sprite using x and y values.
	 * 
	 * @param location			Directory location of the image file.
	 * @param x					X pixel location of the sprite.
	 * @param y					Y pixel location of the sprite.
	 * @param width				Width of the sprite.
	 * @param height			Height of the sprite.
	 */
	public Sprite(ResourceLocation location, int x, int y, int width, int height) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.coord = new Coordinate(x, y);
	}
	
	/**
	 * Gets the Directory location of the image file.
	 * 
	 * @return		Image file location.
	 */
	public ResourceLocation getLocation() {
		return location;
	}
	
	/**
	 * Gets the X pixel location.
	 * 
	 * @return 		Sprite image x-value.
	 */
	public int getX() {
		return coord.x;
	}
	
	/**
	 * Gets the Y pixel location.
	 *
	 * @return		Sprite image y-value.
	 */
	public int getY() {
		return coord.y;
	}
	
	/**
	 * Gets the sprite width.
	 * 
	 * @return		Sprite width in pixels.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the sprite height.
	 * 
	 * @return		Sprite height in pixels.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Location of the sprite in the image.
	 */
	private Coordinate coord;
	
	/**
	 * Width of the sprite in pixels.
	 */
	private int width;
	
	/**
	 * Height of the sprite in pixels.
	 */
	private int height;
	
	/**
	 * File location of the image.
	 */
	private ResourceLocation location;
}
