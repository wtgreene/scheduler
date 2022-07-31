package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Controls the registration process.
 * 
 * @author Unnamed Co-Worker
 */
public class RegistrationManager {

	/** instance of the registration manager */
	private static RegistrationManager instance;
	/** course catalog */
	private CourseCatalog courseCatalog;
	/** student directory */
	private StudentDirectory studentDirectory;
	/** faculty directory */
	private FacultyDirectory facultyDirectory;
	/** registrar user */
	private User registrar;
	/** current user */
	private User currentUser;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** registrar properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructs a registration manager.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
	}

	/**
	 * Creates a registrar.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Returns a hashed password.
	 * 
	 * @param pw password
	 * @return a hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the instance of the RegistrationManager.
	 * 
	 * @return the instance of the RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the course catalog.
	 * 
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the student directory.
	 * 
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Returns the faculty directory.
	 * 
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Logic for the login process.
	 * 
	 * @param id       User unity id
	 * @param password User password
	 * @return true if able to login, false if not
	 */
	public boolean login(String id, String password) {

		// registrar
		if (registrar.getId().equals(id) && registrar.getPassword().equals(hashPW(password)) && currentUser == null) {

			currentUser = registrar;
			return true;
		}

		// student
		if (studentDirectory.getStudentById(id) != null) {

			Student s = studentDirectory.getStudentById(id);

			if (s.getPassword().equals(hashPW(password))
					&& (currentUser == null || currentUser.getId().equals(registrar.getId()))) {
				currentUser = s;
				return true;
			}
		}

		// faculty
		if (facultyDirectory.getFacultyById(id) != null) {

			Faculty f = facultyDirectory.getFacultyById(id);

			if (f.getPassword().equals(hashPW(password))
					&& (currentUser == null || currentUser.getId().equals(registrar.getId()))) {
				currentUser = f;
				return true;
			}
		}

		return false;
	}

	/**
	 * Logs a User out.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns the current user.
	 * 
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
//	    if (currentUser == null || !(currentUser instanceof Student)) {
		if (/* currentUser == null || */ !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
//	    if (currentUser == null || !(currentUser instanceof Student)) {
		if (/* currentUser == null || */ !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Adds / attaches a Faculty to a Course.
	 * 
	 * @param c Course to add Faculty to
	 * @param f Faculty to add
	 * @return whether the Faculty was added
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {

		if (currentUser != null && currentUser.equals(registrar)) {
			f.getSchedule().addCourseToSchedule(c);
			return true;
		}
		
		return false;
	}

	/**
	 * Removes a Faculty from a Course.
	 * 
	 * @param c Course to remove Faculty from
	 * @param f Faculty to remove Course from
	 * @return whether the Faculty was removed
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {

		if (currentUser != null && currentUser.equals(registrar)) {
			f.getSchedule().removeCourseFromSchedule(c);
			return true;
		}
		
		return false;
	}

	/**
	 * Resets a Faculty's Schedule.
	 * 
	 * @param f Faculty to reset Schedule
	 */
	public void resetFacultySchedule(Faculty f) {

		if (currentUser != null && currentUser.equals(registrar)) {
			f.getSchedule().resetSchedule();
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
//	    if (currentUser == null || !(currentUser instanceof Student)) {
		if (/* currentUser == null || */ !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Clears all data.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * Registrar class
	 * 
	 * @author Co-Worker
	 */
	private static class Registrar extends User {

		/**
		 * Create a registrar user.
		 * 
		 * @param firstName Registrar first name
		 * @param lastName  Registrar last name
		 * @param id        Registrar id
		 * @param email     Registrar email
		 * @param hashPW    Registrar hash password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}