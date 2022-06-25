package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Assembles information for a User.
 */
public abstract class User {

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

	/**
	 * Constructs a User.
	 * 
	 * @param firstName User's first name
	 * @param lastName User's last name
	 * @param id User's unity id
	 * @param email User's email
	 * @param password User's password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
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
	protected void setId(String id) {
	
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
	 * Generates a hashCode for User using all fields.
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}
}