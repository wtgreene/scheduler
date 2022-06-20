package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests Activity.
 * 
 * @author Will Greene
 */
class ActivityTest {

	/**
	 * Tests Activity.checkConflict().
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1445, 1600);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e1.getMessage());
		
		Exception e2 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests Activity.getMeetingString().
	 */
	@Test
	public void testGetMeetingString() {
		Activity a = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 30, 50);
		
		String s = a.getMeetingString();
		assertEquals(s, "MW 12:30AM-12:50AM");
	}
}
