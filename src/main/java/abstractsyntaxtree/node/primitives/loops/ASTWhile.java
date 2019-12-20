package main.java.abstractsyntaxtree.node.primitives.loops;

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
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.primitives.sequences.ASTSeq;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.logics.TBool;
import main.java.values.atomics.IValue;
import main.java.values.logics.VBool;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing a loop conditional-dependent and its set of instructions.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTWhile implements ASTNode {

	// Constants:
	
	/**
	 * The TypeCheck Error Message for the A.S.T. Node for Loop behaviours
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to while (loop) operator!!!";
	
	
	// Global Instance Variables:
	
	/**
	 * The conditional part/component (verification) of the A.S.T. While Node (Loop) descendant
	 */
	private ASTNode conditionASTWhileNodeDescendant;

	/**
	 * The set of instructions part/component
	 * (procedure, in the case of verification be TRUE) of
	 * the A.S.T. While Node descendant
	 */
	private ASTNode instructionSetASTWhileNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - Constructor of the A.S.T. Node for Loop,
	 *   performing a conditional verification and its set of procedures (WHILE);
	 * 
	 * @param conditionASTWhileNodeDescendant the condition of
	 *        the A.S.T. While Node descendant
	 * 
	 * @param instructionSetASTWhileNodeDescendant the set of
	 * 		  instructions part/component
	 * 		  (procedure, in the case of verification be TRUE) of
	 * 		  the A.S.T. While Node descendant
	 */
	public ASTWhile(ASTNode conditionASTWhileNodeDescendant,
					ASTNode instructionSetASTWhileNodeDescendant) {
		
		this.conditionASTWhileNodeDescendant = conditionASTWhileNodeDescendant;
		this.instructionSetASTWhileNodeDescendant = instructionSetASTWhileNodeDescendant;
		
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
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {

		IValue conditionASTWhileNodeDescendantResult = 
						this.conditionASTWhileNodeDescendant.eval(environment);
		
		if(conditionASTWhileNodeDescendantResult instanceof VBool) {
			
			boolean conditionalComponentBooleanResult = 
							( (VBool) conditionASTWhileNodeDescendantResult ).getValue();
					
			while(conditionalComponentBooleanResult) {
				
				
				if(instructionSetASTWhileNodeDescendant instanceof ASTSeq) {
					this.instructionSetASTWhileNodeDescendant.eval(environment);
				}
				else {
					throw new TypeErrorException("Illegal arguments to while (body) set of instructions!!!");
				}
				
				conditionalComponentBooleanResult = 
							( (VBool) conditionASTWhileNodeDescendantResult ).getValue();
			}
			
			return conditionASTWhileNodeDescendantResult;
		}
		else {
			throw new TypeErrorException("Illegal arguments to while (condition) verification!!!");
		}
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
		
		int label1 = environmentCompiler.getCurrentLabelNumber();
		int label2 = environmentCompiler.getCurrentLabelNumber();
		
		// Push the Code Instruction of a predefined label related to a
		// branch representing an obsolete Value to the Execution Stack,
		// in order to perform a branch of a Conditional of A.S.T. Node
		String instructionL1Label = String.format("L%d:", label1);
		codeBlockInstructionsSet.addCodeInstruction(instructionL1Label);
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																	
		this.conditionASTWhileNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of If Equal (ifeq) to the Execution Stack,
		// in order to perform the Equal To Comparison between
		// the evaluation of the IF part/component and the value 0
		// and if the verification holds TRUE, then perform a Jump (GoTo)
		// to the label related with the ELSE part/component
		String instructionIfEqual = String.format("ifeq L%d", label2);
		codeBlockInstructionsSet.addCodeInstruction(instructionIfEqual);
		
		this.instructionSetASTWhileNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
	
		// Push the Code Instruction of a Jump (GoTo) to 
		// a predefined "obsolete" label on the Execution Stack,
		// because the procedure of THEN branch was already performed
		String instructionGoToL1Label = String.format("goto L%d", label1);
		codeBlockInstructionsSet.addCodeInstruction(instructionGoToL1Label);
	
		// Push the Code Instruction of a predefined label related to a
		// branch representing an obsolete Value to the Execution Stack,
		// in order to perform a branch of a Conditional of A.S.T. Node
		String instructionL2Label = String.format("L%d:", label2);
		codeBlockInstructionsSet.addCodeInstruction(instructionL2Label);
		
	}

	/**
	 * Performs the Typechecking for the Type associated to this A.S.T. Node Identifier,
	 * performing the Typecheking on it and in its descendants A.S.T. Nodes,
	 * verifying the Type of the Values of all the A.S.T. Nodes.
	 * 
	 * @param environment the Environment (Scope/Frame), where the types of
	 *        the current Node of an Abstract Syntax Tree (A.S.T.) will be evaluated,
	 *        in a Static Typechecking, before runtime of the program
	 * 
	 * @throws TypeErrorException a Type Error Exception thrown,
	 * 		   in the case of a Type used for in Typechecking of an A.S.T. Node it's
	 * 		   wrong in the current Environment of Types (Scope/Frame) being evaluated
	 *
	 * @throws ASTInvalidIdentifierException an Invalid Identifier Exception thrown,
	 * 		   in the case of an Identifier it's completely unknown in the
	 * 		   Environment's ancestor on the Stack of Environments (Scopes/Frames) 
	 * 
	 * @throws NumberArgumentsErrorException a Number of Arguments Error Exception thrown,
	 *         in the case of the Number of Arguments used in the Typechecking,
	 *         wrong in the current Environment of Types (Scope/Frame) being evaluated
	 *         
	 * @throws ASTDuplicatedIdentifierException a Duplicated Identifier Exception thrown,
	 * 		   in the case of more than one certain Identifier it's found,
	 *         in the current Environment of Types (Scope/Frame) being evaluated
	 *
	 * @return the Type for the A.S.T. Node, after the Typechecking be performed
	 * 
	 */
	@Override
	public IType typecheck(Environment<IType> environment)
			throws TypeErrorException,
	   		       ASTInvalidIdentifierException,
	   		       ASTDuplicatedIdentifierException,
	   		       NumberArgumentsErrorException {
	
		IType conditionASTWhileNodeDescendantType = 
				this.conditionASTWhileNodeDescendant.typecheck(environment);
		
		if(conditionASTWhileNodeDescendantType instanceof TBool) {
			
			return this.instructionSetASTWhileNodeDescendant.typecheck(environment);
		
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
		
	}
}
