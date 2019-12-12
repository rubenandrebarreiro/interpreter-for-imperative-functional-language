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
import main.java.values.mathematics.VInt;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

public class ASTPostFixIncrement implements ASTNode {

	// Global Instance Variables:

	/**
	 * The VInt, representing an Atomic Number to be Post Fix Incremented
	 */
	private VInt numASTNodeValueToBeIncremented;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic Number
	 *   to be Post Fix Incremented;
	 * 
	 * @param numASTNodeValueToBeIncremented the value of the A.S.T. Node,
	 *        representing an Atomic Number to be Post Fix Incremented
	 */
	public ASTPostFixIncrement(VInt numASTNodeValueToBeIncremented) {
		
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

		// Returns A.S.T. Node, representing an Atomic Number PostFix Incremented
		VInt numASTNodeValueToBeIncremented = this.numASTNodeValueToBeIncremented;
		
		this.numASTNodeValueToBeIncremented = 
				new VInt(numASTNodeValueToBeIncremented.getValue() + 1);
		
		
		return numASTNodeValueToBeIncremented;
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		
		codeBlockInstructionsSet.addCodeInstruction
		("sipush " + String.valueOf(numASTNodeValueToBeIncremented.getValue()));
		
		codeBlockInstructionsSet.addCodeInstruction("iinc");
		
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTInvalidIdentifierException,
			NumberArgumentsErrorException, ASTDuplicatedIdentifierException {
		
		return new TInt();
	}

}
