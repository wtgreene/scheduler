/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests ArrayQueue.java
 * 
 * @author Will Greene
 * @param <E> element
 */
class ArrayQueueTest<E> {

	/**
	 * Tests ArrayQueue constructor.
	 */
	@Test
	void testArrayQueue() {
		assertDoesNotThrow(() -> new ArrayQueue<E>(10));
	}

	/**
	 * Tests ArrayQueue.enqueue().
	 */
	@Test
	void testEnqueue() {
		ArrayQueue<String> a = new ArrayQueue<String>(2);
		
		a.enqueue("a");
		
		assertEquals(1, a.size());
		
		a.enqueue("b");

		assertEquals(2, a.size());
		
		assertThrows(IllegalArgumentException.class, () -> a.enqueue("c"));
	}

	/**
	 * Tests ArrayQueue.dequeue().
	 */
	@Test
	void testDequeue() {
		ArrayQueue<String> a = new ArrayQueue<String>(10);
		
		assertThrows(NoSuchElementException.class, () -> a.dequeue());
		
		a.enqueue("a");
		a.enqueue("b");

		assertEquals("a", a.dequeue());
		assertEquals("b", a.dequeue());
	}

	/**
	 * Tests ArrayQueue.isEmpty().
	 */
	@Test
	void testIsEmpty() {
		ArrayQueue<String> a = new ArrayQueue<String>(10);
		
		assertTrue(a.isEmpty());
		
		a.enqueue("a");
		
		assertFalse(a.isEmpty());
	}

	/**
	 * Tests ArrayQueue.size().
	 */
	@Test
	void testSize() {
		ArrayQueue<String> a = new ArrayQueue<String>(10);
		
		a.enqueue("a");		
		a.enqueue("b");
		
		assertEquals(2, a.size());
		
		a.dequeue();
		
		assertEquals(1, a.size());
	}

	/**
	 * Tests ArrayQueue.setCapacity().
	 */
	@Test
	void testSetCapacity() {
		ArrayQueue<String> a = new ArrayQueue<String>(10);
		
		a.enqueue("a");
		a.enqueue("b");
		a.enqueue("c");
		
		assertDoesNotThrow(() -> a.setCapacity(3));
		
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> a.setCapacity(2));
	}
}
