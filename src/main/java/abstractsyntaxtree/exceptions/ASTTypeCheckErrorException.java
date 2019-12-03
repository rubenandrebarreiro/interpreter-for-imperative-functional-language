package main.java.abstractsyntaxtree.exceptions;

public class ASTTypeCheckErrorException extends Exception{

	// Constructors:
	/**
	 * Constructor #1:
	 * - The constructor of an A.S.T. Typecheck Error.
	 * 
	 * @param exceptionMessage
	 */
	public ASTTypeCheckErrorException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
}
