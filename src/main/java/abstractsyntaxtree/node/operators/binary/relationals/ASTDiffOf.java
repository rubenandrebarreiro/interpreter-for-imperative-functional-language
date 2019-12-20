package main.java.abstractsyntaxtree.node.operators.binary.relationals;

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

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;

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
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.logics.TBool;
import main.java.types.mathematics.TInt;
import main.java.values.atomics.IValue;
import main.java.values.logics.VBool;
import main.java.values.mathematics.VInt;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing the Not Equal To Comparison to its descendants.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTDiffOf implements ASTNode {

	// Global Variables:
	
	/**
	 * The left A.S.T. Node descendant
	 */
	private ASTNode leftASTNodeDescendant;
	
	/**
	 * The right A.S.T. Node descendant
	 */
	private ASTNode rightASTNodeDescendant;
	
	/**
	 * The TypeCheck Error Message for the A.S.T. Node for Disequality
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to <> (not equal to) operator!!!";
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The Constructor of a Node of an Abstract Syntax Tree (A.S.T.).
	 * 
	 * @param leftASTNodeDescedant the left side Descendant of the A.S.T. Node
	 * 
	 * @param rightASTNodeDescedant the right side Descendant of the A.S.T. Node
	 * 
	 */
	public ASTDiffOf(ASTNode leftASTNodeDescedant, ASTNode rightASTNodeDescedant) {
		
		this.leftASTNodeDescendant = leftASTNodeDescedant;
		this.rightASTNodeDescendant = rightASTNodeDescedant;
	
	}
	
	
	// Methods/Functions:
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside        
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 * 
	 * @throws NumberArgumentsErrorException a raised Number of Arguments Error Exception,
	 * 		   in the case of the Number of Arguments used in the Evaluation,
	 *         wrong in the current Environment of Values (Scope/Frame) being evaluated
	 *         
	 */
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue leftASTNodeDescendantValue = leftASTNodeDescendant.eval(environment);
		IValue rightASTNodeDescedantValue = rightASTNodeDescendant.eval(environment);
		
		if(leftASTNodeDescendantValue instanceof VInt && rightASTNodeDescedantValue instanceof VInt) {

			// Returns the Not Equal To Comparison of the A.S.T. Nodes Descendants
			return new VBool( ((VInt) leftASTNodeDescendantValue).getValue() != ((VInt) rightASTNodeDescedantValue).getValue());
		
		}
		else if(leftASTNodeDescendantValue instanceof VBool && rightASTNodeDescedantValue instanceof VBool) {

			// Returns the Not Equal To Comparison of the A.S.T. Nodes Descendants
			return new VBool( ((VBool) leftASTNodeDescendantValue).getValue() != ((VBool) rightASTNodeDescedantValue).getValue());
		
		}
		
		throw new TypeErrorException(TYPE_ERROR_MESSAGE);
	}

	/**
	 * Compiles the List of Code Instructions of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope/Frame), where the current A.S.T. Node it's inside and
	 * the List of the Code Instructions of the current Node of an
	 * Abstract Syntax Tree (A.S.T.) will be kept, writing J.V.M. instructions.
	 * 
	 * @param environment the Environment (Scope/Frame), where the current Code Instructions of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be kept
	 * 
	 * @param codeInstructions the List of the Code Instructions to be compiled
	 * 
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 * 
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
		
		int label1 = environmentCompiler.getCurrentLabelNumber();
		int label2 = environmentCompiler.getCurrentLabelNumber();
		
		// Push the Code Instruction of Subtraction (isub) to the Execution Stack,
		// in order to perform the Subtraction of the 2 A.S.T. Nodes
		String instructionSubtraction = String.format("isub");
		codeBlockInstructionsSet.addCodeInstruction(instructionSubtraction);
		
		// Push the Code Instruction of If Not Equal (ifne) to the Execution Stack,
		// in order to perform the Not Equal To Comparison between 2 A.S.T. Nodes
		String instructionIfNotEqual = String.format("ifne L%d", label1);
		codeBlockInstructionsSet.addCodeInstruction(instructionIfNotEqual);
		
		// Push the Code Instruction of Atomic Number 0 (Zero) to the Execution Stack,
		String instructionAtomicNumberZero = String.format("sipush %s", String.valueOf(0));
		codeBlockInstructionsSet.addCodeInstruction(instructionAtomicNumberZero);
		
		// Push the Code Instruction of a Jump (GoTo) to a predefined label on
		// the Execution Stack, in order to perform a branch of
		// an Not Equal To Comparison between 2 A.S.T. Nodes
		String instructionGoToL2 = String.format("goto L%d", label2);
		codeBlockInstructionsSet.addCodeInstruction(instructionGoToL2);
		
		// Push the Code Instruction of a predefined label related to a
		// branch representing an Atomic Number 0 (Zero) to the Execution Stack,
		// in order to perform a branch of a Not Equal To Comparison between 2 A.S.T. Nodes
		String instructionL1Label = String.format("L%d:\nsipush %s", label1, String.valueOf(1));
		codeBlockInstructionsSet.addCodeInstruction(instructionL1Label);
				
		// Push the Code Instruction of a predefined label related to a
		// branch representing an obsolete Value to the Execution Stack,
		// in order to perform a branch of an Not Equal To Comparison between 2 A.S.T. Nodes
		String instructionL2Label = String.format("L%d:", label2);
		codeBlockInstructionsSet.addCodeInstruction(instructionL2Label);
	}	
	
	/**
	 * Evaluates the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 * given the Environment (Scope), where the current A.S.T. Node it's inside.
	 * 
	 * @param environment the Environment (Scope), where the current A.S.T. Node it's inside
	 * 
	 * @return the evaluation of the Expression of the current Node of an Abstract Syntax Tree (A.S.T.),
	 *  	   given the Environment (Scope), where the current A.S.T. Node it's inside        
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames)
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of the Type of a Value it's completely unknown to
	 * 		   the recognised and acceptable Types for Values
	 * 
	 * @throws NumberArgumentsErrorException a raised Number of Arguments Error Exception,
	 * 		   in the case of the Number of Arguments used in the Evaluation,
	 *         wrong in the current Environment of Values (Scope/Frame) being evaluated
	 *         
	 */
	@Override
	public IType typecheck(Environment<IType> environment)
			throws TypeErrorException,
	   		       ASTInvalidIdentifierException,
	   		       ASTDuplicatedIdentifierException,
	   		       NumberArgumentsErrorException {
		
		IType leftASTNodeDescedantType = this.leftASTNodeDescendant.typecheck(environment);
		IType rightASTNodeDescedantType = this.rightASTNodeDescendant.typecheck(environment);
		
		if( (leftASTNodeDescedantType instanceof TBool && rightASTNodeDescedantType instanceof TBool) ||
			(leftASTNodeDescedantType instanceof TInt && rightASTNodeDescedantType instanceof TInt) ) {
		
			return new TBool();
		
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
	}
}