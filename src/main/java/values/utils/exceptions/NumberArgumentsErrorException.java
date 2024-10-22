package main.java.values.utils.exceptions;

/**
 * Interpreter for Imperative/Functional Language
 * 
 * Interpretation and Compilation of Programming Languages
 * 
 * Faculty of Science and Technology of New University of Lisbon
 * (FCT NOVA | FCT/UNL)
 * 
 * Integrated Master of Computer Science and Engineering
 * (BSc. + MSc. Bologna Degree)
 * 
 * Academic Year 2019/2020
 * 
 */

/**
 * The Class for the Number Arguments Error Exception, extending an Exception.
 */
public class NumberArgumentsErrorException extends Exception {
	
	// Constants:
	
	/**
	 * The default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Number Arguments Error Exception;
	 * 
	 * @param exceptionMessage a Number Arguments Error Exception message to be showed
	 */
	public NumberArgumentsErrorException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
}