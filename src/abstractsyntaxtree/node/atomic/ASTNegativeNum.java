package abstractsyntaxtree.node.atomic;

import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;

/**
 * Class for the Node of an Abstract Syntax Tree, representing an atomic negative number.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTNegativeNum implements ASTNode {

	// Global Variables:
	/**
	 * The A.S.T. Node, representing an atomic negative number
	 */
	private int numASTNodeValue;
		
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree.
	 * 
	 * @param numASTNodeValue the value of the A.S.T. Node, representing an atomic negative number
	 */
	public ASTNegativeNum(int numASTNodeValue) {
		this.numASTNodeValue = numASTNodeValue;
	}
	
	
	// Methods:
	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its atomic negative number.
	 */
	@Override
	public int eval(Environment env) {
		
		// Returns A.S.T. Node, representing an atomic negative number
		return (-1) * this.numASTNodeValue;
	}
}