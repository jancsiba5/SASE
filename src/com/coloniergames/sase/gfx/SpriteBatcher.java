package com.coloniergames.sase.gfx;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.coloniergames.sase.util.Color;
import com.coloniergames.sase.util.TextureData;

import static org.lwjgl.opengl.GL11.*;

public class SpriteBatcher {

	private static float[] empty = new float[8];

	private float[] vertArray;
	private byte[] colorArray;
	private float[] texArray;

	private int draws, maxDraws = 1000, vertIndex, colIndex, texIndex;
	Texture currentTex;
	private FloatBuffer vertBuff, texBuff;
	private ByteBuffer colBuff;

	static {
		empty[0] = 0;
		empty[1] = 0;
		empty[2] = 1;
		empty[3] = 0;
		empty[4] = 1;
		empty[5] = 1;
		empty[6] = 0;
		empty[7] = 1;
	}

	public SpriteBatcher() {
		this(1000);
	}

	public SpriteBatcher(int size) {

		vertArray = new float[size * 2 * 4];
		vertBuff = BufferUtils.createFloatBuffer(vertArray.length);

		colorArray = new byte[size * 4 * 4];
		colBuff = BufferUtils.createByteBuffer(colorArray.length);

		texArray = new float[size * 2 * 4];
		texBuff = BufferUtils.createFloatBuffer(vertArray.length);

		vertIndex = 0;
		colIndex = 0;
		texIndex = 0;
		maxDraws = size;
		draws = 0;

		currentTex = TextureData.get("res.textures.test");
		
	}

	public void begin() {

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

	}

	public void end() {

		render();

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);

	}

	public void render() {

		currentTex.bind();
		vertBuff.put(vertArray);
		vertBuff.flip();
		colBuff.put(colorArray);
		colBuff.flip();
		texBuff.put(texArray);
		texBuff.flip();
		glVertexPointer(2, 0, vertBuff);
		glColorPointer(4, true, 0, colBuff);
		glTexCoordPointer(2, 0, texBuff);
		glDrawArrays(GL_QUADS, 0, draws * 4);
		vertBuff.clear();
		colBuff.clear();
		texBuff.clear();
		vertIndex = 0;
		colIndex = 0;
		texIndex = 0;
		draws = 0;

	}
	
	public void draw(Sprite s) {
		
		// s.owner.draw(this, s.row, s.col, s.position.x, s.position.y, s.getWidth(), s.getHeight(), s.getRotation(), s.getColor());
		
		// draw(s.owner.texture, new float[]{s.owner.u * s.row, s.owner.v * s.col , s.owner.u * s.row + s.owner.u, s.owner.v * s.col, s.owner.u * s.row + s.owner.u, s.owner.v * s.col + s.owner.v, s.owner.u * s.row, s.owner.v * s.col + s.owner.v}, s.position.x, 
		//		s.position.y, s.getWidth(), s.getHeight(), s.getRotation(), s.getColor());
		
		s.draw(this);
		
	}
	
	public void draw(Texture t, float x, float y) {
		draw(t, x, y, t.getWidth(), t.getHeight());
	}
	
	public void draw(Texture t, float x, float y, float rotation) {
		draw(t, x, y, t.getWidth(), t.getHeight(), rotation);
	}
	
	public void draw(Texture t, float x, float y, float width, float height) {
		draw(t, x, y, width, height, new Color(1, 1, 1));
	}
	
	public void draw(Texture t, float x, float y, float width, float height, float rotation) {
		draw(t, new float[]{0, 0, 1, 0, 1, 1, 0, 1}, x, y, width, height, rotation, new Color(1, 1, 1, 1));
	}
	
	public void draw(Texture t, float x, float y, float width, float height, float rotation, Color c) {
		draw(t, new float[]{0, 0, 1, 0, 1, 1, 0, 1}, x, y, width, height, rotation, c);
	}
	
	public void draw(Texture t, float x, float y, float width, float height, Color c) {
		draw(t, new float[]{0, 0, 1, 0, 1, 1, 0, 1}, x, y, width, height, 0, c);
	}

	public void draw(Texture t, float[] region, float x, float y, float width, float height, float rotation, Color c) {

		if(t != currentTex) {
			render();
			currentTex = t;
		}

		if(draws == maxDraws) {
			render();
		}

		final float p1x = -width / 2;
		final float p1y = -height / 2;
		final float p2x = width / 2;
		final float p2y = -height / 2;
		final float p3x = width / 2;
		final float p3y = height / 2;
		final float p4x = -width / 2;
		final float p4y = height / 2;

		float x1, y1, x2, y2, x3, y3, x4, y4;

		if(rotation != 0) {
			final float cos = (float) Math.cos(Math.toRadians(rotation));
			final float sin = (float) Math.sin(Math.toRadians(rotation));

			x1 = cos * p1x - sin * p1y;
			y1 = sin * p1x + cos * p1y;

			x2 = cos * p2x - sin * p2y;
			y2 = sin * p2x + cos * p2y;

			x3 = cos * p3x - sin * p3y;
			y3 = sin * p3x + cos * p3y;

			x4 = cos * p4x - sin * p4y;
			y4 = sin * p4x + cos * p4y;
		} else {
			x1 = p1x;
			y1 = p1y;

			x2 = p2x;
			y2 = p2y;

			x3 = p3x;
			y3 = p3y;

			x4 = p4x;
			y4 = p4y;
		}

		x1 += x;
		x2 += x;
		x3 += x;
		x4 += x;
		y1 += y;
		y2 += y;
		y3 += y;
		y4 += y;

		vertArray[vertIndex] = x1;
		texArray[texIndex] = region[0];
		vertArray[vertIndex + 1] = y1;
		texArray[texIndex + 1] = region[1];

		vertArray[vertIndex + 2] = x2;
		texArray[texIndex + 2] = region[2];
		vertArray[vertIndex + 3] = y2;
		texArray[texIndex + 3] = region[3];

		vertArray[vertIndex + 4] = x3;
		texArray[texIndex + 4] = region[4];
		vertArray[vertIndex + 5] = y3;
		texArray[texIndex + 5] = region[5];

		vertArray[vertIndex + 6] = x4;
		texArray[texIndex + 6] = region[6];
		vertArray[vertIndex + 7] = y4;
		texArray[texIndex + 7] = region[7];

		for(int i = 0; i < 4; i++) {
			colorArray[colIndex + i * 4] = (byte) (c.r * 255);
			colorArray[colIndex + 1 + i * 4] = (byte) (c.g * 255);
			colorArray[colIndex + 2 + i * 4] = (byte) (c.b * 255);
			colorArray[colIndex + 3 + i * 4] = (byte) (c.a * 255);
		}
		
		vertIndex += 8;
		texIndex += 8;
		colIndex += 16;
		draws++;
		
	}
}
