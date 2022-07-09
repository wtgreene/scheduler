/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll.java
 * 
 * @author Will Greene
 */
public class CourseRollTest {
	
	/** Test Student's first name. */
	private static final String FIRST_NAME = "first";
	/** Test Student's last name */
	private static final String LAST_NAME = "last";
	/** Test Student's id */
	private static final String ID = "flast";
	/** Test Student's email */
	private static final String EMAIL = "first_last@ncsu.edu";
	/** Test Student's password */
	private static final String PASSWORD = "password";
	
	/**
	 * Tests CourseRoll constructor.
	 */
	@Test
	public void testCourseRoll() {
		assertDoesNotThrow(() -> new CourseRoll(100));
	}
	
	/**
	 * Tests CourseRoll.getEnrollmentCap().
	 */
	@Test
	public void testGetEnrollmentCap() {
		CourseRoll roll = new CourseRoll(100);
		assertEquals(100, roll.getEnrollmentCap());
	}
	
	/**
	 * Tests CourseRoll.setEnrollmentCap().
	 */
	@Test
	public void testSetEnrollmentCap() {
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(9));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251));
		
		CourseRoll roll = new CourseRoll(100);
		roll.setEnrollmentCap(11);
		assertEquals(11, roll.getEnrollmentCap());
		
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		
		Student s1 = sd.getStudentById("zking");
		Student s2 = sd.getStudentById("cschwartz");
		Student s3 = sd.getStudentById("shansen");
		Student s4 = sd.getStudentById("daustin");
		Student s5 = sd.getStudentById("rbrennan");
		Student s6 = sd.getStudentById("efrost");
		Student s7 = sd.getStudentById("lberg");
		Student s8 = sd.getStudentById("gstone");
		Student s9 = sd.getStudentById("ahicks");
		Student s10 = sd.getStudentById("dnolan");
		
		Student s11 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		
		assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(10));
	}
	
	/**
	 * Tests CourseRoll.enroll().
	 */
	@Test
	public void testEnroll() {
		CourseRoll roll = new CourseRoll(10);
		
		assertThrows(IllegalArgumentException.class, () -> roll.enroll(null));

		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		
		Student s1 = sd.getStudentById("zking");
		
		assertDoesNotThrow(() -> roll.enroll(s1));

		assertThrows(IllegalArgumentException.class, () -> roll.enroll(s1));
		
		Student s2 = sd.getStudentById("cschwartz");
		Student s3 = sd.getStudentById("shansen");
		Student s4 = sd.getStudentById("daustin");
		Student s5 = sd.getStudentById("rbrennan");
		Student s6 = sd.getStudentById("efrost");
		Student s7 = sd.getStudentById("lberg");
		Student s8 = sd.getStudentById("gstone");
		Student s9 = sd.getStudentById("ahicks");
		Student s10 = sd.getStudentById("dnolan");
		
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		
		Student s11 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		
		assertThrows(IllegalArgumentException.class, () -> roll.enroll(s11));
	}
	
	/**
	 * Tests CourseRoll.drop().
	 */
	@Test
	public void testDrop() {
		CourseRoll roll = new CourseRoll(100);
		
		assertThrows(IllegalArgumentException.class, () -> roll.drop(null));

		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		Student s = sd.getStudentById("zking");
		
		roll.enroll(s);
		
		assertDoesNotThrow(() -> roll.drop(s));
	}
	
	/**
	 * Tests CourseRoll.getOpenSeats().
	 */
	@Test
	public void testGetOpenSeats() {
		CourseRoll roll = new CourseRoll(10);
		
		assertEquals(10, roll.getOpenSeats());

		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		
		Student s1 = sd.getStudentById("zking");		
		Student s2 = sd.getStudentById("cschwartz");
		Student s3 = sd.getStudentById("shansen");
		Student s4 = sd.getStudentById("daustin");
		Student s5 = sd.getStudentById("rbrennan");
		Student s6 = sd.getStudentById("efrost");
		Student s7 = sd.getStudentById("lberg");
		Student s8 = sd.getStudentById("gstone");
		Student s9 = sd.getStudentById("ahicks");
		Student s10 = sd.getStudentById("dnolan");
		
		roll.enroll(s1);
		
		assertEquals(9, roll.getOpenSeats());
		
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		
		assertEquals(0, roll.getOpenSeats());
	}
	
	/**
	 * Tests CourseRoll.canEnroll().
	 */
	@Test
	public void testCanEnroll() {
		CourseRoll roll = new CourseRoll(10);
		
		StudentDirectory sd = new StudentDirectory();
		sd.loadStudentsFromFile("test-files/student_records.txt");
		
		Student s1 = sd.getStudentById("zking");		
		Student s2 = sd.getStudentById("cschwartz");
		Student s3 = sd.getStudentById("shansen");
		Student s4 = sd.getStudentById("daustin");
		Student s5 = sd.getStudentById("rbrennan");
		Student s6 = sd.getStudentById("efrost");
		Student s7 = sd.getStudentById("lberg");
		Student s8 = sd.getStudentById("gstone");
		Student s9 = sd.getStudentById("ahicks");
		Student s10 = sd.getStudentById("dnolan");
		
		assertTrue(roll.canEnroll(s1));
		
		roll.enroll(s1);
		
		assertFalse(roll.canEnroll(s1));
		
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		
		Student s11 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
				
		assertFalse(roll.canEnroll(s11));
	}
}
