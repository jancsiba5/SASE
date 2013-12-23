package com.coloniergames.sase.util;

public class Vector {

	public float x, y;
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float newX, float newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public void move(float vX, float vY) {
		this.x += vX;
		this.y += vY;
	}
}
