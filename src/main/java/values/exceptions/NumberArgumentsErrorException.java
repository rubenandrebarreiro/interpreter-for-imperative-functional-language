package main.java.values.exceptions;

public class NumberArgumentsErrorException extends Exception {
	
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