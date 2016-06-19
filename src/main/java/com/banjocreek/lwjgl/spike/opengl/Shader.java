package com.banjocreek.lwjgl.spike.opengl;

import org.lwjgl.opengl.GL20;

import com.banjocreek.lwjgl.spike.resource.Resource;

public final class Shader implements AutoCloseable {

	private final Resource glShader;
	
	public Shader(OpenGL gl, Type type, CharSequence source) {
		this.glShader = new Resource(gl.glCreateShader(type.code), gl::glDeleteShader);
		gl.glShaderSource(glShader.id(), source);
		gl.glCompileShader(this.glShader.id());
		if (gl.glGetShader(this.glShader.id(), GL20.GL_COMPILE_STATUS) == 0) {
			final String info = gl.glGetShaderInfoLog(glShader.id(),4096);
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
