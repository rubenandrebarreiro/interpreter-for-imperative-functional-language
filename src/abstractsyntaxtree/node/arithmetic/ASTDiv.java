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
 * Class for the a Node of an Abstract Syntax Tree, performing the Division of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTDiv implements ASTNode {

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
	 * - The Constructor of a Node of an Abstract Syntax Tree.
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTDiv(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescedant = leftASTNodeDescedant;
		this.rightASTNodeDescedant = rightASTNodeDescedant;
	}
	
	
	// Methods:
	/**
	 * Evaluates the current Node of an Abstract SyntaxTree, performing its Division.
	 */
	@Override
	public int eval(Environment env) {
		int leftASTNodeDescendantValue = leftASTNodeDescedant.eval(env);
		int rightASTNodeDescedantValue = rightASTNodeDescedant.eval(env);
		
		// Returns the Division of the A.S.T. Nodes Descendants
		return leftASTNodeDescendantValue / rightASTNodeDescedantValue;
	}
}