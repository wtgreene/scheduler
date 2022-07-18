/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Controls enrollment, dropping, and cap for a Course.
 * 
 * @author Will Greene
 */
public class CourseRoll {

	/** list of Students */
	private LinkedAbstractList<Student> roll;
	/** enrollment capacity of the roll */
	private int enrollmentCap;
//	/** Course */
//	private Course c;
	/** wait list */
	private LinkedQueue<Student> waitlist;

	/** smallest class size */
	private static final int MIN_ENROLLMENT = 10;
	/** largest class size */
	private static final int MAX_ENROLLMENT = 250;
	/** initial size of wait list */
	private static final int WAITLIST_SIZE = 10;

	/**
	 * Constructs a CourseRoll object.
	 * 
	 * @param c             Course
	 * @param enrollmentCap enrollment capacity of the roll
	 */
	public CourseRoll(Course c, int enrollmentCap) {

		if (c == null) {
			throw new IllegalArgumentException();
		}

//		this.c = c;
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
	}

	/**
	 * Returns the enrollment cap.
	 * 
	 * @return the enrollment cap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the enrollment cap.
	 * 
	 * @param enrollmentCap enrollment capacity of the roll
	 * @throws IllegalArgumentException if outside of range
	 */
	public void setEnrollmentCap(int enrollmentCap) {

		// parameter error checking - outside of range
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}

		if (roll == null) {
			this.enrollmentCap = enrollmentCap;
		}

		else {
			if (enrollmentCap < roll.size()) {
				throw new IllegalArgumentException("Cap cannot be less than number of students.");
			}

			else {
				this.enrollmentCap = enrollmentCap;
			}
		}
	}

	/**
	 * Enrolls a Student (by adding the Student to the list).
	 * 
	 * @param s Student to be enrolled
	 * @throws IllegalArgumentException if Student parameter is invalid
	 */
	public void enroll(Student s) {

		// parameter error checking - null Student
		if (s == null) {
			throw new IllegalArgumentException();
		}

		// parameter error checking - no more room in class
		if (roll.size() == enrollmentCap) {
			waitlist.enqueue(s);
			throw new IllegalArgumentException();
		}

		// parameter error checking - Student already enrolled
		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				throw new IllegalArgumentException();
			}
		}

		try {
			roll.add(roll.size(), s);
		} catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Drops a Student from the roll (by removing the Student from the list).
	 * 
	 * @param s Student to be removed
	 * @throws IllegalArgumentException if Student parameter is invalid
	 */
	public void drop(Student s) {

		// parameter error checking - null Student
		if (s == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < roll.size(); i++) {

			if (roll.get(i).equals(s)) {

				try {
					roll.remove(i);
					if (!waitlist.isEmpty()) {
						roll.add(waitlist.dequeue());
					}
				} catch (IndexOutOfBoundsException e) {
					throw new IllegalArgumentException();
				}
			}
		}
	}

	/**
	 * Returns the number of open seats left in the roll.
	 * 
	 * @return the number of open seats left in the roll
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Returns whether the Student is able to enroll.
	 * 
	 * @param s Student to be evaluated
	 * @return true if able, false if not
	 */
	public boolean canEnroll(Student s) {

		if (roll.size() == enrollmentCap && waitlist.size() == WAITLIST_SIZE) {
			return false;
		}

		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}

		for (int i = 0; i < waitlist.size(); i++) {
			if (waitlist.contains(s)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns number of Students on the wait list.
	 * 
	 * @return number of Students on the wait list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
