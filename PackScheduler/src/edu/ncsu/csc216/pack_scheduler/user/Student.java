package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Assembles information for a Student
 * 
 * @author Will Greene
 */
public class Student extends User implements Comparable<Student> {

	/** minimum number of credits for a student */
	public static final int MIN_CREDITS = 3;
	/** maximum number of credits for a student */
	public static final int MAX_CREDITS = 18;

	/** student's max credits */
	private int maxCredits;

	/**
	 * Constructs a Student object with values for all fields.
	 * 
	 * @param firstName  Student's first name
	 * @param lastName   Student's last name
	 * @param id         Student's unity id
	 * @param email      Student's email
	 * @param password   Student's password
	 * @param maxCredits Student's max number of credit hours
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}

	/**
	 * Constructs a Student object with the given firstName, lastName, id, email and
	 * password.
	 * 
	 * @param firstName Student's first name
	 * @param lastName  Student's last name
	 * @param id        Student's unity id
	 * @param email     Student's email
	 * @param password  Student's password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Returns the Student's maximum credit hours.
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the Student's maximum credits hours. If maxCredits is outside the
	 * defined range, an IllegalArgumentException is thrown.
	 * 
	 * @param maxCredits Student's maximum number of credit hours
	 * @throws IllegalArgumentException if maxCredits parameter is invalid
	 */
	public void setMaxCredits(int maxCredits) {

		// throw exception if outside of credit range
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}

	/**
	 * Alphabetically compares current student to another, by last name, then first name (if
	 * necessary), then unity id (if necessary).
	 * 
	 * @param o Student to compare to
	 * @return -1 if less than, 0 if equal to, or 1 if greater than Student o
	 */
	@Override
	public int compareTo(Student o) {
	
		String last1 = getLastName().toLowerCase();
		String last2 = o.getLastName().toLowerCase();
		String first1 = getFirstName().toLowerCase();
		String first2 = o.getFirstName().toLowerCase();
		String id1 = getId().toLowerCase();
		String id2 = o.getId().toLowerCase();
	
		// last name
		for (int i = 0; i < last1.length(); i++) {
			if (last1.charAt(i) < last2.charAt(i)) {
				return -1;
			} else if (last1.charAt(i) > last2.charAt(i)) {
				return 1;
			}
		}
	
		// last name - extra char
		if (last1.length() < last2.length()) {
			return -1;
		} else if (last1.length() > last2.length()) {
			return 1;
		}
	
		// first name
		for (int i = 0; i < first1.length(); i++) {
			if (first1.charAt(i) < first2.charAt(i)) {
				return -1;
			} else if (first1.charAt(i) > first2.charAt(i)) {
				return 1;
			}
		}
	
		// first name - extra char
		if (first1.length() < first1.length()) {
			return -1;
		} else if (first1.length() > first1.length()) {
			return 1;
		}
	
		// id
		for (int i = 0; i < id1.length(); i++) {
			if (id1.charAt(i) < id2.charAt(i)) {
				return -1;
			} else if (id1.charAt(i) > id2.charAt(i)) {
				return 1;
			}
		}
	
		// id - extra char
		if (id1.length() < id2.length()) {
			return -1;
		} else if (id1.length() > id2.length()) {
			return 1;
		}
	
		return 0;
	}

	/**
	 * Returns a comma-separated value String of all Student fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
	}

	/**
	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
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
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
}
