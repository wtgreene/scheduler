/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
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
		
		// parameter error checking - duplicate
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(c)) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}
		}
		
		// parameter error checking - time conflict
		for (int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(c);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		schedule.add(c); // might need to try + catch if duplicate error checking doesn't always throw IAE
		
		return true;
	}
	
	/**
	 * Removes a Course from the Schedule.
	 * 
	 * @param c Course to remove
	 * @return true if Course was removed, false if there was not a Course to remove
	 */
	public boolean removeCourseFromSchedule(Course c) {
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(c)) {
				schedule.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Resets the Schedule
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Returns a 2D array of Course information from the Schedule.
	 * 
	 * @return a 2D array of Course information
	 */
	public String[][] getScheduledCourses() {
		
		String[][] s = new String[schedule.size()][];
		
		for (int i = 0; i < schedule.size(); i++) {
			s[i] = schedule.get(i).getShortDisplayArray();
		}
		
		return s;
	}
	
	/**
	 * Sets the Schedule title.
	 * 
	 * @param title title to set
	 * @throws IllegalArgumentException if null title
	 */
	public void setTitle(String title) {
		
		// parameter error checking - null
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}
	
	/**
	 * Returns the Schedule title.
	 * @return the Schedule title
	 */
	public String getTitle() {
		return title;
	}
}
