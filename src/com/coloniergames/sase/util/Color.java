package com.coloniergames.sase.util;

public class Color {

	public static final Color RED = new Color(1, 0, 0);
	public static final Color DARK_RED = new Color(0.5f, 0, 0);
	public static final Color CRIMSON_RED = new Color(0.863f, 0.078f, 0.235f);
	public static final Color GREEN = new Color(0, 1, 0);
	public static final Color DARK_GREEN = new Color(0, 0.5f, 0);
	public static final Color LAWN_GREEN = new Color(0.486f, 0.988f, 0);
	public static final Color BLUE = new Color(0, 0, 1);
	public static final Color DARK_BLUE = new Color(0, 0, 0.5f);
	public static final Color MIDNIGHT_BLUE = new Color(0.098f, 0.098f, 0.439f);
	public static final Color ROYAL_BLUE = new Color(0.255f, 0.412f, 0.882f);
	public static final Color PURPLE = new Color(1, 0, 1);
	public static final Color DARK_PURPLE = new Color(0.5f, 0, 0.5f);
	public static final Color CYAN = new Color(0, 1, 1);
	public static final Color DARK_CYAN = new Color(0, 0.5f, 0.5f);
	public static final Color YELLOW = new Color(1, 1, 0);
	public static final Color DARK_YELLOW = new Color(0.5f, 0.5f, 0);
	public static final Color WHITE = new Color(1, 1, 1);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color SILVER = new Color(0.753f, 0.753f, 0.753f);
	public static final Color LIGHT_GRAY = new Color(0.827f, 0.827f, 0.827f);
	public static final Color DARK_GRAY = new Color(0.663f, 0.663f, 0.663f);
	public static final Color GRAY = new Color(0.5f, 0.5f, 0.5f);
	public static final Color DIM_GRAY = new Color(0.412f, 0.412f, 0.412f);
	public static final Color BROWN = new Color(0.647f, 0.165f, 0.165f);
	
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
