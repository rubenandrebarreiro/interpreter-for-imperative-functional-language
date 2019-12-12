package main.java.abstractsyntaxtree.node.values.strings;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.logics.TBool;
import main.java.types.strings.TString;
import main.java.values.atomics.IValue;
import main.java.values.strings.VString;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree, representing an Atomic String.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTString implements ASTNode {
	
	// Global Instance Variables:

	/**
	 * The value of the A.S.T. Node, representing an Atomic String
	 */
	private VString stringASTNodeValue;
	
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree, to represent an Atomic String.
	 * 
	 * @param boolASTNodeValue the value of the A.S.T. Node, representing an Atomic String
	 */
	public ASTString(VString stringASTNodeValue) {
		this.stringASTNodeValue = stringASTNodeValue;
	}
	
	
	// Methods:

	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its Atomic String.
	 * 
	 * @param environment the Environment of the Scope/Frame, containing this A.S.T. Node (String)
	 */
	@Override
	public IValue eval(Environment<IValue> environment) {
		
		// Returns A.S.T. Node, representing an Atomic String
		return this.stringASTNodeValue;
	}
	
	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * pushing an Atomic String to the Evaluation Stack.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet) {
		
		char[] stringCharArrayASTNodeValue = this.stringASTNodeValue.getValue().toCharArray();
		
		int charIndex = 0;
		
		//TODO ArrayRef
		codeBlockInstructionsSet.addCodeInstruction(String.format("sipush %d"));
		
		for(char charFromStringCharArrayASTNodeValue : stringCharArrayASTNodeValue) {
			
			codeBlockInstructionsSet.addCodeInstruction(String.format("dup"));
			codeBlockInstructionsSet.addCodeInstruction(String.format("sipush %d", charIndex));
			codeBlockInstructionsSet.addCodeInstruction(String.format("sipush %d", ((int) charFromStringCharArrayASTNodeValue)));
			
		}
		
	}


	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException {
		
		return TString.getInstance();
	
	}
}