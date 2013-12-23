package com.coloniergames.sase;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.coloniergames.sase.util.Vector;
import com.coloniergames.sase.util.Vector2i;

/**
 * 
 * @author jancsiba5
 *
 */

public abstract class GameBase {

	public static Vector2i screenSize, viewportSize;
	public static Vector viewportZ;
	public int GOD_KEY;
	public GameBase(int targetWidth, int targetHeight, boolean targetFullscreen, String title, int viewportRight, int viewportBottom, float zNear, float zFar, int GOD_KEY) {
		screenSize = new Vector2i(targetWidth, targetHeight);
		setUpDisplay(screenSize.x, screenSize.y, targetFullscreen, title);
		viewportSize = new Vector2i(viewportRight, viewportBottom);
		viewportZ = new Vector(zNear, zFar);
		initGL();
		
		init();
		
		this.GOD_KEY = GOD_KEY;
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(GOD_KEY)) {
			
			pollInput();
			
			tick();
			
			renderGL();
			
			Display.update();
			
		}
	}
	
	public abstract void handleKeys(int key);
	public abstract void handleMouseMovement(float mX, float mY, float mDX, float mDY);
	public abstract void handleMouseButton(int button);
	public abstract void otherInputHandling();
	
	public void pollInput() {
		
		while(Keyboard.next()) {
			handleKeys(Keyboard.getEventKey());
		}
		
		handleMouseMovement(Mouse.getX(), screenSize.y - Mouse.getY(), Mouse.getDX(), Mouse.getDY());
		
		while(Mouse.next()) {
			handleMouseButton(Mouse.getEventButton());
		}
		
		otherInputHandling();
		
	}
	
	public void tick() {
		
	}
	
	public void renderGL() {
		
		glClear(GL_COLOR_BUFFER_BIT);
		
		preRender();
		
		drawGame();
		
		postRender();
		
	}
	
	public abstract void preRender();

	public abstract void renderGame();
	
	public abstract void postRender();
	
	public void drawGame() {
		
		renderGame();
		
	}
	
	public abstract void init();
	
	
	
	public void setUpDisplay(int width, int height, boolean fullscreen, String title) {
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) { System.err.println("Problem at Display initialization!"); }
		
		setDisplayMode(width, height, fullscreen);
		
	}
	
	public void setDisplayMode(int width, int height, boolean fullscreen) {

	    // return if requested DisplayMode is already set
	    if ((Display.getDisplayMode().getWidth() == width) && 
	        (Display.getDisplayMode().getHeight() == height) && 
		(Display.isFullscreen() == fullscreen)) {
		    return;
	    }

	    try {
	        DisplayMode targetDisplayMode = null;
			
		if (fullscreen) {
		    DisplayMode[] modes = Display.getAvailableDisplayModes();
		    int freq = 0;
					
		    for (int i=0;i<modes.length;i++) {
		        DisplayMode current = modes[i];
						
			if ((current.getWidth() == width) && (current.getHeight() == height)) {
			    if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
			        if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
				    targetDisplayMode = current;
				    freq = targetDisplayMode.getFrequency();
	                        }
	                    }

			    // if we've found a match for bpp and frequence against the 
			    // original display mode then it's probably best to go for this one
			    // since it's most likely compatible with the monitor
			    if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
	                        (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
	                            targetDisplayMode = current;
	                            break;
	                    }
	                }
	            }
	        } else {
	            targetDisplayMode = new DisplayMode(width,height);
	        }

	        if (targetDisplayMode == null) {
	            System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
	            return;
	        }

	        Display.setDisplayMode(targetDisplayMode);
	        Display.setFullscreen(fullscreen);
				
	    } catch (LWJGLException e) {
	        System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
	    }
	}

	public void initGL() {
	
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, viewportSize.x, viewportSize.y, 0, viewportZ.x, viewportZ.y);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		
		glShadeModel(GL_SMOOTH);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
	}
	
	public static void setPath() {
		System.setProperty("org.lwjgl.librarypath", new File("lib/natives/windows").getAbsolutePath());
	}
	
}
