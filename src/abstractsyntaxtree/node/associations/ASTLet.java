package abstractsyntaxtree.node.associations;

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the association of its descendants {ID -> Value}.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTLet implements ASTNode {
	
	// Global Variables:
	/**
	 * The left A.S.T. Node descendant
	 */
	private String leftASTNodeDescedant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescedant;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTLet(String leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		
		// The left side Descendant of the A.S.T. Node
		// will be the name/identifier of the expression
		this.leftASTNodeDescedant = leftASTNodeDescedant;
		
		// The left side Descendant of the A.S.T. Node
		// will be the value of the expression
		this.rightASTNodeDescedant = rightASTNodeDescedant;
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
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes) 
	 */
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		
		// Stars the Scope (Environment) of the declared expression
		environment.beginScope();
		
		// The evaluation of the right side Descendant
		int expressionEvaluated = rightASTNodeDescedant.eval(environment);
		
		try {
			// The addition of the association between the left side Descendant
			// and the expression evaluated of the right side
			environment.addAssoc(leftASTNodeDescedant, expressionEvaluated);
		}
		catch (ASTInvalidIdentifierException astInvalidIdentifierException) {
			astInvalidIdentifierException.printStackTrace();
		}

		// Ends the Scope (Environment) of the previously declared expression
		environment.endScope();
		
		// Returns the expression evaluated
		return expressionEvaluated;
	}
}