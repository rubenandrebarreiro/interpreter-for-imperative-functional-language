package abstractsyntaxtree.exceptions;

public class ASTInvalidIdentifierException extends Exception {

	/**
	 * The default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	// Constructors:
	/**
	 * Constructor #1:
	 * - The constructor of an A.S.T. Invalid Identifier Exception.
	 * 
	 * @param exceptionMessage
	 */
	public ASTInvalidIdentifierException(String exceptionMessage) {
		super(exceptionMessage);
	}
}