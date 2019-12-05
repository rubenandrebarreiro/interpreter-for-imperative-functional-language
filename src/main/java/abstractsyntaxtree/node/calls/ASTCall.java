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
	
	private List<ASTNode> functionArguments;
	
	
	public ASTCall(ASTFun astFunction, List<ASTNode> functionArguments) {
		this.astFunction = astFunction;
		this.functionArguments = functionArguments;
	}
	
	
	@Override
	public IValue eval(Environment<IValue> environment)
		   throws ASTInvalidIdentifierException, TypeErrorException, NumberArgumentsErrorException {
		
		this.astFunction.eval(environment);
		
		
		Environment<IValue> newEnvironment = new Environment<>();
		
		
		newEnvironment.beginScope();
		
		
		int sizeOfFunctionArgumentsIDs = this.astFunction.getFunctionArgumentsIDs().size();
		
		int sizeOfFunctionArgumentsValues = this.functionArguments.size();
		
		if(sizeOfFunctionArgumentsIDs != sizeOfFunctionArgumentsValues) {
			
			throw new NumberArgumentsErrorException("Illegal Number of Arguments to the Functions: "
												  + "The number of IDs and Values must be the same!!!");
		
		}
		
		int numArguments = this.functionArguments.size(); 
		
		for(int currentArgument = 0; currentArgument < numArguments; currentArgument++) {
			
			String argumentID = this.astFunction.getFunctionArgumentsIDs().get(currentArgument);
			ASTNode argumentValue = this.functionArguments.get(currentArgument);
			
			newEnvironment.addAssoc(argumentID, argumentValue.eval(newEnvironment));
			
		}
		
		IValue functionBodyEvaluationValue = this.astFunction.getFunctionBody().eval(newEnvironment);
		
		newEnvironment.endScope();
		
		
		return functionBodyEvaluationValue;
				
	}

	@Override
	public void compile(EnvironmentCompiler environmentCompiler, CodeBlockInstructionsSet codeBlockInstructionsSet)
			throws ASTInvalidIdentifierException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public IType typecheck(Environment<IType> environment)
		   throws TypeErrorException, NumberArgumentsErrorException, ASTInvalidIdentifierException {

		this.astFunction.typecheck(environment);
		
		
		Environment<IType> newEnvironment = new Environment<>();
		
		
		newEnvironment.beginScope();
		
		
		int sizeOfFunctionArgumentsIDs = this.astFunction.getFunctionArgumentsIDs().size();
		
		int sizeOfFunctionArgumentsTypes = this.functionArguments.size();
		
		if(sizeOfFunctionArgumentsIDs != sizeOfFunctionArgumentsTypes) {
			
			throw new NumberArgumentsErrorException("Illegal Number of Arguments to the Functions: "
												  + "The number of IDs and Types must be the same!!!");
		
		}
		
		int numArguments = this.functionArguments.size(); 
		
		for(int currentArgument = 0; currentArgument < numArguments; currentArgument++) {
			
			String argumentID = this.astFunction.getFunctionArgumentsIDs().get(currentArgument);
			ASTNode argumentType = this.functionArguments.get(currentArgument);
			
			newEnvironment.addAssoc(argumentID, argumentType.typecheck(newEnvironment));
			
		}
		
		IType functionBodyEvaluationValue = this.astFunction.getFunctionBody().typecheck(newEnvironment);
		
		newEnvironment.endScope();
		
		
		return functionBodyEvaluationValue;
		
	}
	
}