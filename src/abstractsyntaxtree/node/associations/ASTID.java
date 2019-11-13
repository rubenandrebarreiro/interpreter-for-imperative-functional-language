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
import abstractsyntaxtree.scopes.structures.FieldAddress;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the keeping of an identifier for an expression.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTID implements ASTNode {
	
	// Global Variables:
	/**
	 * The Expression's Identifier
	 */
	private String expressionID;
	
	
	// Constructors:
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * @param rightASTNodeDescedant the left side Descendant of the A.S.T. Node
	 */
	public ASTID(String expressionID) {
		this.expressionID = expressionID;
	}
	
	
	// Methods:
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside, performing its association.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside, performing its association        
	 * @throws ASTInvalidIdentifierException 
	 */
	@Override
	public int eval(Environment environment) throws ASTInvalidIdentifierException {
		
		// Returns the value associated to the Expression's ID,
		// in the current Environment (Scope)
		return environment.find(this.expressionID);
	}


	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructions codeBlockInstructions) {
		FieldAddress value = null;
		EnvironmentCompiler tempEnv = environmentCompiler;
		codeBlockInstructions.addCodeInstruction("aload 0");
		try {
			value = environmentCompiler.find(expressionID);
			while(value == null) {
				codeBlockInstructions.addCodeInstruction("getfield f" + tempEnv.getFrameID() + 
						 "/sl Lf" + tempEnv.getAncestor().getFrameID() + ";");
				tempEnv = tempEnv.getAncestor();
				value = tempEnv.find(expressionID);
			}
			codeBlockInstructions.addCodeInstruction("getfield f" + value.getNumberOfFrameLevel() + 
					 "/x" + value.getOffsetField() + " I");
		} catch (ASTInvalidIdentifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
