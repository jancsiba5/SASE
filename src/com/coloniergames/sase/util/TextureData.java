package com.coloniergames.sase.util;

import java.util.HashMap;

import com.coloniergames.sase.gfx.Texture;

public class TextureData {

	private static HashMap<String, Texture> textureTable = new HashMap<String, Texture>();
	public static Texture get(String name) {
		// System.out.println("Request texture \"" + name + "\"");
		if(textureTable.containsKey(name)) return (Texture) textureTable.get(name);
		else { 
			if(load(new Texture(name.replaceAll("\\.", "/") + ".png"), name)) return textureTable.get(name);
		}
		return null;
	}

	public static boolean load(Texture texture, String name) {
		if(!textureTable.containsValue(texture)) {
			textureTable.put(name, texture);
			System.out.println("Loaded texture \"" + name + "\". Now containing " + textureTable.size() + " textures.");
			return true;
		}

		return false;
	}

	public static void clear() {
		
		textureTable.clear();
		
	}
}
