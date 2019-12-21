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
 * The Class for the Type Error Exception, extending an Exception.
 */
public class TypeErrorException extends Exception {
	
	// Constants:
	
	/**
	 * The default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Type Error Exception;
	 * 
	 * @param exceptionMessage a Type Error Exception message to be showed
	 */
	public TypeErrorException(String exceptionMessage) {
		super(exceptionMessage);
	}
}