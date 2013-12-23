package com.coloniergames.sase.util;

public class Vector2i {

	public int x, y;
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public void move(int vX, int vY) {
		this.x += vX;
		this.y += vY;
	}
}
