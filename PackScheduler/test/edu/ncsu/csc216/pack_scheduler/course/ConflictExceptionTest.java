/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * TODO
 * @author Will Greene
 *
 */
class ConflictExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	void testConflictExceptionString() {
		ConflictException ce = new ConflictException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
