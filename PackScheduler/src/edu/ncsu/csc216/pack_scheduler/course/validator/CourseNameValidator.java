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
	public boolean isValid(String courseName) {
		currentState = stateInitial;
		
		int i = 0;
		while (i < courseName.length()) {
			char ch = courseName.charAt(i);
			
			if (Character.isLetter(ch)) {
				try {
					currentState.onLetter();
				} catch (InvalidTransitionException e) {
					throw new IllegalArgumentException("Should I change this?");
				}
			}
			
			else if (Character.isDigit(ch)) {
				try {
					currentState.onDigit();
				} catch (InvalidTransitionException e) {
					throw new IllegalArgumentException("Should I change this?");
				}			}
			
			else {
				try {
					currentState.onOther();
				} catch (InvalidTransitionException e) {
					throw new IllegalArgumentException("Should I change this?");
				}			}
			
			i++;
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
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * Controls behavior proceeding a letter character.
	 * 
	 * @author Will Greene
	 */
	public class LetterState extends State {
		
		/**
		 * Constructs ...
		 */
		private LetterState() {
			
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter() throws InvalidTransitionException {
			letterCount++;
			
			if (letterCount > 4) {
				throw new InvalidTransitionException("TODO - match with error message");
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
		
		/**
		 * Constructs ...
		 */
		private NumberState() {
			
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter () throws InvalidTransitionException {
			if (digitCount == 3) {
				currentState = stateSuffix;
			}
			
			else {
				throw new InvalidTransitionException("TODO - match with error message");
			}
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			digitCount++;
			
			if (digitCount > 3) {
				throw new InvalidTransitionException("TODO - match with error message");
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
			
		}
		
		/**
		 * Controls behavior for a letter character.
		 */
		@Override
		void onLetter() {
			validEndState = true;
		}
		
		/**
		 * Controls behavior for a digit character.
		 */
		@Override
		void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
}
