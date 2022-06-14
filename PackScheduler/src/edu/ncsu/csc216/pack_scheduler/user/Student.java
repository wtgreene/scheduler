package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Assembles information for a Student
 * 
 * @author Will Greene
 */
public class Student implements Comparable<Student> {

	/** minimum number of credits for a student */
	public static final int MIN_CREDITS = 3;
	/** maximum number of credits for a student */
	public static final int MAX_CREDITS = 18;

	/** student's first name */
	private String firstName;
	/** student's last name */
	private String lastName;
	/** student's unity id */
	private String id;
	/** student's email */
	private String email;
	/** student's password */
	private String password;
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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
	 * Returns the Student's first name.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set's the Student's first name. If the first name is null or an empty string,
	 * an IllegalArgumentException is thrown.
	 * 
	 * @param firstName Student's first name
	 * @throws IllegalArgumentException if the firstName parameter is invalid
	 */
	public void setFirstName(String firstName) {

		// throw exception if null or empty string
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}

		this.firstName = firstName;
	}

	/**
	 * Returns the Student's last name.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the Student's last name. If the last name is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param lastName the Student's last name.
	 * @throws IllegalArgumentException if the lastName parameter is invalid
	 */
	public void setLastName(String lastName) {

		// throw exception if null or empty string
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}

		this.lastName = lastName;
	}

	/**
	 * Returns the Student's unity id.
	 * 
	 * @return the unity id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the Student's unity id. If the unity id is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param id Student's unity id
	 * @throws IllegalArgumentException if id parameter is invalid
	 */
	private void setId(String id) {

		// throw exception if null or empty string
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}

		this.id = id;
	}

	/**
	 * Returns the Student's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the Student's email. If the email is null, an empty string, does not
	 * contain a '@' or '.' character, or if the last '.' character is earlier than
	 * the first '@' character, an IllegalArgumentException is thrown.
	 * 
	 * @param email Student's email
	 * @throws IllegalArgumentException if email parameter is invalid
	 */
	public void setEmail(String email) {

		// throw exception if null or empty string
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}

		// throw exception if no "@" or "."
		if (email.indexOf('@') == -1 || email.indexOf('.') == -1) {
			throw new IllegalArgumentException("Invalid email");
		}

		// throw exception if last ‘.’ character in the email string
		// is earlier than the index of the first ‘@’ character
		if (email.lastIndexOf('.') < email.indexOf('@')) {
			throw new IllegalArgumentException("Invalid email");
		}

		this.email = email;
	}

	/**
	 * Returns the Student's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Student's password. If the password is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param password Student's password
	 * @throws IllegalArgumentException if password parameter is invalid
	 */
	public void setPassword(String password) {

		// throw exception if null or empty string
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}

		this.password = password;
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
	
		String last1 = lastName.toLowerCase();
		String last2 = o.lastName.toLowerCase();
		String first1 = firstName.toLowerCase();
		String first2 = o.firstName.toLowerCase();
		String id1 = id.toLowerCase();
		String id2 = o.id.toLowerCase();
	
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
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (obj == null)
//			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& maxCredits == other.maxCredits && Objects.equals(password, other.password);
	}

	/**
	 * Returns a comma-separated value String of all Student fields.
	 * 
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + password + "," + maxCredits;
	}

	/**
	 * Generates a hashCode for Student using all fields.
	 * 
	 * @return hashCode for Student
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, maxCredits, password);
	}
}
