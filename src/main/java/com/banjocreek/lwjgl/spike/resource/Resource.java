package com.banjocreek.lwjgl.spike.resource;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntConsumer;

public final class Resource {

	private final AtomicBoolean released = new AtomicBoolean(false);
	private final IntConsumer release;
	private final int id;
	
	public Resource(final int id, final IntConsumer release) {
		this.id = id;
		this.release = release;
	}
	
	public void release() {
		if (!released.compareAndSet(false, true))
			return;
		
		this.release.accept(this.id);
		
	}
	
	public int id() {
		if (released.get())
			throw new RuntimeException("resource is released");
		return id;
	}
	
}
