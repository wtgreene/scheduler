/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Assembles information for a Faculty
 * 
 * @author Will Greene
 */
public class Faculty extends User {

	/** minimum number of courses for a Faculty */
	public static final int MIN_COURSES = 1;
	/** maximum number of courses for a Faculty */
	public static final int MAX_COURSES = 3;

	/** Faculty's max courses */
	private int maxCourses;
	/** schedule */
	private FacultySchedule schedule;

	/**
	 * Constructs a Faculty object with values for all fields.
	 * 
	 * @param firstName  Faculty's first name
	 * @param lastName   Faculty's last name
	 * @param id         Faculty's unity id
	 * @param email      Faculty's email
	 * @param password   Faculty's password
	 * @param maxCourses Faculty's max number of courses
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Sets the Faculty's maximum Courses hours. If maxCourses is outside the
	 * defined range, an IllegalArgumentException is thrown.
	 * 
	 * @param maxCourses Faculty's maximum number of credit hours
	 * @throws IllegalArgumentException if maxCourses parameter is invalid
	 */
	public void setMaxCourses(int maxCourses) {

		// throw exception if outside of credit range
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}

		this.maxCourses = maxCourses;
	}

	/**
	 * Returns the Faculty's maximum credit hours.
	 * 
	 * @return the maxCourses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Generates a hashCode for Faculty using maxCourses.
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}

	/**
	 * Compares a given object to this object for equality on hashCode.
	 * 
	 * @param obj object to compare to
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * Returns a comma-separated value String of all Faculty fields.
	 * 
	 * @return String representation of Faculty
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}
	
	/**
	 * Returns Faculty Schedule.
	 * @return Faculty Schedule
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Returns whether number of scheduled courses is greater than maxCourses.
	 * @return true if overloaded, false if not
	 */
	public boolean isOverloaded() {
		return getSchedule().getNumScheduledCourses() > getMaxCourses();
	}
}
