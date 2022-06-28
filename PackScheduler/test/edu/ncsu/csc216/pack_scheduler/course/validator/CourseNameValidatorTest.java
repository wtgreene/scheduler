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
public class CourseNameValidatorTest {
	
	/**
	 * Tests CourseNameValidator.isValid() - LLLDDD
	 */
	@Test
	public void testIsValidLLLDDD() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();		
		assertTrue(c.isValid("ABC123"));
	}

	/**
	 * Tests CourseNameValidator.isValid() - LLLLDL
	 */
	@Test
	public void testIsValidLLLLDL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABCD1A"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LLL DDD
	 */
	@Test
	public void testIsValidLLLspaceDDD() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABC 123"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - special character
	 */
	@Test
	public void testIsValidSpecial() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("!"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - D
	 */
	@Test
	public void testIsValidD() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("9"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LLLLL
	 */
	@Test
	public void testIsValidLLLLL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABCDE"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LDL
	 */
	@Test
	public void testIsValidLDL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A1A"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LDDL
	 */
	@Test
	public void testIsValidLDDL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A12F"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LDDDD
	 */
	@Test
	public void testIsValidLDDDD() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A1234"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LDDDLL
	 */
	@Test
	public void testIsValidLDDDLL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A123FG"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LDDDLD
	 */
	@Test
	public void testIsValidLDDDLD() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("A123F5"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LLDL
	 */
	@Test
	public void testIsValidLLDL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("AB1A"));
	}
	
	/**
	 * Tests CourseNameValidator.isValid() - LLLDL
	 */
	@Test
	public void testIsValidLLLDL() throws InvalidTransitionException {
		CourseNameValidator c = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> c.isValid("ABC1A"));
	}
}
