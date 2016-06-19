package com.banjocreek.lwjgl.spike;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public final class StaticModel implements Model, AutoCloseable {

	private final int glArray;
	private final int glBuffer;
	private final int location;
	private final int vertexCount;

	public StaticModel(final int location, final float[] vertices) {

		this.location = location;
		this.glArray = GL30.glGenVertexArrays();
		this.vertexCount = vertices.length / 2;

		GL30.glBindVertexArray(glArray);

		glBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, glBuffer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

	}

	public void close() {
		GL15.glDeleteBuffers(glBuffer);
		GL30.glDeleteVertexArrays(glArray);
	}

	@Override
	public void render() {
		GL30.glBindVertexArray(glArray);
		GL20.glEnableVertexAttribArray(location);

		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
		GL20.glDisableVertexAttribArray(location);
		GL30.glBindVertexArray(glArray);
	}
}
