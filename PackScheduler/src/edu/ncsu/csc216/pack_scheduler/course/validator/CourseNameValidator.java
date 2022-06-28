/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Determines whether a Course's name is valid.
 * 
 * @author Will Greene
 */
public class CourseNameValidator {
	
	/** indicates whether Course name is valid */
	private boolean validEndState;
	/** Course name letter count */
	private int letterCount = 0;
	/** Course name digit count */
	private int digitCount = 0;
	/** current FSM state */
	private State currentState;
	/** FSM initial state */
	private final State stateInitial = new InitialState();
	/** FSM letter state */
	private final State stateLetter = new LetterState();
	/** FSM number state */
	private final State stateNumber = new NumberState();
	/** FSM suffix state */
	private final State stateSuffix = new SuffixState();	
	
	
	/**
	 * Determines whether a Course's name is valid. Calls the abstract State class, 
	 * and its concrete classes.
	 * 
	 * @param courseName Course name
	 * @return true if valid, false if not
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		validEndState = false;
		currentState = stateInitial;
		
		int i = 0;
		while (i < courseName.length()) {
			
			char ch = courseName.charAt(i);
			
			if (Character.isLetter(ch)) {
				currentState.onLetter();
			} else if (Character.isDigit(ch)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
			
			i++;
		}
		
		if (letterCount < 1 || letterCount > 4 || digitCount != 3) {
			throw new InvalidTransitionException();
		}
		
		return validEndState;
	}
	
	/**
	 * Controls behavior for a specific character in a Course name.
	 * 
	 * @author Will Greene
	 */
	public abstract class State {
		
		/**
		 * Controls behavior for a letter.
		 */
		abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Controls behavior for a digit / number.
		 */
		abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Controls behavior for a non alphanumeric character.
		 * 
		 * @throws InvalidTransitionException character is not a letter or digit
		 */
		void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Controls behavior at the beginning of a Course name.
	 * 
	 * @author Will Greene
	 */
	public class InitialState extends State {
		
		/**
		 * Constructs ...
		 */
		private InitialState() {
			super();
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter() {
			letterCount++;
			currentState = stateLetter;
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * Controls behavior proceeding a letter character.
	 * 
	 * @author Will Greene
	 */
	public class LetterState extends State {
		
		/** maximum number of prefix letters */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/**
		 * Constructs ...
		 */
		private LetterState() {
			super();
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			letterCount++;
			
			if (letterCount > MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}
	}
	
	/**
	 * Controls behavior proceeding a digit character.
	 * 
	 * @author Will Greene
	 */
	public class NumberState extends State {
		
		/** number of characters in a Course number */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/**
		 * Constructs ...
		 */
		private NumberState() {
			super();
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter () throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = stateSuffix;
			}
			
			else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			digitCount++;
			
			if (digitCount == COURSE_NUMBER_LENGTH && letterCount >= 1) {
				validEndState = true;
			}
			
			if (digitCount > COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
	
	/**
	 * Controls behavior proceeding a suffix letter.
	 * 
	 * @author Will Greene
	 */
	public class SuffixState extends State {
		
		/**
		 * Constructs ...
		 */
		private SuffixState() {
			super();
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
