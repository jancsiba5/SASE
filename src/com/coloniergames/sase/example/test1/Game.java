package com.coloniergames.sase.example.test1;

import static org.lwjgl.opengl.GL11.glClearColor;
import net.java.games.input.ControllerEnvironment;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;

import com.coloniergames.sase.GameBase;
import com.coloniergames.sase.gfx.SpriteSheet;
import com.coloniergames.sase.gfx.Texture;
import com.coloniergames.sase.util.TextureData;

public class Game extends GameBase {

	SpriteSheet sheet;
	Texture t;
	public Game() {
		super(800, 600, false, "TEST 1", 800,
				600, -1, 1, Keyboard.KEY_ESCAPE);

	}
	
	public void tick() {

		
	}

	public void preRender() {

	}

	public void renderGame() {

		
	}

	public void postRender() {

	}

	public void init() {

		sheet = new SpriteSheet("res.textures.sheet", 16, 16);
		
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

		while(Controllers.next()) {
			Controller source = Controllers.getEventSource();
			glClearColor((source.getAxisValue(2) + 1f) / 2f, (source.getAxisValue(3) + 1f) / 2f, (source.getAxisValue(1) + 1f) / 2f, (source.getAxisValue(0) + 1f) / 2f);
		}
		
	}

}
