package main.java.abstractsyntaxtree.node.operators.unary.references;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.references.TRef;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

public class ASTReference<T> implements ASTNode {

	private ASTNode referenceValue;
	
	private TRef referenceType;
	
	
	public ASTReference(ASTNode referenceValue) {
		
		this.referenceValue = referenceValue;
		
	}
	
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException,
		          NumberArgumentsErrorException {
		
		return this.referenceValue.eval(environment);
	
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
		   throws ASTInvalidIdentifierException {
		
		
		String stackRefName = this.referenceType.getStackRefName();
		String stackFrameName = this.referenceType.getStackFrameName();
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("new %s", stackRefName) );
		
		codeBlockInstructionsSet.addCodeInstruction("dup");
		codeBlockInstructionsSet.addCodeInstruction( String.format("invokespecial %s/<init>()V", stackRefName) );
		codeBlockInstructionsSet.addCodeInstruction("dup");
		
		this.referenceValue.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction( String.format("putfield %s/v %s", stackRefName, stackFrameName) );
		
	}


	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException,
	   		      ASTInvalidIdentifierException,
	   		      ASTDuplicatedIdentifierException,
	   		      NumberArgumentsErrorException {
		
		this.referenceType = (TRef) this.referenceValue.typecheck(environment);
		
		return this.referenceType;
		
	}
	
}