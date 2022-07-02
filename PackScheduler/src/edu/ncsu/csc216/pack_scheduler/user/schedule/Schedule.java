/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Assembles a Schedule for a User.
 * 
 * @author Will Greene
 */
public class Schedule {
	
	/** schedule */
	private ArrayList<Course> schedule;
	
	/** schedule title */
	private String title;
	
	/**
	 * Schedule constructor.
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Adds a Course to the Schedule.
	 * 
	 * @param c Course to add
	 * @return true if Course was added, false if not
	 */
	public boolean addCourseToSchedule(Course c) {
		
		
		
		return false;
	}

}
