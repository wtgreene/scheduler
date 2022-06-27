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
		
		assertThrows(IllegalArgumentException.class, () -> manager.login("notalogin", "badpassword"));
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