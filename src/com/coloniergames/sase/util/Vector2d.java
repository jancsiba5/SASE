package com.coloniergames.sase.util;

public class Vector2d {

	public double x, y;
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setPosition(double newX, double newY) {
		this.x = newX;
		this.y = newY;
	}
	
	public void move(double vX, double vY) {
		this.x += vX;
		this.y += vY;
	}
	
}
