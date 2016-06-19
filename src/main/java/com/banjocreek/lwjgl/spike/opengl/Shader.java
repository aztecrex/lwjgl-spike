package com.banjocreek.lwjgl.spike.opengl;

import org.lwjgl.opengl.GL20;

public final class Shader implements AutoCloseable {

	private final Resource glShader;
	
	public Shader(Type type, CharSequence source) {
		this.glShader = new Resource(GL20.glCreateShader(type.code), GL20::glDeleteShader);
		GL20.glShaderSource(glShader.id(), source);
		GL20.glCompileShader(this.glShader.id());
		if (GL20.glGetShaderi(this.glShader.id(), GL20.GL_COMPILE_STATUS) == 0) {
			final String info = GL20.glGetShaderInfoLog(glShader.id(),4096);
			glShader.release();
		    throw new RuntimeException(
		    		"Error compiling Shader code: " + info);
		}

	}
	
	@Override
	public void close() throws Exception {
		glShader.release();
	}

	
	void attach(Resource glProgram) {
		GL20.glAttachShader(glProgram.id(), glShader.id());
	}
	
	public enum Type {
		Vertex(GL20.GL_VERTEX_SHADER),
		Fragment(GL20.GL_FRAGMENT_SHADER)
		;
		private final int code;
		Type(int code) {
			this.code = code;
		}
	}
	
}
