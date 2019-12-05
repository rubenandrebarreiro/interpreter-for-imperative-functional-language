package main.java.abstractsyntaxtree.node.logics;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.atomics.TBool;
import main.java.values.atomics.IValue;
import main.java.values.atomics.VBool;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Conjunction of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTAnd implements ASTNode {
	
	// Global Instance Variables:
	
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescendant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescendant;
	
	/**
	 * The TypeCheck Error Message for the A.S.T. Node for And
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to and (conjunction) operator!!!";
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.),
	 *   representing an AND operation.
	 * 
	 * @param leftASTNodeDescendant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescendant the right side Descendant of the A.S.T. Node
	 */
	public ASTAnd(ASTNode leftASTNodeDescendant, ASTNode rightASTNodeDescendant) {
		this.leftASTNodeDescendant = leftASTNodeDescendant;
		this.rightASTNodeDescendant = rightASTNodeDescendant;
	}
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 * performing its conjunction.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 *         performing its conjunction
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
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VBool && rightASTNodeDescedantValue instanceof VBool) {

			// Returns the Conjunction of the A.S.T. Nodes Descendants
			return new VBool( ((VBool) leftASTNodeDescendantValue).getValue() && ((VBool) rightASTNodeDescedantValue).getValue());
		}
		throw new TypeErrorException(TYPE_ERROR_MESSAGE);
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
						CodeBlockInstructionsSet codeBlockInstructionsSet)
										throws ASTInvalidIdentifierException {
		
		// To Perform the Conjunction of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Conjunction (iand) to the Execution Stack,
		// in order to perform the Conjunction of the 2 A.S.T. Nodes
		String instructionConjunction = String.format("iand");
		codeBlockInstructionsSet.addCodeInstruction(instructionConjunction);	
	}
	
	@Override
	public IType typecheck(Environment<IType> environment)
			throws TypeErrorException,
	   		       ASTInvalidIdentifierException,
	   		       ASTDuplicatedIdentifierException,
	   		       NumberArgumentsErrorException {
		
		IType leftASTNodeDescedantType = this.leftASTNodeDescendant.typecheck(environment);
		IType rightASTNodeDescedantType = this.rightASTNodeDescendant.typecheck(environment);
		
		// TODO
		if(leftASTNodeDescedantType instanceof TBool && rightASTNodeDescedantType instanceof TBool) {
			
			return new TBool();
		
		}
		else {
			
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		
		}
	}
}
