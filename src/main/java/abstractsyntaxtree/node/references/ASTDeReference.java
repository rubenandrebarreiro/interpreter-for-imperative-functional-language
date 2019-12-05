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

public class ASTDeReference implements ASTNode {
	
	private ASTNode referenceValueToBeDereferenced;
	
	
	public ASTDeReference(ASTNode referenceValueToBeDereferenced) {
		
		this.referenceValueToBeDereferenced = 
				referenceValueToBeDereferenced;
		
	}


	@Override
	public IValue<?> eval(Environment<?> environment)
			throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		return this.referenceValueToBeDereferenced.eval(environment);
	}


	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public IType typecheck(Environment<IType> environment) throws TypeErrorException {
		// TODO Auto-generated method stub
		return null;
	}
}
