package main.java.abstractsyntaxtree.node.atomics;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomics.IValue;
import main.java.values.atomics.VBool;

/**
 * Class for the Node of an Abstract Syntax Tree, representing a Negated Atomic Boolean.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTNotBool implements ASTNode {
	
	// Global Instance Variables:

	/**
	 * The value of the A.S.T. Node, representing a Negated Atomic Boolean
	 */
	private VBool boolASTNodeValue;
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent a Negated Atomic Boolean.
	 * 
	 * @param boolASTNodeValue the value of the A.S.T. Node, representing a Negated Atomic Boolean
	 */
	public ASTNotBool(VBool boolASTNodeValue) {
		this.boolASTNodeValue = new VBool(!boolASTNodeValue.getValue());
	}
	
	
	// Methods:

	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its Negated Atomic Boolean.
	 * 
	 * @param environment the Environment of the Scope/Frame, containing this A.S.T. Node (Bool)
	 */
	@Override
	public IValue<?> eval(Environment<?> environment) {
		
		// Returns A.S.T. Node, representing an Atomic Boolean
		return this.boolASTNodeValue;
	}
	
	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * pushing an Atomic Boolean to the Evaluation Stack.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet) {
		int heapStackFrameJVMBooleanRepresentation = !this.boolASTNodeValue.getValue() ? 1 : 0;
		
		codeBlockInstructionsSet.addCodeInstruction("sipush " + String.valueOf(heapStackFrameJVMBooleanRepresentation));

		// Push the Code Instruction of Atomic Number 1 (One) to the Execution Stack,
		// in order to perform the Negation of the A.S.T. Node
		String instructionAtomicNumberOne = "sipush " + String.valueOf(1);
		codeBlockInstructionsSet.addCodeInstruction(instructionAtomicNumberOne);
		
		// Push the Code Instruction of Exclusive Disjunction (ixor) to the Execution Stack,
		// in order to perform the Negation of the A.S.T. Node
		String instructionExclusiveDisjunction = String.format("ixor");
		codeBlockInstructionsSet.addCodeInstruction(instructionExclusiveDisjunction);
	}
}