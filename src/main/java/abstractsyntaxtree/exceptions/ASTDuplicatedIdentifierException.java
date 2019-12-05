package main.java.abstractsyntaxtree.exceptions;

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

public class ASTDuplicatedIdentifierException extends Exception {

	/**
	 * The default serial version UID
	 */
	private static final long serialVersionUID = 1L;

	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The constructor of an A.S.T. Duplicated Identifier Exception.
	 * 
	 * @param exceptionMessage an Exception Message for A.S.T. Duplicated Identifier
	 */
	public ASTDuplicatedIdentifierException(String exceptionMessage) {
		super(exceptionMessage);
	}
	
}
