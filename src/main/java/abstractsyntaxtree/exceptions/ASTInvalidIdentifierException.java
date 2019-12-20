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

/**
 * Exception Class for the Invalid Identifier Exception of an Abstract Syntax Tree.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTInvalidIdentifierException extends Exception {

	// Constants:
	
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