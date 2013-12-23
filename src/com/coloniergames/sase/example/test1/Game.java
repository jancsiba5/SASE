package com.coloniergames.sase.example.test1;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.coloniergames.sase.GameBase;
import com.coloniergames.sase.gfx.Sprite;
import com.coloniergames.sase.gfx.SpriteBatcher;
import com.coloniergames.sase.gfx.SpriteSheet;
import com.coloniergames.sase.gfx.Texture;
import com.coloniergames.sase.util.TextureData;

public class Game extends GameBase {


	SpriteBatcher spriteBatcher;
	SpriteSheet sheet;
	List<Sprite> sprites;
	Texture t;
	public Game() {
		super(800, 600, false, "TEST 1", 800,
				600, -1, 1, Keyboard.KEY_ESCAPE);

	}

	public void preRender() {

		// t.bind();


	}

	public void renderGame() {

		spriteBatcher.begin();
		
		for(Sprite s : sprites) {
			spriteBatcher.draw(s);
		}
		// spriteBatcher.draw(t, 50, 50, 64, 64);

		spriteBatcher.end();
		
	}

	public void postRender() {

	}

	public void init() {

		sprites = new ArrayList<Sprite>();
		
		spriteBatcher = new SpriteBatcher(100);

		sheet = new SpriteSheet("res.textures.sheet", 16, 16);

		for(int i = 0; i < 1000; i++) {
			sprites.add(new Sprite(i, i % 50, sheet, 0, 0, 64, 64));
		}
		
		t = TextureData.get("res.textures.test");

	}


	public static void main(String[] args) {

		setPath();

		new Game();
	}

	public void handleKeys(int key) {

	}

	public void handleMouseMovement(float mX, float mY, float mDX, float mDY) {

	}

	public void handleMouseButton(int button) {

	}

	public void otherInputHandling() {

	}

}
