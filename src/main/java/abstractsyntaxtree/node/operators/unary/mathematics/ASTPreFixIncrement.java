package main.java.abstractsyntaxtree.node.operators.unary.mathematics;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.mathematics.TInt;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;
import main.java.values.mathematics.VInt;

public class ASTPreFixIncrement implements ASTNode {

	// Global Instance Variables:

	/**
	 * The VInt, representing an Atomic Number to be Pre Fix Incremented
	 */
	private VInt numASTNodeValueToBeIncremented;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic Number
	 *   to be Pre Fix Incremented;
	 * 
	 * @param numASTNodeValueToBeIncremented the value of the A.S.T. Node,
	 *        representing an Atomic Number to be Pre Fix Incremented
	 */
	public ASTPreFixIncrement(VInt numASTNodeValueToBeIncremented) {
		
		this.numASTNodeValueToBeIncremented = numASTNodeValueToBeIncremented;
		
	}
	
	
	// Methods:

	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its Atomic Number.
	 * 
	 * @param environment the Environment of the Scope/Frame, containing this A.S.T. Node (Num)
	 */
	@Override
	public IValue eval(Environment<IValue> environment)
			throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {

		// Returns A.S.T. Node, representing an Atomic Number PreFix Decremented
		
		this.numASTNodeValueToBeIncremented = 
				new VInt(numASTNodeValueToBeIncremented.getValue() - 1);
		
		
		return numASTNodeValueToBeIncremented;
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		
		codeBlockInstructionsSet.addCodeInstruction
		("sipush " + String.valueOf(numASTNodeValueToBeIncremented.getValue()));
		
		codeBlockInstructionsSet.addCodeInstruction
		("sipush " + String.valueOf(1));
		
		codeBlockInstructionsSet.addCodeInstruction("iadd");
		
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTInvalidIdentifierException,
			NumberArgumentsErrorException, ASTDuplicatedIdentifierException {
		
		return new TInt();
	}

}
