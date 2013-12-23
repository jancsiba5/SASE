package com.coloniergames.sase.gfx;

public class Render {

	private static SpriteBatcher MAIN_BATCHER;
	
	public static void init(int batcherSize) {
		Render.MAIN_BATCHER = new SpriteBatcher(batcherSize);
	}
	
	public static void drawSprite(Sprite s) {
		MAIN_BATCHER.draw(s);
	}
	
}
