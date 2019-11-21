package main.java.abstractsyntaxtree.node.loops;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.atomic.VBool;
import main.java.values.exceptions.TypeErrorException;

public class ASTWhile implements ASTNode {

	// Global Variables:
	
	/**
	 * The conditional part/component (verification) of the A.S.T. While Node descendant
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
	 * 
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
	
	@Override
	public IValue<?> eval(Environment<?> environment) throws ASTInvalidIdentifierException, TypeErrorException {

		IValue<?> conditionASTWhileNodeDescendantResult = 
						this.conditionASTWhileNodeDescendant.eval(environment);
		
		if(conditionASTWhileNodeDescendantResult instanceof VBool) {
			
			boolean conditionalComponentBooleanResult = 
							( (VBool) conditionASTWhileNodeDescendantResult ).getValue();
			
			// Stars the Scope (Environment) of the declared expression
			Environment<?> newEnv = environment.beginScope();
			
			while(conditionalComponentBooleanResult) {
				this.instructionSetASTWhileNodeDescendant.eval(environment);
				
				conditionalComponentBooleanResult = 
							( (VBool) conditionASTWhileNodeDescendantResult ).getValue();
			}
			
			newEnv.endScope();
			
			return conditionASTWhileNodeDescendantResult;
		}
		else {
			throw new TypeErrorException("Illegal arguments to while (condition) verification!!!");
		}
	}

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
}
