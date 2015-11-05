package net.binaryaura.customize.client.gui;

import net.minecraft.util.ResourceLocation;

public class Sprite {
	
	public class Coordinate {
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		private int x;
		private int y;
	}
	
	public Sprite(ResourceLocation location, int width, int height, Coordinate coord) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.coord = coord;
	}

	public Sprite(ResourceLocation location, int width, int height, int x, int y) {
		this.location = location;
		this.width = width;
		this.height = height;
		this.coord = new Coordinate(x, y);
	}
	
	public ResourceLocation getLocation() {
		return location;
	}
	
	public int getX() {
		return coord.x;
	}
	
	public int getY() {
		return coord.y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private Coordinate coord;
	private int width, height;
	private ResourceLocation location;
}
