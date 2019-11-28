package main.java.values.closure;

import java.util.List;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.values.atomic.IValue;


public class Closure implements IValue {

	// TODO generic type of Env
	private Environment closureEnvironment;
	
	private List<String> functionArguments;
	
	private ASTNode functionBody;
	
	
	public Closure(Environment<?> closureEnvironment,
			       List<String> functionArguments, ASTNode functionBody) {
	
		this.closureEnvironment = closureEnvironment;
		this.functionArguments = functionArguments;
		this.functionBody = functionBody;
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}