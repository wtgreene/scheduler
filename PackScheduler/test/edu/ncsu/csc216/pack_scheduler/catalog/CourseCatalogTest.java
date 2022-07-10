/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog class.
 * 
 * @author Will Greene (uses constants from Dr. Heckman's WolfSchedulerTest
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	
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
	private static final String MEETING_DAYS = "TH";
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
	 * Tests CourseCatalog constructor.
	 */
	@Test
	public void testCourseCatalog() {
		assertDoesNotThrow(() -> new CourseCatalog(), "Should not throw exception");
	}
	
	/**
	 * Test CourseCatalog.newCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		
		c.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		c.addCourseToCatalog(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);

		c.newCourseCatalog();
		
		assertEquals(c.getCourseFromCatalog(NAME, SECTION), null);
		assertEquals(c.getCourseFromCatalog(NAME_2, SECTION_2), null);
	}
	
	/**
	 * Tests CourseCatalog.loadCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog c = new CourseCatalog();

		c.loadCoursesFromFile(validTestFile);
		Course course = c.getCourseFromCatalog("CSC316", "001");
		assertEquals(course.getName(), "CSC316");
	}
	
	/**
	 * Tests CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog c = new CourseCatalog();
		
		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		c.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		
		assertEquals(c.getCourseFromCatalog(NAME, SECTION), course);
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog c = new CourseCatalog();
		
		c.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		c.addCourseToCatalog(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		
		c.removeCourseFromCatalog(NAME, SECTION);
		
		assertEquals(c.getCourseFromCatalog(NAME, SECTION), null);
	}
	
	/**
	 * Tests CourseCatalog.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		
		c.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		c.addCourseToCatalog(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		
		Course c1 = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		Course c2 = new Course(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		
		String[][] catalog = c.getCourseCatalog();
		
		assertEquals(catalog[0][0], NAME_2);
		assertEquals(catalog[0][1], SECTION_2);
		assertEquals(catalog[0][2], TITLE_2);
		assertEquals(catalog[0][3], c2.getMeetingString());
		assertEquals(catalog[1][0], NAME);
		assertEquals(catalog[1][1], SECTION);
		assertEquals(catalog[1][2], TITLE);
		assertEquals(catalog[1][3], c1.getMeetingString());
	}

	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog c = new CourseCatalog();
		
		c.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME, END_TIME);
		c.addCourseToCatalog(NAME_2, TITLE_2, SECTION_2, CREDITS_2, INSTRUCTOR_ID_2, ENROLLMENT_CAP, MEETING_DAYS_2, START_TIME_2, END_TIME_2);
		
		assertDoesNotThrow(() -> c.saveCourseCatalog("test-files/sample_catalog.txt"));
	}
}
