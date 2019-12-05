package main.java.abstractsyntaxtree.node.atomics;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.atomics.TNegativeInt;
import main.java.values.atomics.IValue;
import main.java.values.atomics.VNegativeInt;
import main.java.values.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree, representing an Atomic Negative Number.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTNegativeNum implements ASTNode {

	// Global Instance Variables:

	/**
	 * The VNegativeInt, representing an Atomic Negative Number
	 */
	private VNegativeInt negativeNumASTNodeValue;
		
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic Negative Number;
	 * 
	 * @param negativeNumASTNodeValue the value of the A.S.T. Node, representing an Atomic Negative Number
	 */
	public ASTNegativeNum(VNegativeInt negativeNumASTNodeValue) {
		this.negativeNumASTNodeValue = negativeNumASTNodeValue;
	}
	
	
	// Methods:

	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its Atomic Negative Number.
	 * 
	 * @param environment the Environment of the Scope/Frame, containing this A.S.T. Node (Negative Num)
	 */
	@Override
	public IValue eval(Environment<IValue> environment) {
		
		// Returns A.S.T. Node, representing an Atomic Negative Number
		return this.negativeNumASTNodeValue;
	}

	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * pushing an Atomic Negative Number to the Evaluation Stack.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet) {
		codeBlockInstructionsSet.addCodeInstruction("sipush " + String.valueOf(Math.abs(this.negativeNumASTNodeValue.getValue())));
		codeBlockInstructionsSet.addCodeInstruction("ineg");
	}


	@Override
	public IType typecheck(Environment<IType> environmentType) throws TypeErrorException {
		
		return new TNegativeInt();
	
	}
}