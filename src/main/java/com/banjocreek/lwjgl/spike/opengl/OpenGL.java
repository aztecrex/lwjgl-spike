package com.banjocreek.lwjgl.spike.opengl;

public interface OpenGL {

	static OpenGL bind() {
		return BoundOpenGL.INSTANCE;
	}
	
	int glCreateShader(int type);

	void glShaderSource(int sid, CharSequence src);

	void glCompileShader(int sid);

	int glGetShader(int sid, int pname);

	String glGetShaderInfoLog(int sid, int buflen);

	void glDeleteShader(int sid);

	int glCreateProgram();
	
	void glLinkProgram(int pid);

	void glDeleteProgram(int pid);

	void glUseProgram(int pid);
}