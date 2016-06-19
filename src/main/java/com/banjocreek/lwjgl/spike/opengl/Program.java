package com.banjocreek.lwjgl.spike.opengl;

import java.util.Arrays;

import org.lwjgl.opengl.GL20;

public final class Program implements AutoCloseable {

	private final Resource glProgram;

	public Program(Shader... shaders) {
		this.glProgram = new Resource(GL20.glCreateProgram(), GL20::glDeleteProgram);
		Arrays.stream(shaders)
			.forEach(s -> s.attach(glProgram));
		GL20.glLinkProgram(glProgram.id());
	}

	public void use()  {
		GL20.glUseProgram(glProgram.id());
	}
	
	@Override
	public void close() throws Exception {
		glProgram.release();
	}

}
