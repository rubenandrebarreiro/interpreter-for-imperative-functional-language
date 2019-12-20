package main.java.values.utils.exceptions;

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
	 * - The constructor of a Number Arguments Error Exception.
	 * 
	 * @param exceptionMessage
	 */
	public NumberArgumentsErrorException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
}