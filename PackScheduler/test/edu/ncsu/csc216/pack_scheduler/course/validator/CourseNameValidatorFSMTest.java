/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests CourseNameValidator.java
 * 
 * @author Will Greene
 */
public class CourseNameValidatorFSMTest {
	
	/**
	 * Tests CourseNameValidator.isValid().
	 */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		
		assertThrows(InvalidTransitionException.class, () -> c.isValid("!"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("9"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABCDE"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A1A"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A12F"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A1234"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A123FG"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A123F5"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("AB1A"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABC1A"));
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABCD1A"));
		
		assertTrue(c.isValid("ABC123"));
	}
}
