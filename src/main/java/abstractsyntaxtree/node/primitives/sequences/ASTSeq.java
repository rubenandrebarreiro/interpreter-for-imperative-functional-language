package main.java.abstractsyntaxtree.node.primitives.sequences;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.codeblocks.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;
import main.java.values.utils.exceptions.WrongArgumentTypeErrorException;

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
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		// It's necessary to evaluate the left A.S.T. Node Descendant
		// (i.e., the head element of the Sequence),
		// because it can have secondary effects on the the right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// but it will not be used as the final result of the global evaluation
		this.leftASTNodeDescendant.eval(environment);
		
		// The right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// which will be effectively returned
		IValue rightASTNodeDescendantValue = this.rightASTNodeDescendant.eval(environment);
		
		return rightASTNodeDescendantValue;
	}
	
	
	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		this.leftASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction("pop");
		
		this.rightASTNodeDescendant.compile(environmentCompiler, codeBlockInstructionsSet);
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
		
		// It's necessary to evaluate the left A.S.T. Node Descendant
		// (i.e., the head element of the Sequence),
		// because it can have secondary effects on the the right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// but it will not be used as the final result of the global evaluation
		this.leftASTNodeDescendant.typecheck(environment);
		
		// The right A.S.T. Node Descendant
		// (i.e., the tail elements of the Sequence),
		// which will be effectively returned
		IType rightASTNodeDescendantValue = this.rightASTNodeDescendant.typecheck(environment);
		
		return rightASTNodeDescendantValue;
		
	}
}
