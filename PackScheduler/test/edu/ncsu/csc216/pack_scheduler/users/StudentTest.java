package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests the Student object.
 * @author SarahHeckman
 */
public class StudentTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/** Course name */
	private static final String NAME_2 = "CSC116";
	/** Course title */
	private static final String TITLE_2 = "Intro to Programming - Java";
	/** Course section */
	private static final String SECTION_2 = "001";
	/** Course credits */
	private static final int CREDITS_2 = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID_2 = "jdyoung2";
	/** Course meeting days */
	private static final String MEETING_DAYS_2 = "MW";
	/** Course start time */
	private static final int START_TIME_2 = 910;
	/** Course end time */
	private static final int END_TIME_2 = 1100;
	
	/** Course enrollment cap */
	private static final int ENROLLMENT_CAP = 10;
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
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
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * Tests Student.canAdd().
	 */
	@Test
	public void testCanAdd() {
		Student s = new Student(firstName, lastName, id, email, hashPW, 6);
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		// now has same meeting days, start and end times as c1
		Course c2 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertFalse(s.canAdd(null));
		assertTrue(s.canAdd(c1));
		
		s.getSchedule().addCourseToSchedule(c1);
		
		assertFalse(s.canAdd(c1));
		assertFalse(s.canAdd(c2));
		
		Course c3 = new Course(NAME_2, TITLE_2, SECTION_2, 4, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		Course c4 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		
		assertFalse(s.canAdd(c3));
		assertTrue(s.canAdd(c4));
	}

}
