package abstractsyntaxtree.node.atomic;

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

import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;
import abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructions;

/**
 * Class for the Node of an Abstract Syntax Tree, representing an atomic number.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTNum implements ASTNode {

	// Global Variables:
	/**
	 * The A.S.T. Node, representing an atomic number
	 */
	private int numASTNodeValue;
		
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree.
	 * 
	 * @param numASTNodeValue the value of the A.S.T. Node, representing an atomic number
	 */
	public ASTNum(int numASTNodeValue) {
		this.numASTNodeValue = numASTNodeValue;
	}
	
	
	// Methods:
	/**
	 * Evaluates the current Node of an Abstract Syntax Tree, returning its atomic number.
	 */
	@Override
	public int eval(Environment env) {
		
		// Returns A.S.T. Node, representing an atomic number
		return this.numASTNodeValue;
	}


	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructions codeBlockInstructions) {
		// TODO Auto-generated method stub
		codeBlockInstructions.addCodeInstruction("sipush " + String.valueOf(this.numASTNodeValue));
	}
}