package com.banjocreek.lwjgl.spike.opengl;

import java.util.Arrays;

import com.banjocreek.lwjgl.spike.resource.Resource;

public final class Program implements AutoCloseable {

	private final Resource glProgram;
	private final OpenGL gl;
	
	
	public Program(OpenGL gl, Shader... shaders) {
		this.gl = gl;
		this.glProgram = new Resource(this.gl.glCreateProgram(), this.gl::glDeleteProgram);
		Arrays.stream(shaders)
			.forEach(s -> s.attach(glProgram));
		gl.glLinkProgram(glProgram.id());
	}

	public void use()  {
		this.gl.glUseProgram(this.glProgram.id());
	}
	
	@Override
	public void close() throws Exception {
		glProgram.release();
	}

}
