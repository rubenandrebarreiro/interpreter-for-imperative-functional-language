package main.java.abstractsyntaxtree.node.primitives.conditionals;

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
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.logics.TBool;
import main.java.values.atomics.IValue;
import main.java.values.logics.VBool;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;
import main.java.values.utils.exceptions.WrongArgumentTypeErrorException;

/**
 * Class for the Node of an Abstract Syntax Tree (A.S.T.),
 * performing a conditional verification and its branches of procedures.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class ASTIfThenElse implements ASTNode {

	// Constants:
	
	/**
	 * The TypeCheck Error Message for the A.S.T. Node for Conditional behaviours
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to conditional (if-then-else) operator!!!";
	
	
	// Global Instance Variables:
	
	/**
	 * The IF (verification) part/component of the A.S.T. Conditional Node descendant
	 */
	private ASTNode ifASTConditionalNodeDescendant;

	/**
	 * The THEN part/component (procedure, in the case of verification be TRUE) of
	 * the A.S.T. Conditional Node descendant
	 */
	private ASTNode thenASTConditionalNodeDescendant;

	/**
	 * The ELSE part/component (procedure, in the case of verification be FALSE) of
	 * the A.S.T. Conditional Node descendant
	 */
	private ASTNode elseASTConditionalNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - Constructor of the A.S.T. Node for Conditional,
	 *   performing a conditional verification and its branches of procedures (IF/THEN/ELSE);
	 * 
	 * @param ifASTConditionalNodeDescendant the IF (verification) part/component of
	 *        the A.S.T. Conditional Node descendant
	 * 
	 * @param thenASTConditionalNodeDescendant the THEN part/component (procedure, in the case of verification be TRUE) of
	 * 		  the A.S.T. Conditional Node descendant
	 * 
	 * @param elseASTConditionalNodeDescendant the ELSE part/component (procedure, in the case of verification be FALSE) of
	 * 		  the A.S.T. Conditional Node descendant
	 */
	public ASTIfThenElse(ASTNode ifASTConditionalNodeDescendant,
					     ASTNode thenASTConditionalNodeDescendant,
					     ASTNode elseASTConditionalNodeDescendant) {
		
		this.ifASTConditionalNodeDescendant = ifASTConditionalNodeDescendant;
		this.thenASTConditionalNodeDescendant = thenASTConditionalNodeDescendant;
		this.elseASTConditionalNodeDescendant = elseASTConditionalNodeDescendant;
	
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
		
		IValue ifASTConditionalNodeDescendantResult = 
							this.ifASTConditionalNodeDescendant.eval(environment);
		
		if(ifASTConditionalNodeDescendantResult instanceof VBool) {
			
			boolean ifComponentBooleanResult = 
							( (VBool) ifASTConditionalNodeDescendantResult ).getValue();
			
			
			IValue finalThenElseResultEvaluation = 
					ifComponentBooleanResult ? 
							this.thenASTConditionalNodeDescendant.eval(environment) : 
								this.elseASTConditionalNodeDescendant.eval(environment);
			
			return finalThenElseResultEvaluation;
		}
		else {
			throw new TypeErrorException("Illegal arguments to if (conditional) verification!!!");
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
		
		// To Perform the Conditional of A.S.T. Node,
		// it's necessary to evaluate the IF part/component
		// and push their evaluation to the Execution Stack
		this.ifASTConditionalNodeDescendant
			.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of If Equal (ifeq) to the Execution Stack,
		// in order to perform the Equal To Comparison between
		// the evaluation of the IF part/component and the value 0
		// and if the verification holds TRUE, then perform a Jump (GoTo)
		// to the label related with the ELSE part/component
		String instructionIfEqual = String.format("ifeq L%d", label1);
		codeBlockInstructionsSet.addCodeInstruction(instructionIfEqual);
		
		// To Perform the procedure related to
		// the true evaluation of the IF part/component of A.S.T. Node,
		// it's necessary to evaluate the THEN part/component
		// and push their evaluation to the Execution Stack
		this.thenASTConditionalNodeDescendant
			.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of a Jump (GoTo) to 
		// a predefined "obsolete" label on the Execution Stack,
		// because the procedure of THEN branch was already performed
		String instructionGoToL2 = String.format("goto L%d", label2);
		codeBlockInstructionsSet.addCodeInstruction(instructionGoToL2);
		
		// Push the Code Instruction of a predefined label related to a
		// branch representing the result of a numeric of boolean operation
		String instructionL1Label = String.format("L%d:", label1);
		codeBlockInstructionsSet.addCodeInstruction(instructionL1Label);
		
		// Push the Code Instruction of a predefined label associated to the procedure
		// related to the true evaluation of the IF part/component of A.S.T. Node,
		// it's necessary to evaluate the ELSE part/component
		// and push their evaluation to the Execution Stack
		this.elseASTConditionalNodeDescendant
			.compile(environmentCompiler, codeBlockInstructionsSet);
		
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
	 * @throws WrongArgumentTypeErrorException a Wrong Argument Type Error Exception thrown,
	 * 		   in the case of, at least, one argument of a Closure have a wrong Typechecked Type
	 *
	 * @return the Type for the A.S.T. Node, after the Typechecking be performed
	 * 
	 */
	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException,
	   		      ASTInvalidIdentifierException,
	   		      ASTDuplicatedIdentifierException,
	   		      NumberArgumentsErrorException,
	   		      WrongArgumentTypeErrorException {
		
		IType ifASTConditionalNodeDescendantType = 
				this.ifASTConditionalNodeDescendant.typecheck(environment);
		
		if(ifASTConditionalNodeDescendantType instanceof TBool) {
			
			IType thenASTConditionalNodeDescendantType = 
					this.ifASTConditionalNodeDescendant.typecheck(environment);
			IType elseASTConditionalNodeDescendantType = 
					this.ifASTConditionalNodeDescendant.typecheck(environment);
			
			if(thenASTConditionalNodeDescendantType.equals(elseASTConditionalNodeDescendantType)) {
				return thenASTConditionalNodeDescendantType;
			}
			else {
				
				throw new TypeErrorException(TYPE_ERROR_MESSAGE);	
			
			}
		}
		else {
			
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		
		}
		
	}
	
}