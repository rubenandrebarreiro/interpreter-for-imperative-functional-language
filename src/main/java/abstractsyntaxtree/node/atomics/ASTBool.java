package main.java.abstractsyntaxtree.node.atomics;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.atomic.VBool;

/**
 * Class for the Node of an Abstract Syntax Tree, representing an Atomic Number.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTBool implements ASTNode {
	
	// Global Instance Variables:

	/**
	 * The A.S.T. Node, representing an Atomic Boolean
	 */
	private VBool boolASTNodeValue;
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic Boolean.
	 * 
	 * @param boolASTNodeValue the value of the A.S.T. Node, representing an Atomic Boolean
	 */
	public ASTBool(VBool boolASTNodeValue) {
		this.boolASTNodeValue = boolASTNodeValue;
	}
	
	
	// Methods:

	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its Atomic Boolean.
	 * 
	 * @param environment the Environment of the Scope/Frame, containing this A.S.T. Node (Bool)
	 */
	@Override
	public IValue<Boolean> eval(Environment environment) {
		
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
		int heapStackFrameJVMBooleanRepresentation = this.boolASTNodeValue.getValue() ? 1 : 0;
		
		codeBlockInstructionsSet.addCodeInstruction("sipush " + String.valueOf(heapStackFrameJVMBooleanRepresentation));
	}
}