package com.coloniergames.sase.gfx;

import java.awt.image.BufferedImage;

import com.coloniergames.sase.util.TextureLoader;

import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class Texture {

	public int textureID;
	private BufferedImage textureImage;
	private int width, height;
	String name;
	public static final Texture emptyTexture = new Texture();
	
	public Texture(BufferedImage image) {
		
		textureID = TextureLoader.loadTexture(image);
		this.textureImage = image;
		this.name = "LOADED FROM IMAGE NO NAME";
		this.setWidth(textureImage.getWidth());
		this.setHeight(textureImage.getHeight());
		
	}
	/*
	public Texture(BufferedImage image, int row, int col, int cellWidth, int cellHeight) {
		
		textureID = TextureLoader.loadTexture(image, row, col, cellWidth, cellHeight);
		this.textureImage = image;
		this.name = "From Sprite Sheet " + row + "; " + col;
		this.setWidth(cellWidth);
		this.setHeight(cellHeight);
		
	}
	*/
	
	public Texture(BufferedImage image, int width, int height) {
		
		textureID = TextureLoader.loadTexture(image);
		this.textureImage = image;
		this.name = "LOADED FROM IMAGE NO NAME";
		this.setWidth(width);
		this.setHeight(height);
		
	}
	
	public Texture(String location) {
		
		this.textureID = TextureLoader.loadTexture(location);
		this.textureImage = TextureLoader.loadImage(location);
		this.name = location;
		this.setWidth(textureImage.getWidth());
		this.setHeight(textureImage.getHeight());
		
	}
	
	public Texture(String location, int width, int height) {
		
		this.textureID = TextureLoader.loadTexture(location);
		this.textureImage = TextureLoader.loadImage(location);
		this.name = location;
		this.setWidth(width);
		this.setHeight(height);
		
	}
	
	private Texture() {
		
		this.textureID = 0;
		this.textureImage = null;
		this.name = "EMPTY";
		this.setWidth(1);
		this.setHeight(1);
		
	}
	
	public void bind() {
		
		glBindTexture(GL_TEXTURE_2D, textureID);
		
	}
	
	public void resize(int newW, int newH) {
		
		this.setWidth(newW);
		this.setHeight(newH);
	}
	
	public void reload(String location) {
		
		this.textureID = TextureLoader.loadTexture(location);
		this.textureImage = TextureLoader.loadImage(location);
		resize(textureImage.getWidth(), textureImage.getHeight());
		
	}
	
	public void reload(BufferedImage image) {
		
		this.textureID = TextureLoader.loadTexture(image);
		this.textureImage = image;
		resize(textureImage.getWidth(), textureImage.getHeight());
		
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public String toString() {
		return name;
	}
	
}
