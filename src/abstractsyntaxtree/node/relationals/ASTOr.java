package abstractsyntaxtree.node.relationals;

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

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;
import abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import values.atomic.IValue;
import values.atomic.VBool;
import values.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Disjunction of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTOr implements ASTNode {
	
	// Global Instance Variables:
	
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescendant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.),
	 *   representing an OR operation.
	 * 
	 * @param leftASTNodeDescendant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescendant the right side Descendant of the A.S.T. Node
	 */
	public ASTOr(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescendant = leftASTNodeDescedant;
		this.rightASTNodeDescendant = rightASTNodeDescedant;
	}
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 * performing its disjunction.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 *         performing its disjunction
	 *  
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Heap Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 */
	@Override
	public IValue<?> eval(Environment environment) throws ASTInvalidIdentifierException, TypeErrorException {
		IValue<?> leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue<?> rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VBool && rightASTNodeDescedantValue instanceof VBool) {

			// Returns the Disjunction of the A.S.T. Nodes Descendants
			return new VBool( ((VBool) leftASTNodeDescendantValue).getValue() || ((VBool) rightASTNodeDescedantValue).getValue());
		}
		throw new TypeErrorException("Illegal arguments to or (disjunction) operator!!!");
	}

	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform an Disjunction in the Evaluation Stack.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
						CodeBlockInstructionsSet codeBlockInstructionsSet)
										throws ASTInvalidIdentifierException {
		
		// To Perform the Disjunction of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Disjunction (ior) to the Execution Stack,
		// in order to perform the Disjunction of the 2 A.S.T. Nodes
		String instructionDisjunction = String.format("ior");
		codeBlockInstructionsSet.addCodeInstruction(instructionDisjunction);
		
	}
}