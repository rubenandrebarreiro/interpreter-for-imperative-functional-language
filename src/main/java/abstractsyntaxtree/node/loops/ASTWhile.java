package main.java.abstractsyntaxtree.node.loops;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.sequences.ASTSeq;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.atomics.TBool;
import main.java.values.atomics.IValue;
import main.java.values.atomics.VBool;
import main.java.values.exceptions.NumberArgumentsErrorException;
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

	/**
	 * The TypeCheck Error Message for the A.S.T. Node for Addition
	 */
	private static final String TYPE_ERROR_MESSAGE = "Illegal arguments to while (loop) operator!!!";
	
	
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
	public IValue<?> eval(Environment<?> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {

		IValue<?> conditionASTWhileNodeDescendantResult = 
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

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException {

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
