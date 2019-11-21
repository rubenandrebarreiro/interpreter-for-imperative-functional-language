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
		
		IValue<?> a = this.leftASTNodeDescendant.eval(environment);
		
		return null;
	}
	
	
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
	}
}
