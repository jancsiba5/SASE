package com.coloniergames.sase.gfx;

import com.coloniergames.sase.util.Color;
import com.coloniergames.sase.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {

	public Vector position;
	private Color color;
	private float width, height, rotation;
	public SpriteSheet owner;
	public int row, col;
	
	public Sprite(float x, float y, SpriteSheet s, int row, int col) {
		
		this.position = new Vector(x, y);
		this.rotation = 0;
		
		this.width = s.cellWidth;
		this.height = s.cellHeight;
		this.color = new Color(1, 1, 1, 1);
		this.owner = s;
		this.row = row;
		this.col = col;
		
	}
	
	public Sprite(float x, float y, SpriteSheet s, int row, int col, float width, float height) {
		
		this.position = new Vector(x, y);
		this.rotation = 0;
		
		this.width = width;
		this.height = height;
		this.color = new Color(1, 1, 1, 1);
		this.owner = s;
		this.row = row;
		this.col = col;
		
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void immidiateDraw() {
		
		owner.texture.bind();
		
		glBegin(GL_QUADS);
		
		glTexCoord2f(owner.u * row, owner.v * col);
		glVertex2f(position.x, position.y);
		
		glTexCoord2f(owner.u * row + owner.u, owner.v * col);
		glVertex2f(position.x + getWidth(), position.y);
		
		glTexCoord2f(owner.u * row + owner.u, owner.v * col + owner.v);
		glVertex2f(position.x + getWidth(), position.y + getHeight());
		
		glTexCoord2f(owner.u * row, owner.v * col + owner.v);
		glVertex2f(position.x, position.y + getHeight());
		
		glEnd();
		
	}
	
	public void draw(SpriteBatcher batcher) {
		
		owner.draw(batcher, row, col, position.x, position.y, getWidth(), getHeight(), getRotation(), color);
		
	}
	
}
