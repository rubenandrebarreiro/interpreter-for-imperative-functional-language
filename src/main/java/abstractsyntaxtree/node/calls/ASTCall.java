package main.java.abstractsyntaxtree.node.calls;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.functions.ASTFun;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.exceptions.TypeErrorException;

public class ASTCall implements ASTNode {

	private ASTFun astFunction;
	
	
	public ASTCall(ASTFun astFunction) {
		this.astFunction = astFunction;
	}
	
	
	@Override
	public IValue<?> eval(Environment<?> environment) throws ASTInvalidIdentifierException, TypeErrorException {
		
		return this.astFunction.eval(environment);
		
		
		
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}

}
