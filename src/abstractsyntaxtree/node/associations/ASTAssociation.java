package abstractsyntaxtree.node.associations;

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

import abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import abstractsyntaxtree.node.ASTNode;
import abstractsyntaxtree.scopes.Environment;
import abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructions;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Subtraction of its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTAssociation implements ASTNode {

	// Global Instance Variables:
	/**
	 * The identifier of the A.S.T. Node.
	 */
	private String nodeID;
	
	/**
	 * The value of the A.S.T. Node.
	 */
	private ASTNode nodeValue;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param nodeID the identifier of a Node of an Abstract Syntax Tree (A.S.T.)
	 * @param nodeValue the value of a Node of an Abstract Syntax Tree (A.S.T.)
	 */
	public ASTAssociation(String nodeID, ASTNode nodeValue) {
		this.nodeID = nodeID;
		this.nodeValue = nodeValue;
	}
	
	/**
	 * Evaluates the expression value of the expression, from an A.S.T. Association Node.
	 */
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		environment.addAssoc(nodeID, this.nodeValue.eval(environment));
		
		return this.nodeValue.eval(environment);
	}

	/**
	 * Compiles the Java Byte Code instructions, written in J.V.M.,
	 * of this A.S.T. Node, in order to perform an Association.  
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructions codeBlockInstructions) {
		
		// The counter of the current Field of
		// the variables in the Scope of the Environment 
		int currentFieldCounter = environmentCompiler.getCurrentField();
		environmentCompiler.addAssoc(nodeID);
		
		// Start a new assignment Java Byte Code instruction, written in J.V.M.,
		// for an A.S.T. Association, placed in the Execution Stack
		codeBlockInstructions.addCodeInstruction(";------------------Start new assignment------------------");
		codeBlockInstructions.addCodeInstruction("aload 0");
		
		// Compile the assignment Java Byte Code, written in J.V.M.,
		// for an A.S.T. Association, placed in the Execution Stack,
		// if the value of the A.S.T. Association
		// requires more Java Byte Code instructions, written in J.V.M.
		this.nodeValue.compile(environmentCompiler, codeBlockInstructions);

		// Assigns the Java Byte Code instruction, written in J.V.M.,
		// for an A.S.T. Association, placed in the Execution Stack
		codeBlockInstructions.addCodeInstruction("putfield f" + environmentCompiler.getFrameID() + "/x" + currentFieldCounter + " I");
		
		// End a new assignment Java Byte Code instruction, written in J.V.M.,
		// for an A.S.T. Association, placed in the Execution Stack
		codeBlockInstructions.addCodeInstruction(";------------------End new assignment------------------");
		codeBlockInstructions.addCodeInstruction("\n");
	}
}