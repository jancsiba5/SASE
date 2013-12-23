package com.coloniergames.sase.gfx.shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ShaderProgram {

	public int vertexShader, fragmentShader, shaderProgram;
	
	StringBuilder vertexShaderSource = new StringBuilder();
	StringBuilder fragmentShaderSource = new StringBuilder();
	
	public ShaderProgram(String name) {
		
		shaderProgram = glCreateProgram();
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		BufferedReader vertexReader = null;
		try {
			vertexReader = new BufferedReader(new FileReader(name + ".vs"));
			String line;
			while((line = vertexReader.readLine()) != null) {
				vertexShaderSource.append(line).append('\n');
			}
		} catch (Exception e) {
			System.out.println("VERTEX SHADER NOT FOUND!");
		} finally {
			if(vertexReader != null) {
				try {
					vertexReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		BufferedReader fragmentReader = null;
		try {
			fragmentReader = new BufferedReader(new FileReader(name + ".fs"));
			String line;
			while((line = fragmentReader.readLine()) != null) {
				fragmentShaderSource.append(line).append('\n');
			}
		} catch (Exception e) {
			System.out.println("FRAGMENT SHADER NOT FOUND!");
		} finally {
			if(fragmentReader != null) {
				try {
					fragmentReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println("VERTEX SHADER WASN'T COMPILED!");
		}
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.out.println("FRAGMENT SHADER WASN'T COMPILED!");
		}
		
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);

		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);

	}
	
	public void use() {
		glUseProgram(shaderProgram);
	}
	
	public void unUse() {
		glUseProgram(0);
	}
}
