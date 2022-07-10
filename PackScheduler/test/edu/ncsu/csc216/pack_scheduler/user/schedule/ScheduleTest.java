/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests Schedule.java
 * 
 * @author Will Greene
 */
public class ScheduleTest {
	
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
	
	/**
	 * Tests Schedule constructor.
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		
		assertEquals("My Schedule", s.getTitle());
		
		String[][] display = s.getScheduledCourses();
		
		assertThrows(IndexOutOfBoundsException.class, () -> assertEquals(null, display[0][0]));
	}
	
	/**
	 * Tests Schedule.addCourseToSchedule().
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		assertTrue(s.addCourseToSchedule(c));
		
		String[][] display = s.getScheduledCourses();
		
		assertEquals(NAME, display[0][0]);
		assertEquals(SECTION, display[0][1]);
		assertEquals(TITLE, display[0][2]);
		assertEquals(c.getMeetingString(), display[0][3]);
	}
	
	/**
	 * Tests Schedule.removeCourseFromSchedule().
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		s.addCourseToSchedule(c);
		assertTrue(s.removeCourseFromSchedule(c));
	}
	
	/**
	 * Tests Schedule.resetSchedule().
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule();
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		s.setTitle("Your Schedule");
		s.addCourseToSchedule(c);
		s.resetSchedule();
		
		String[][] display = s.getScheduledCourses();
		
		assertEquals("My Schedule", s.getTitle());
		assertThrows(IndexOutOfBoundsException.class, () -> assertEquals(null, display[0][0]));
	}
	
	/**
	 * Tests Schedule.getScheduledCourses().
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s = new Schedule();
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);

		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		
		String[][] display = s.getScheduledCourses();
		
		assertEquals(NAME, display[0][0]);
		assertEquals(SECTION, display[0][1]);
		assertEquals(TITLE, display[0][2]);
		assertEquals(c1.getMeetingString(), display[0][3]);
		
		assertEquals(NAME_2, display[1][0]);
		assertEquals(SECTION_2, display[1][1]);
		assertEquals(TITLE_2, display[1][2]);
		assertEquals(c2.getMeetingString(), display[1][3]);
	}
	
	/**
	 * Test Schedule.setTitle().
	 */
	@Test
	public void testSetTitle() {
		Schedule s = new Schedule();

		s.setTitle("Your Schedule");
		assertEquals("Your Schedule", s.getTitle());
	}
	
	/**
	 * Tests Schedule.getScheduleCredits().
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		
		assertEquals(0, s.getScheduleCredits());
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);

		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		
		assertEquals(6, s.getScheduleCredits());
	}
	
	/**
	 * Tests Schedule.canAdd().
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		
		assertFalse(s.canAdd(null));
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertTrue(s.canAdd(c1));
		
		s.addCourseToSchedule(c1);

		assertFalse(s.canAdd(c1));
		
		// now has same meeting days, start and end times as c1
		Course c2 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);

		assertFalse(s.canAdd(c2));
	}
}
