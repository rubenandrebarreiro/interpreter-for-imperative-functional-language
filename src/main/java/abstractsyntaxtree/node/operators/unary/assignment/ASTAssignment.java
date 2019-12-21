package main.java.abstractsyntaxtree.node.operators.unary.assignment;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.references.VRef;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;
import main.java.values.utils.exceptions.WrongArgumentTypeErrorException;

public class ASTAssignment implements ASTNode {

	private static final String TYPE_ERROR_MESSAGE = 
			"Illegal arguments to Assignment operator:\n"
		  + "- The value used in the respectively Reference it's not of Reference Type!!!";
	
	private ASTNode assignmentID;
	
	private ASTNode assignmentValue;
	
	private IType assignmentValueType;
	
	
	public ASTAssignment(ASTNode assignmentID, ASTNode assignmentValue) {
		
		this.assignmentID = assignmentID;
		this.assignmentValue = assignmentValue;
		
	}
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		IValue assignmentEvaluated = this.assignmentID.eval(environment);
		
		if(assignmentEvaluated instanceof VRef) {
			
			return new VRef(this.assignmentValue.eval(environment));
			
		}
		else {
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
		}
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		this.assignmentID.compile(environmentCompiler, codeBlockInstructionsSet);
		
		String stackRefName = this.assignmentValueType.getHeapStackFrameReferenceName();
		String stackFrameName = this.assignmentValueType.getHeapStackFrameName();
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("checkcast %s", stackRefName) );
		
		this.assignmentValue.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("putfield %s/v %s", stackRefName, stackFrameName) );
		
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
		          NumberArgumentsErrorException,
		          ASTDuplicatedIdentifierException,
		          WrongArgumentTypeErrorException {
		
		return this.assignmentValueType = this.assignmentValue.typecheck(environment);
		
	}
	
}