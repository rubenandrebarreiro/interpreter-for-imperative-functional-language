package main.java.abstractsyntaxtree.node.conditionals;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;

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

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.atomic.VBool;
import main.java.values.exceptions.TypeErrorException;

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

	// Global Variables:
	
	/**
	 * The IF (verification) part/component of the A.S.T. Conditional Node descendant
	 */
	private ASTNode ifASTConditionalNodeDescendant;

	/**
	 * The THEN (procedure, in the case of verification be TRUE) part/component of
	 * the A.S.T. Conditional Node descendant
	 */
	private ASTNode thenASTConditionalNodeDescendant;

	/**
	 * The ELSE (procedure, in the case of verification be FALSE) part/component of
	 * the A.S.T. Conditional Node descendant
	 */
	private ASTNode elseASTConditionalNodeDescendant;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - TODO
	 * 
	 * @param ifASTConditionalNodeDescendant
	 * 
	 * @param thenASTConditionalNodeDescendant
	 * 
	 * @param elseASTConditionalNodeDescendant
	 */
	public ASTIfThenElse(ASTNode ifASTConditionalNodeDescendant,
					     ASTNode thenASTConditionalNodeDescendant,
					     ASTNode elseASTConditionalNodeDescendant) {
		
		this.ifASTConditionalNodeDescendant = ifASTConditionalNodeDescendant;
		this.thenASTConditionalNodeDescendant = thenASTConditionalNodeDescendant;
		this.elseASTConditionalNodeDescendant = elseASTConditionalNodeDescendant;
	}
	
	@Override
	public IValue<?> eval(Environment environment) throws ASTInvalidIdentifierException, TypeErrorException {
		
		IValue<?> ifASTConditionalNodeDescendantResult = ifASTConditionalNodeDescendant.eval(environment);
		
		if(ifASTConditionalNodeDescendantResult instanceof VBool) {
			
			boolean ifComponentBooleanResult = ( (VBool) ifASTConditionalNodeDescendantResult ).getValue();
			
			// Stars the Scope (Environment) of the declared expression
			Environment newEnv = environment.beginScope();
			
			IValue<?> finalThenElseResultEvaluation = 
					ifComponentBooleanResult ? 
							this.thenASTConditionalNodeDescendant.eval(environment) : 
								this.elseASTConditionalNodeDescendant.eval(environment);
			
			newEnv.endScope();
			
			return finalThenElseResultEvaluation;
		}
		else {
			throw new TypeErrorException("Illegal arguments to if (conditional) verification!!!");
		}
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler,
						CodeBlockInstructionsSet codeBlockInstructionsSet)
								throws ASTInvalidIdentifierException {
		
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
		String instructionIfEqual = String.format("ifeq L1");
		codeBlockInstructionsSet.addCodeInstruction(instructionIfEqual);
		
		// To Perform the procedure related to
		// the true evaluation of the IF part/component of A.S.T. Node,
		// it's necessary to evaluate the the THEN part/component
		// and push their evaluation to the Execution Stack
		this.thenASTConditionalNodeDescendant
			.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// Push the Code Instruction of a Jump (GoTo) to 
		// a predefined "obsolete" label on the Execution Stack,
		// because the procedure of THEN branch was already performed
		String instructionGoToL2 = String.format("goto L2");
		codeBlockInstructionsSet.addCodeInstruction(instructionGoToL2);
		
		// Push the Code Instruction of a predefined label associated to the procedure
		// related to the true evaluation of the IF part/component of A.S.T. Node,
		// it's necessary to evaluate the the ELSE part/component
		// and push their evaluation to the Execution Stack
		this.elseASTConditionalNodeDescendant
			.compile(environmentCompiler, codeBlockInstructionsSet);
		
		// TODO - confirm
		//String instructionL1Label = String.format("L1: sipush ");
		//codeBlockInstructionsSet.addCodeInstruction(instructionL1Label);
		
		// Push the Code Instruction of a predefined label related to a
		// branch representing an obsolete Value to the Execution Stack,
		// in order to perform a branch of a Conditional of A.S.T. Node
		String instructionL2Label = String.format("L2:");
		codeBlockInstructionsSet.addCodeInstruction(instructionL2Label);
	}
}
