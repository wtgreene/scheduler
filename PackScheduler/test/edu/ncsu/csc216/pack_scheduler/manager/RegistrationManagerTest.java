package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests RegistrationManager.java
 * 
 * @author Unnamed Co-Worker
 */
public class RegistrationManagerTest {
	
	/** RegistrationManager instance */
	private RegistrationManager manager;
	
	/** registrar properties file */
	private static final String PROP_FILE = "registrar.properties";
	
	/** student's first name */
	private static final String FIRST_NAME = "firstName";
	/** student's last name */
	private static final String LAST_NAME = "lastName";
	/** student's unity id */
	private static final String ID = "id";
	/** student's email */
	private static final String EMAIL = "email@ncsu.edu";
	/** student's password */
	private static final String PASSWORD = "password";
	/** student's max credits */
	private static final int MAX_CREDITS = 15;
	
	/** 2nd student's first name */
	private static final String FIRST_NAME_2 = "firstName2";
	/** 2nd student's last name */
	private static final String LAST_NAME_2 = "lastName2";
	/** 2nd student's unity id */
	private static final String ID_2 = "id2";
	/** 2nd student's email */
	private static final String EMAIL_2 = "email2@ncsu.edu";
	/** 2nd student's password */
	private static final String PASSWORD_2 = "password2";
	/** 2nd student's max credits */
	private static final int MAX_CREDITS_2 = 16;
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests RegistrationManager.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		manager = RegistrationManager.getInstance();
		assertDoesNotThrow(() -> manager.getCourseCatalog());
	}

	/**
	 * Tests RegistrationManager.getStudentDirectory().
	 */
	@Test
	public void testGetStudentDirectory() {
		manager = RegistrationManager.getInstance();
		assertDoesNotThrow(() -> manager.getStudentDirectory());
	}

	/**
	 * Tests RegistrationManager.login().
	 */
	@Test
	public void testLogin() {
		manager = RegistrationManager.getInstance();
				
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
		
		assertTrue(manager.login(prop.getProperty("id"), prop.getProperty("pw")));
		
		// new - beg
		manager.getStudentDirectory().addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		manager.getStudentDirectory().addStudent(FIRST_NAME_2, LAST_NAME_2, ID_2, EMAIL_2, PASSWORD_2, PASSWORD_2, MAX_CREDITS_2);
		assertTrue(manager.login(ID, PASSWORD));
		assertFalse(manager.login(ID_2, PASSWORD_2));
		// new - end
	}

	/**
	 * Tests RegistrationManager.logout().
	 */
	@Test
	public void testLogout() {
		manager = RegistrationManager.getInstance();
		assertDoesNotThrow(() -> manager.logout());
	}

	/**
	 * Tests RegistrationManager.getCurrentUser().
	 */
	@Test
	public void testGetCurrentUser() {
		manager = RegistrationManager.getInstance();
		assertDoesNotThrow(() -> manager.getCurrentUser());
	}
}