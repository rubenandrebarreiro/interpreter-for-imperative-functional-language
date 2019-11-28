package main.java.abstractsyntaxtree.node.functions;

import java.util.List;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.values.atomic.IValue;
import main.java.values.closure.VClosure;
import main.java.values.exceptions.TypeErrorException;

public class ASTFun implements ASTNode {
	
	private List<String> functionArgumentsIDs;
	
	private ASTNode functionBody;
	
	public ASTFun(List<String> functionArgumentsIDs, ASTNode functionBody) {
		this.functionArgumentsIDs = functionArgumentsIDs;
		this.functionBody = functionBody;
	}
	
	public List<String> getFunctionArgumentsIDs() {
		return this.functionArgumentsIDs;
	}
	
	public ASTNode getFunctionBody() {
		return this.functionBody;
	}
	
	@Override
	public IValue<?> eval(Environment<?> environment) throws ASTInvalidIdentifierException, TypeErrorException {
	
		return new VClosure(environment, this.functionArgumentsIDs, this.functionBody);
	
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		
		// TODO Auto-generated method stub
		
	}
}