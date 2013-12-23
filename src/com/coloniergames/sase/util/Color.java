package com.coloniergames.sase.util;

public class Color {

	public float r, g, b, a;
	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1;
	}
	
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void set(float newR, float newG, float newB) {
		this.r = newR;
		this.g = newG;
		this.b = newB;
		
	}
	
	public void set(float newR, float newG, float newB, float newA) {
		this.r = newR;
		this.g = newG;
		this.b = newB;
		this.a = newA;
	}
}
