package com.banjocreek.lwjgl.spike.opengl;

import org.lwjgl.opengl.GL20;

final class BoundOpenGL implements OpenGL {

	public static final BoundOpenGL INSTANCE = new BoundOpenGL();

	@Override
	public int glCreateShader(int type) {
		return GL20.glCreateShader(type);
	}

	@Override
	public void glShaderSource(int sid, CharSequence src) {
		GL20.glShaderSource(sid, src);
	}

	@Override
	public void glCompileShader(int sid) {
		GL20.glCompileShader(sid);
	}

	@Override
	public int glGetShader(int sid, int pname) {
		return GL20.glGetShaderi(sid, pname);
	}

	@Override
	public String glGetShaderInfoLog(int sid, int buflen) {
		return GL20.glGetShaderInfoLog(sid, buflen);
	}

	@Override
	public void glDeleteShader(int sid) {
		GL20.glDeleteShader(sid);
	}

	@Override
	public int glCreateProgram() {
		return GL20.glCreateProgram();
	}

	@Override
	public void glLinkProgram(int pid) {
		GL20.glLinkProgram(pid);
	}

	@Override
	public void glDeleteProgram(int pid) {
		GL20.glDeleteProgram(pid);
	}

	@Override
	public void glUseProgram(int pid) {
		GL20.glUseProgram(pid);
	}

}
