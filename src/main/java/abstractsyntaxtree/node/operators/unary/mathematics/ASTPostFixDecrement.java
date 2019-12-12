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


public class ASTPostFixDecrement implements ASTNode {

	// Global Instance Variables:

	/**
	 * The value of the A.S.T. Node, representing an Atomic Number to be Post Fix Decremented
	 */
	private VInt numASTNodeValueToBeDecremented;
	
	/**
	 * The type of the A.S.T. Node, representing an Atomic Number to be Post Fix Decremented
	 */
	private TInt numASTNodeTypeToBeDecremented;
	
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic Number
	 *   to be Post Fix Decremented;
	 * 
	 * @param numASTNodeValueToBeDecremented the value of the A.S.T. Node,
	 *        representing an Atomic Number to be Post Fix Decremented
	 */
	public ASTPostFixDecrement(VInt numASTNodeValueToBeDecremented) {
		
		this.numASTNodeValueToBeDecremented = numASTNodeValueToBeDecremented;
		
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

		// Returns A.S.T. Node, representing an Atomic Number PostFix Decremented
		VInt evaluatedNumASTNodeValueNotDecremented = this.numASTNodeValueToBeDecremented;
		
		this.numASTNodeValueToBeDecremented = 
				new VInt(numASTNodeValueToBeDecremented.getValue() - 1);
		
		
		return evaluatedNumASTNodeValueNotDecremented;
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		
		codeBlockInstructionsSet.addCodeInstruction
		("sipush " + String.valueOf(numASTNodeValueToBeDecremented.getValue()));
		
		codeBlockInstructionsSet.addCodeInstruction
		("sipush " + String.valueOf(1));
		
		codeBlockInstructionsSet.addCodeInstruction("isub");
		
	}

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTInvalidIdentifierException,
			NumberArgumentsErrorException, ASTDuplicatedIdentifierException {
		
		this.numASTNodeTypeToBeDecremented = TInt.getSingletonInstance();
		
		return this.numASTNodeTypeToBeDecremented;
		
	}
	
}
