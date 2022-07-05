package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

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
	}
	
	/**
	 * Creates a registrar.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
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
	 * Logic for the login process.
	 * 
	 * @param id User unity id
	 * @param password User password
	 * @return true if able to login, false if not
	 */
	public boolean login(String id, String password) {
		if (id != null) {
			String localId = id;
			String localHashPW;
			
			if (password != null) {
				String localPW = password;
				localHashPW = hashPW(localPW);

				if (registrar.getId().equals(localId) && currentUser == null) {
					String registrarPW = registrar.getPassword();

					if (registrarPW.equals(localHashPW)) {
						currentUser = registrar;
						localId = "";
						return true;
					}
				}

				Student s = studentDirectory.getStudentById(id);

				if (s.getPassword().equals(localHashPW) && (currentUser == null || currentUser.getId().equals(registrar.getId()))) {
					currentUser = s;
					return true;
				}
			}
		}
		
		return false;
	}

	/**
	 * Logs a User out.
	 */
	public void logout() {
		currentUser = registrar; 
	} 
	
	/**
	 * Returns the current user.
	 * @return the current user
	 */
	public User getCurrentUser() {
		return currentUser;
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
		 * @param lastName Registrar last name
		 * @param id Registrar id
		 * @param email Registrar email
		 * @param hashPW Registrar hash password
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}