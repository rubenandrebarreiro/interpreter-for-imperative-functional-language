package abstractsyntaxtree.node.associations;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the keeping of an identifier for an expression.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTID implements ASTNode {
	
	// Global Variables:
	/**
	 * The Expression's Identifier
	 */
	private String expressionID;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTID(String expressionID) {
		this.expressionID = expressionID;
	}
	
	
	// Methods:
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside, performing its association.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside, performing its association        
	 * @throws ASTInvalidIdentifierException 
	 */
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		
		// Returns the value associated to the Expression's ID,
		// in the current Environment (Scope)
		return environment.find(this.expressionID);
	}
}