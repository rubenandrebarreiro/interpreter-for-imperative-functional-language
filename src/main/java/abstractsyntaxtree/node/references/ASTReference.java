package main.java.abstractsyntaxtree.node.references;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
import main.java.values.exceptions.TypeErrorException;

public class ASTReference implements ASTNode {

	private ASTNode referenceValue;
	
	
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
		
		codeBlockInstructionsSet.addCodeInstruction("new "); //TODO
		codeBlockInstructionsSet.addCodeInstruction("dup");
		codeBlockInstructionsSet.addCodeInstruction("invokespecial " + "/<init>()V"); //TODO
		codeBlockInstructionsSet.addCodeInstruction("dup");
		
		// TODO
		this.referenceValue.compile(environmentCompiler, codeBlockInstructionsSet);
		
		codeBlockInstructionsSet.addCodeInstruction("putfield " + "/"); //TODO
		
		
	}


	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException, ASTInvalidIdentifierException, NumberArgumentsErrorException {

		return this.referenceValue.typecheck(environment);
		
	}
	
}