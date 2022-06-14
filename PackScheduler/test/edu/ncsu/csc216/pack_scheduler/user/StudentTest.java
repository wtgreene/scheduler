package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Student.
 * 
 * @author Will Greene
 *
 */
class StudentTest {

	/** student's first name */
	private static final String FIRST_NAME = "firstName";
	/** student's last name */
	private static final String LAST_NAME = "lastName";
	/** student's unity id */
	private static final String ID = "id";
	/** student's email */
	private static final String EMAIL = "email@ncsu.edu";
	/** student's password */
	private static final String PASSWORD = "password";
	/** student's max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Tests Student constructor (includes max credits).
	 */
	@Test
	void testStudentStringStringStringStringStringInt() {

		// test a valid construction
		Student s = assertDoesNotThrow(() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS),
				"Should not throw exception");

		assertAll("Student", () -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect first name"),
				() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect last name"),
				() -> assertEquals(ID, s.getId(), "incorrect id"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"),
				() -> assertEquals(MAX_CREDITS, s.getMaxCredits(), "incorrect max credits"));

		// invalid parameter testing below

		// first name - null
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", e1.getMessage());
		// first name - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", e2.getMessage());

		// last name - null
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", e3.getMessage());
		// last name - empty string
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", e4.getMessage());

		// id - null
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", e5.getMessage());
		// id - empty string
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", e6.getMessage());

		// email - null
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e7.getMessage());
		// email - empty string
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e8.getMessage());
		// email - missing '@'
		Exception e9 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "emailncsu.edu", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e9.getMessage());
		// email - missing '.'
		Exception e10 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsuedu", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e10.getMessage());
		// email - last '.' before first '@'
		Exception e11 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "ncsu.edu@email", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e11.getMessage());

		// password - null
		Exception e12 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS));
		assertEquals("Invalid password", e12.getMessage());
		// password - empty string
		Exception e13 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS));
		assertEquals("Invalid password", e13.getMessage());

		// max credits - less than minimum (by 1)
		Exception e14 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, Student.MIN_CREDITS - 1));
		assertEquals("Invalid max credits", e14.getMessage());
		// max credits - more than maximum (by 1)
		Exception e15 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, Student.MAX_CREDITS + 1));
		assertEquals("Invalid max credits", e15.getMessage());
	}

	/**
	 * Tests Student constructor (does not include max credits).
	 */
	@Test
	void testStudentStringStringStringStringString() {

		// test a valid construction
		Student s = assertDoesNotThrow(() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD),
				"Should not throw exception");

		assertAll("Student", () -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect first name"),
				() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect last name"),
				() -> assertEquals(ID, s.getId(), "incorrect id"),
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect email"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect password"),
				() -> assertEquals(Student.MAX_CREDITS, s.getMaxCredits(), "incorrect max credits"));

		// invalid parameter testing below

		// first name - null
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", e1.getMessage());
		// first name - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid first name", e2.getMessage());

		// last name - null
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", e3.getMessage());
		// last name - empty string
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid last name", e4.getMessage());

		// id - null
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", e5.getMessage());
		// id - empty string
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", e6.getMessage());

		// email - null
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, null, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e7.getMessage());
		// email - empty string
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e8.getMessage());
		// email - missing '@'
		Exception e9 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "emailncsu.edu", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e9.getMessage());
		// email - missing '.'
		Exception e10 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "email@ncsuedu", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e10.getMessage());
		// email - last '.' before first '@'
		Exception e11 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, "ncsu.edu@email", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid email", e11.getMessage());

		// password - null
		Exception e12 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_CREDITS));
		assertEquals("Invalid password", e12.getMessage());
		// password - empty string
		Exception e13 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", MAX_CREDITS));
		assertEquals("Invalid password", e13.getMessage());

		// max credits - less than maximum (by 1)
		Exception e14 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, Student.MIN_CREDITS - 1));
		assertEquals("Invalid max credits", e14.getMessage());
		// max credits - more than maximum (by 1)
		Exception e15 = assertThrows(IllegalArgumentException.class,
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, Student.MAX_CREDITS + 1));
		assertEquals("Invalid max credits", e15.getMessage());
	}

	/**
	 * Tests Student.setFirstName().
	 */
	@Test
	void testSetFirstName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		// valid change
		s.setFirstName(FIRST_NAME + 1);
		assertEquals(FIRST_NAME + 1, s.getFirstName());

		// invalid change - null
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage());
		assertEquals(FIRST_NAME + 1, s.getFirstName());

		// invalid change - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage());
		assertEquals(FIRST_NAME + 1, s.getFirstName());
	}

	/**
	 * Tests Student.setLastName().
	 */
	@Test
	void testSetLastName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		// valid change
		s.setLastName(LAST_NAME + 1);
		assertEquals(LAST_NAME + 1, s.getLastName());

		// invalid change - null
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage());
		assertEquals(LAST_NAME + 1, s.getLastName());

		// invalid change - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage());
		assertEquals(LAST_NAME + 1, s.getLastName());
	}

	/**
	 * Tests Student.setEmail().
	 */
	@Test
	void testSetEmail() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		// valid change
		s.setEmail(EMAIL + 1);
		assertEquals(EMAIL + 1, s.getEmail());

		// invalid change - null
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage());
		assertEquals(EMAIL + 1, s.getEmail());

		// invalid change - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setEmail(""));
		assertEquals("Invalid email", e2.getMessage());
		assertEquals(EMAIL + 1, s.getEmail());

		// invalid change - missing '@'
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("emailncsu.edu"));
		assertEquals("Invalid email", e3.getMessage());
		assertEquals(EMAIL + 1, s.getEmail());

		// invalid change - missing '.'
		Exception e4 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("email@ncsuedu"));
		assertEquals("Invalid email", e4.getMessage());
		assertEquals(EMAIL + 1, s.getEmail());

		// invalid change - last '.' before first '@'
		Exception e5 = assertThrows(IllegalArgumentException.class, () -> s.setEmail("ncsu.edu@email"));
		assertEquals("Invalid email", e5.getMessage());
		assertEquals(EMAIL + 1, s.getEmail());
	}

	/**
	 * Tests Student.setPassword().
	 */
	@Test
	void testSetPassword() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		// valid change
		s.setPassword(PASSWORD + 1);
		assertEquals(PASSWORD + 1, s.getPassword());

		// invalid change - null
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage());
		assertEquals(PASSWORD + 1, s.getPassword());

		// invalid change - empty string
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setPassword(""));
		assertEquals("Invalid password", e2.getMessage());
		assertEquals(PASSWORD + 1, s.getPassword());
	}

	/**
	 * Tests Student.setMaxCredits().
	 */
	@Test
	void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		// valid change
		s.setMaxCredits(MAX_CREDITS + 1);
		assertEquals(MAX_CREDITS + 1, s.getMaxCredits());

		// invalid change - less than minimum (by 1)
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(Student.MIN_CREDITS - 1));
		assertEquals("Invalid max credits", e1.getMessage());
		assertEquals(MAX_CREDITS + 1, s.getMaxCredits());

		// invalid change - more than maximum (by 1)
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> s.setMaxCredits(Student.MAX_CREDITS + 1));
		assertEquals("Invalid max credits", e2.getMessage());
		assertEquals(MAX_CREDITS + 1, s.getMaxCredits());
	}

	/**
	 * Tests Student.compareTo().
	 */
	@Test
	void testCompareTo() {
		Student s1 = new Student("George", "Washington", "gwashington", "gwashington@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s2 = new Student("John", "Adams", "jadams", "jadams@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s3 = new Student("Ben", "Franklin", "bfrank", "bfrank@ncsu.edu", PASSWORD, MAX_CREDITS);
		Student s4 = new Student("Ben", "Franklin", "bfrank", "bfrank@ncsu.edu", PASSWORD, MAX_CREDITS);

		assertEquals(s1.compareTo(s2), 1);
		assertEquals(s2.compareTo(s3), -1);
		assertEquals(s1.compareTo(s3), 1);
		assertEquals(s3.compareTo(s4), 0);
	}

	/**
	 * Tests Student.equals().
	 */
	@Test
	void testEqualsObject() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME + 1, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME + 1, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID + 1, EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL + 1, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD + 1, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS + 1);

		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));

		Student s9 = s1;
		assertTrue(s1.equals(s9));
	}

	/**
	 * Tests Student.toString().
	 */
	@Test
	void testToString() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		assertEquals("firstName,lastName,id,email@ncsu.edu,password,15", s.toString());
	}

	/**
	 * Tests Student.hashCode().
	 */
	@Test
	void testHashCode() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s3 = new Student(FIRST_NAME + 1, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME + 1, ID, EMAIL, PASSWORD, MAX_CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID + 1, EMAIL, PASSWORD, MAX_CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL + 1, PASSWORD, MAX_CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD + 1, MAX_CREDITS);
		Student s8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS + 1);

		assertEquals(s1.hashCode(), s2.hashCode());

		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}
}
