/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.users;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests Faculty.java
 * 
 * @author Will Greene
 */
public class FacultyTest {
	
	/** Test Faculty's first name. */
	private String firstName = "first";
	/** Test Faculty's last name */
	private String lastName = "last";
	/** Test Faculty's id */
	private String id = "flast";
	/** Test Faculty's email */
	private String email = "first_last@ncsu.edu";
	/** Test Faculty's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the FacultyTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the FacultyTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Tests Faculty constructor.
	 */
	@Test
	public void testFaculty() {
		assertDoesNotThrow(() -> new Faculty(firstName, lastName, id, email, hashPW, 2));
	}
	
	/**
	 * Tests Faculty.setMaxCourses().
	 */
	@Test
	public void testSetMaxCourses() {
		Faculty f = new Faculty(firstName, lastName, id, email, hashPW, 2);
		
		assertThrows(IllegalArgumentException.class, () -> f.setMaxCourses(0));
		assertThrows(IllegalArgumentException.class, () -> f.setMaxCourses(4));
		
		f.setMaxCourses(2);
				
		assertEquals(2, f.getMaxCourses());
	}
	
	/**
	 * Tests Faculty.hashCode().
	 */
	@Test
	public void testHashCode() {
		Faculty f1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		Faculty f2 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		Faculty f3 = new Faculty(firstName, lastName, id, email, "somethingDifferent", 2);

		assertEquals(f1.hashCode(), f2.hashCode());
		assertNotEquals(f1.hashCode(), f3.hashCode());
	}
	
	/**
	 * Tests Faculty.equals().
	 */
	@Test
	public void testEquals() {
		Faculty f1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		Faculty f2 = new Faculty(firstName, lastName, id, email, hashPW, 2);
		Faculty f3 = new Faculty(firstName, lastName, id, email, "somethingDifferent", 2);
		
		assertTrue(f1.equals(f2));
		assertTrue(f2.equals(f1));
		assertFalse(f1.equals(f3));
	}
	
	/**
	 * Tests Faculty.toString().
	 */
	@Test
	public void testToString() {
		Faculty f = new Faculty(firstName, lastName, id, email, hashPW, 2);

		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",2", f.toString());
	}
}
