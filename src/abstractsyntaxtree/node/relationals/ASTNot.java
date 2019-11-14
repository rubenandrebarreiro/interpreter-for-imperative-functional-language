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
 * performing the Negation of its descendant.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTNot implements ASTNode {
	
	// Global Instance Variables:
	
	/**
	 * The A.S.T. Node descendant
	 */
	private ASTNode astNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.),
	 *   representing a NOT operation.
	 * 
	 * @param astNodeDescedant the left side Descendant of the A.S.T. Node
	 * 
	 */
	public ASTNot(ASTNode astNodeDescendant) {
		this.astNodeDescendant = astNodeDescendant;
	}
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 * performing its exclusive disjunction.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 *         performing its exclusive disjunction
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
		IValue<?> astNodeDescendantValue = astNodeDescendant.eval(environment);
		
		if(astNodeDescendantValue instanceof VBool) {

			// Returns the Exclusive Disjunction of the A.S.T. Nodes Descendants
			return new VBool( ((VBool) astNodeDescendantValue).getValue() ^ true);
		}
		throw new TypeErrorException("Illegal arguments to xor (exclusive disjunction) operator!!!");
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
						CodeBlockInstructionsSet codeBlockInstructionsSet)
										throws ASTInvalidIdentifierException {
		
		// To Perform the Exclusive Disjunction of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.astNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Atomic Number 1 to the Execution Stack,
		// in order to perform the Negation of the A.S.T. Node
		codeBlockInstructionsSet.addCodeInstruction("sipush " + String.valueOf(1));
		
		// Push the Code Instruction of Exclusive Disjunction (ixor) to the Execution Stack,
		// in order to perform the Negation of the A.S.T. Node
		String instructionExclusiveDisjunction = String.format("ixor");
		codeBlockInstructionsSet.addCodeInstruction(instructionExclusiveDisjunction);	
	}
}