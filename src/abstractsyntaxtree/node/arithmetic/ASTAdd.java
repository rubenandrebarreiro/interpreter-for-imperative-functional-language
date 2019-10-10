package abstractsyntaxtree.node.arithmetic;

import abstractsyntaxtree.Environment;
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
import abstractsyntaxtree.node.ASTNode;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Addition of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTAdd implements ASTNode {
	
	// Global Variables:
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescedant;
	
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
	public ASTAdd(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescedant = leftASTNodeDescedant;
		this.rightASTNodeDescedant = rightASTNodeDescedant;
	}
	
	
	// Methods:
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside, performing its addition.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside, performing its addition        
	 */
	@Override
	public int eval(Environment environment) {
		int leftASTNodeDescendantValue = leftASTNodeDescedant.eval(environment);
		int rightASTNodeDescedantValue = rightASTNodeDescedant.eval(environment);
		
		// Returns the Addition of the A.S.T. Nodes Descendants
		return leftASTNodeDescendantValue + rightASTNodeDescedantValue;
	}
}