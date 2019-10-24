package abstractsyntaxtree.node.arithmetic;

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

import java.util.List;
import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Division of its descendants.
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
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
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
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside, performing its division.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside, performing its division        
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes) 
	 */
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		int leftASTNodeDescendantValue = leftASTNodeDescedant.eval(environment);
		int rightASTNodeDescedantValue = rightASTNodeDescedant.eval(environment);
		
		// Returns the Division of the A.S.T. Nodes Descendants
		return leftASTNodeDescendantValue / rightASTNodeDescedantValue;
	}

	/**
	 * 
	 */
	@Override
	public void compile(Environment environment, List<String> codeInstructions) {
		
		// To Perform the Division of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescedant.compile(environment, codeInstructions);
		this.rightASTNodeDescedant.compile(environment, codeInstructions);
		
		// Push the Code Instruction of Division (idiv) to the Execution Stack,
		// in order to perform the Division of the 2 A.S.T. Nodes
		String instructionAddition = String.format("idiv");
		codeInstructions.add(instructionAddition);
	}
}