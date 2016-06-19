package test.com.banjocreek.lwjgl.spike.resource;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.banjocreek.lwjgl.spike.resource.Resource;

public class ResourceTest {

	@Test
	public void testResourceStoresId() {
		
		assertEquals(10, new Resource(10, i -> {}).id(), 10);
		
	}
	
	@Test public void testResourceInvokesReleaser() {
		
		// closure over mutable...
		final AtomicInteger released = new AtomicInteger(-1);
		
		new Resource(10, released::set).release();
		
		assertEquals(10, released.get());
		
		
	}
	
	@Test(expected=RuntimeException.class)
	public void testCannotUseReleased() {
		final Resource res = new Resource(10, i-> {});
		res.release();
		res.id();
	}
	
}
