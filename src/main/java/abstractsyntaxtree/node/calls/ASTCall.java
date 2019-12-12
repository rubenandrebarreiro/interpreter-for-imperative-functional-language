package main.java.abstractsyntaxtree.node.calls;

import java.util.List;

import main.java.abstractsyntaxtree.exceptions.ASTDuplicatedIdentifierException;
import main.java.abstractsyntaxtree.exceptions.ASTInvalidIdentifierException;
import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.node.operators.nary.functionals.ASTFun;
import main.java.scopes.Environment;
import main.java.scopes.compiler.EnvironmentCompiler;
import main.java.scopes.compiler.instructions.CodeBlockInstructionsSet;
import main.java.types.IType;
import main.java.types.functions.TFun;
import main.java.values.atomics.IValue;
import main.java.values.utils.exceptions.NumberArgumentsErrorException;
import main.java.values.utils.exceptions.TypeErrorException;

public class ASTCall implements ASTNode {

	private static final String TYPE_ERROR_MESSAGE = null;

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
			throws TypeErrorException,
	   		       ASTInvalidIdentifierException,
	   		       ASTDuplicatedIdentifierException,
	   		       NumberArgumentsErrorException {
		
		IType astFunctionType = this.astFunction.typecheck(environment);
		
		if(astFunctionType instanceof TFun) {
			
			return null;
			
		}
		else {
		
			throw new TypeErrorException(TYPE_ERROR_MESSAGE);
			
		}
		
	}
	
}