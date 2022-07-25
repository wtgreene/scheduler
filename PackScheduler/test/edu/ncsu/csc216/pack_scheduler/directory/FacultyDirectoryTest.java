package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Tests FacultyDirectory.java
 * 
 * @author Sarah Heckman
 * @author Will Greene
 */
public class FacultyDirectoryTest {

	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	
	/** Faculty first name */
	private static final String FIRST_NAME = "Fac";
	/** Faculty last name */
	private static final String LAST_NAME = "Ulty";
	/** Faculty id */
	private static final String ID = "fulty";
	/** Faculty email */
	private static final String EMAIL = "fulty@ncsu.edu";
	/** Faculty password */
	private static final String PASSWORD = "pw";
	/** Faculty max courses */
	private static final int MAX_COURSES = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fd = new FacultyDirectory();
		assertFalse(fd.removeFaculty("awitt"));
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are Faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory fd = new FacultyDirectory();

		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		fd.newFacultyDirectory();
		assertEquals(0, fd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid file
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);

		// Test invalid file
		Exception e = assertThrows(IllegalArgumentException.class, () -> fd.loadFacultyFromFile("not valid"));
		assertEquals("Unable to read file not valid", e.getMessage());

	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Test valid Faculty
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);

		// Adding new tests ...

		// PW
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> fd.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", e1.getMessage());
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add Faculty and remove
		fd.loadFacultyFromFile(validTestFile);
		assertEquals(8, fd.getFacultyDirectory().length);
		assertTrue(fd.removeFaculty("awitt"));
		String[][] facultyDirectory = fd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Fiona", facultyDirectory[0][0]);
		assertEquals("Meadows", facultyDirectory[0][1]);
		assertEquals("fmeadow", facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory fd = new FacultyDirectory();

		// Add a Faculty
		fd.addFaculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 2);
		assertEquals(1, fd.getFacultyDirectory().length);
		fd.saveFacultyDirectory("test-files/sample_faculty_records.txt");
		checkFiles("test-files/sample_faculty_records.txt", "test-files/sample_expected_faculty_records.txt");
	}

	/**
	 * Helper method to compare two files for the same contents
	 * 
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests.FacultyDirectory.getFacultyById()
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory fd = new FacultyDirectory();
		fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		
		assertEquals(fd.getFacultyById(ID).getId(), ID);
	}
}
