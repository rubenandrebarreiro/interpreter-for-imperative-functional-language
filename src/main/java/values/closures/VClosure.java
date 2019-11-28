package main.java.values.closures;

import java.util.List;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.values.atomics.IValue;


public class VClosure implements IValue<VClosure> {

	// TODO generic type of Env
	private Environment<IValue<?>> closureEnvironment;
	
	private List<String> functionArgumentsNames;
	
	private ASTNode functionBodyExpression;
	
	
	public VClosure(Environment<IValue<?>> closureEnvironment,
			       List<String> functionArgumentsNames, ASTNode functionBodyExpression) {
	
		this.closureEnvironment = closureEnvironment;
		this.functionArgumentsNames = functionArgumentsNames;
		this.functionBodyExpression = functionBodyExpression;
		
	}

	public Environment<IValue<?>> getClosureEnvironment() {
		return closureEnvironment;
	}

	public List<String> getFunctionArgumentsNames() {
		return functionArgumentsNames;
	}

	public ASTNode getFunctionBodyExpression() {
		return functionBodyExpression;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		// change or create show function
		System.out.println("fun(" + this.functionBodyExpression.toString() + ")");
	}

}