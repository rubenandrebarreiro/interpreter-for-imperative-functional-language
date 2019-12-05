package main.java.abstractsyntaxtree.node.calls;

import java.util.List;

import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.functions.ASTFun;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.abstractsyntaxtree.scopes.compiler.EnvironmentCompiler;
import main.java.abstractsyntaxtree.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.values.atomics.IValue;
import main.java.values.exceptions.NumberArgumentsErrorException;
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
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		this.astFunction.eval(environment);
		
		
		Environment<?> newEnvironment = new Environment<>();
		
		
		newEnvironment.beginScope();
		
		
		int sizeOfFunctionArgumentsIDs = this.astFunction.getFunctionArgumentsIDs().size();
		
		int sizeOfFunctionArgumentsValues = this.functionArgumentsValues.size();
		
		if(sizeOfFunctionArgumentsIDs != sizeOfFunctionArgumentsValues) {
			
			throw new NumberArgumentsErrorException("Illegal Number of Arguments to the Functions: "
												  + "The number of IDs and Values must be the same!!!");
		
		}
		
		int numArguments = this.functionArgumentsValues.size(); 
		
		for(int currentArgument = 0; currentArgument < numArguments; currentArgument++) {
			
			String argumentID = this.astFunction.getFunctionArgumentsIDs().get(currentArgument);
			ASTNode argumentValue = this.functionArgumentsValues.get(currentArgument);
			
			newEnvironment.addAssoc(argumentID, argumentValue.eval(newEnvironment));
		}
		
		IValue<?> functionBodyEvaluationValue = this.astFunction.getFunctionBody().eval(newEnvironment);
		
		newEnvironment.endScope();
		
		
		return functionBodyEvaluationValue;
				
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