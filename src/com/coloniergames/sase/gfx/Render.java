package com.coloniergames.sase.gfx;

import java.util.ArrayList;
import java.util.List;

public class Render {

	private static SpriteBatcher MAIN_BATCHER = new SpriteBatcher();

	private static List<Sprite> sprites = new ArrayList<Sprite>();
	private static List<Sprite> spritesToRemove = new ArrayList<Sprite>();

	public static void addSprite(Sprite s) {
		if(!sprites.contains(s)) sprites.add(s);
	}

	public static void removeSprite(Sprite s) {
		if(sprites.contains(s)) sprites.remove(s);
	}

	public static void collectGarbage() {
		for(Sprite s : sprites) if(s.disposed) spritesToRemove.add(s);
		sprites.removeAll(spritesToRemove);
	}

	public static void drawSprites() {

		MAIN_BATCHER.begin();

		for(Sprite s : sprites) {
			MAIN_BATCHER.draw(s);
		}

		MAIN_BATCHER.end();
	}

	public static void init(int batcherSize) {
		Render.MAIN_BATCHER = new SpriteBatcher(batcherSize);
	}

	public static void drawSprite(Sprite s) {
		MAIN_BATCHER.draw(s);
	}

}
