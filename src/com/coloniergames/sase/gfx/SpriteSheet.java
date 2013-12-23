package com.coloniergames.sase.gfx;

import com.coloniergames.sase.util.Color;
import com.coloniergames.sase.util.TextureData;

public class SpriteSheet {

	public Texture texture;
	public int rows, cols;
	public float cellWidth, cellHeight, u, v;
	public SpriteSheet(String location, int cellWidth, int cellHeight) {
		
		this.texture = TextureData.get(location);
		this.rows = texture.getWidth() / cellWidth;
		this.cols = texture.getHeight() / cellHeight;
		this.u = 1f / texture.getWidth() * cellWidth;
		this.v = 1f / texture.getHeight() * cellHeight;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		
		// System.out.println("NEW SPRITESHEET " + u + ", " + v + ", " + rows + ", " + cols + ", " + cellWidth + ", " + cellHeight);
		
	}
	
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y) {
		draw(batcher, row, col, x, y, texture.getWidth(), texture.getHeight(), 0, Color.WHITE);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, float rotation) {
		draw(batcher, row, col, x, y, texture.getWidth(), texture.getHeight(), rotation, Color.WHITE);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, float width, float height) {
		draw(batcher, row, col, x, y, width, height, 0, Color.WHITE);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, Color c) {
		draw(batcher, row, col, x, y, texture.getWidth(), texture.getHeight(), 0, c);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, float rotation, Color c) {
		draw(batcher, row, col, x, y, texture.getWidth(), texture.getHeight(), rotation, c);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, float width, float height, Color c) {
		draw(batcher, row, col, x, y, width, height, 0, c);
	}
	
	public void draw(SpriteBatcher batcher, int row, int col, float x, float y, float width, float height, float rotation, Color color) {
		
		batcher.draw(texture, new float[]{u * row, v * col , u * row + u, v * col, u * row + u, v * col + v, u * row, v * col + v}, x, y, width, height, rotation, color);
		
		// System.out.println("DRAWING { " + u * row + ", " + v * col + ", " + (u * row + u) + ", " + v * col);
		
	}
	
}
