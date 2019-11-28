package main.java.abstractsyntaxtree.node.arithmetics;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomics.IValue;
import main.java.values.atomics.VInt;
import main.java.values.atomics.VNegativeInt;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Multiplication of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTMult implements ASTNode {

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
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTMult(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescendant = leftASTNodeDescedant;
		this.rightASTNodeDescendant = rightASTNodeDescedant;
	}
	
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside, performing its multiplication.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside, performing its multiplication
	 *  
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Heap Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 * 
	 * @throws NumberArgumentsErrorException 
	 */
	@Override
	public IValue<Integer> eval(Environment<?> environment) throws
		   ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue<?> leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue<?> rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VInt && rightASTNodeDescedantValue instanceof VInt) {

			// Returns the Multiplication of the A.S.T. Nodes Positive Descendants
			int mulResult = ((VInt) leftASTNodeDescendantValue).getValue() 
				          * ((VInt) rightASTNodeDescedantValue).getValue();
			
			return new VInt(mulResult);
		}
		
		if(leftASTNodeDescendantValue instanceof VNegativeInt && rightASTNodeDescedantValue instanceof VInt) {

			// Returns the Multiplication of the A.S.T. Nodes Positive/Negative Descendants
			int mulResult = ((VNegativeInt) leftASTNodeDescendantValue).getValue() 
					      * ((VInt) rightASTNodeDescedantValue).getValue();
			
			return new VNegativeInt(Math.abs(mulResult));
		}
		
		if(leftASTNodeDescendantValue instanceof VInt && rightASTNodeDescedantValue instanceof VNegativeInt) {

			// Returns the Multiplication of the A.S.T. Nodes Positive/Negative Descendants
			int mulResult = ((VInt) leftASTNodeDescendantValue).getValue() 
					      * ((VNegativeInt) rightASTNodeDescedantValue).getValue();
			
			return new VNegativeInt(Math.abs(mulResult));
		}
		
		if(leftASTNodeDescendantValue instanceof VNegativeInt && rightASTNodeDescedantValue instanceof VNegativeInt) {

			// Returns the Multiplication of the A.S.T. Nodes Negative Descendants
			int mulResult = ((VNegativeInt) leftASTNodeDescendantValue).getValue() 
					      * ((VNegativeInt) rightASTNodeDescedantValue).getValue();
			
			return new VInt(mulResult);
		}
		
		throw new TypeErrorException("Illegal arguments to * (multiplication) operator!!!");
	}


	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform a Multiplication in the Evaluation Stack.
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
		
		// To Perform the Multiplication of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Multiplication (imul) to the Execution Stack,
		// in order to perform the Multiplication of the 2 A.S.T. Nodes
		String instructionAddition = String.format("imul");
		codeBlockInstructionsSet.addCodeInstruction(instructionAddition);
	}
}