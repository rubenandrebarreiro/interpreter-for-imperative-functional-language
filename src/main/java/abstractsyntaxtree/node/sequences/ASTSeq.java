package main.java.abstractsyntaxtree.node.sequences;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.exceptions.TypeErrorException;

public class ASTSeq implements ASTNode {

	// Global Instance Variables:
	
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
	 * - 
	 * 
	 * @param leftASTNodeDescendant the A.S.T. Node associated to the head element of the Sequence
	 * 
	 * @param rightASTNodeDescendant the A.S.T. Node associated to the tail elements of the Sequence
	 */
	public ASTSeq(ASTNode leftASTNodeDescendant, ASTNode rightASTNodeDescendant) {
		this.leftASTNodeDescendant = leftASTNodeDescendant;
		this.rightASTNodeDescendant = rightASTNodeDescendant;
	}
	
	
	@Override
	public IValue<?> eval(Environment<?> environment) throws ASTInvalidIdentifierException, TypeErrorException {
		
		// It's necessary to evaluate the left A.S.T. Node Descendant
		// (i.e., the head element of the Sequence),
		// because it can have secondary effects on the the right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// but it will not be used as the final result of the global evaluation
		this.leftASTNodeDescendant.eval(environment);
		
		// The right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// which will be effectively returned
		IValue<?> rightASTNodeDescendantValue = this.rightASTNodeDescendant.eval(environment);
		
		return rightASTNodeDescendantValue;
	}
	
	
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction("pop");
		
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
	}
}
