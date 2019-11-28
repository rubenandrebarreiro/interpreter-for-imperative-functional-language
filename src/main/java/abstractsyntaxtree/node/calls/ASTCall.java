package main.java.abstractsyntaxtree.node.calls;

import java.util.List;

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
	
	private List<ASTNode> functionArgumentsValues;
	
	
	public ASTCall(ASTFun astFunction, List<ASTNode> functionArgumentsValues) {
		this.astFunction = astFunction;
		this.functionArgumentsValues = functionArgumentsValues;
	}
	
	
	@Override
	public IValue<?> eval(Environment<?> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException {
		
		this.astFunction.eval(environment);
		
		
		Environment<?> newEnvironment = new Environment<>();
		
		
		newEnvironment.beginScope();
		
		
		int sizeOfFunctionArgumentsIDs = this.astFunction.getFunctionArgumentsIDs().size();
		
		int sizeOfFunctionArgumentsValues = this.functionArgumentsValues.size();
		
		if(sizeOfFunctionArgumentsIDs != sizeOfFunctionArgumentsValues) {
			
			// TODO - lancar excepacao
		
		}
		
		int numArguments = this.functionArgumentsValues.size(); 
		
		for(int currentArgument = 0; currentArgument < numArguments; currentArgument++) {
			
			// TODO - adicionar associacoes / ir buscar IDs e Values
			//newEnvironment.addAssoc(expressionID, expressionValue);
			
			
		}
		
		this.astFunction.getFunctionBody().eval(newEnvironment);
		
		newEnvironment.endScope();
		
		
		return null;
				
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}
	
}