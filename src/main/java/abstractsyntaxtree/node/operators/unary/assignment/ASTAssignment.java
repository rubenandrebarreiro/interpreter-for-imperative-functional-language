package main.java.abstractsyntaxtree.node.operators.unary.assignment;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

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
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException, ASTInvalidIdentifierException,
		   NumberArgumentsErrorException, ASTDuplicatedIdentifierException {
		
		return this.assignmentValueType = this.assignmentValue.typecheck(environment);
		
	}
	
}