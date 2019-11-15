package main.java.abstractsyntaxtree.node.relationals;

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

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.atomic.VBool;
import main.java.values.atomic.VInt;
import main.java.values.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Greater Than Comparison to its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTGreaterThan implements ASTNode {

	// Global Variables:
	
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescendant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescedant the right side Descendant of the A.S.T. Node
	 */
	public ASTGreaterThan(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		this.leftASTNodeDescendant = leftASTNodeDescedant;
		this.rightASTNodeDescendant = rightASTNodeDescedant;
	}
	
	// Methods:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 * performing the Greater Than Comparison.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope/Frame), where the current A.S.T. Node it's inside,
	 *  	   performing the greater than relationship comparison
	 *  
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Heap Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 */
	@Override
	public IValue<Boolean> eval(Environment environment)
				throws ASTInvalidIdentifierException, TypeErrorException {
		
		IValue<?> leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue<?> rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VInt && rightASTNodeDescedantValue instanceof VInt) {

			// Returns the Greater Than Comparison of the A.S.T. Nodes Descendants
			return new VBool( ((VInt) leftASTNodeDescendantValue).getValue() > ((VInt) rightASTNodeDescedantValue).getValue());
		}
		throw new TypeErrorException("Illegal arguments to > (greater than) operator!!!");
	}

	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions,
	 * in order to, perform a Greater Than Comparison, in the Evaluation Stack.
	 * 
	 * @param environmentCompiler the Environment (Scope/Frame),
	 * 		  where the current Code Instructions Set of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeBlockInstructionsSet the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 */
	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
						CodeBlockInstructionsSet codeBlockInstructionsSet)
										throws ASTInvalidIdentifierException {

		// To Perform the Subtraction of the 2 A.S.T. Nodes,
		// it's necessary to evaluate the both left and right descendants
		// and push their evaluation to the Execution Stack
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of Subtraction (isub) to the Execution Stack,
		// in order to perform the Subtraction of the 2 A.S.T. Nodes
		String instructionSubtraction = String.format("isub");
		codeBlockInstructionsSet.addCodeInstruction(instructionSubtraction);
		
		// Push the Code Instruction of If Greater Than (ifgt) to the Execution Stack,
		// in order to perform the Greater Than comparison between 2 A.S.T. Nodes
		String instructionIfGreaterThan = String.format("ifgt L1");
		codeBlockInstructionsSet.addCodeInstruction(instructionIfGreaterThan);
		
		// Push the Code Instruction of Atomic Number 0 (Zero) to the Execution Stack,
		String instructionAtomicNumberZero = "sipush " + String.valueOf(0);
		codeBlockInstructionsSet.addCodeInstruction(instructionAtomicNumberZero);
		
		// Push the Code Instruction of a Jump (GoTo) to a predefined label on
		// the Execution Stack, in order to perform a branch of
		// a Greater Than comparison between 2 A.S.T. Nodes
		String instructionGoToL2 = String.format("goto L2");
		codeBlockInstructionsSet.addCodeInstruction(instructionGoToL2);
		
		// Push the Code Instruction of a predefined label related to a
		// branch representing an Atomic Number 0 (Zero) to the Execution Stack,
		// in order to perform a branch of a Greater Than comparison between 2 A.S.T. Nodes
		String instructionL1Label = String.format("L1: sipush " + String.valueOf(1));
		codeBlockInstructionsSet.addCodeInstruction(instructionL1Label);
				
		// Push the Code Instruction of a predefined label related to a
		// branch representing an obsolete Value to the Execution Stack,
		// in order to perform a branch of a Greater Than Comparison between 2 A.S.T. Nodes
		String instructionL2Label = String.format("L2:");
		codeBlockInstructionsSet.addCodeInstruction(instructionL2Label);
	}	
}