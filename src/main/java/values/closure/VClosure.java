package main.java.values.closure;

import java.util.List;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.values.atomic.IValue;


public class VClosure implements IValue {

	// TODO generic type of Env
	private Environment closureEnvironment;
	
	private List<String> functionArgumentsNames;
	
	private ASTNode functionBody;
	
	
	public VClosure(Environment<?> closureEnvironment,
			       List<String> functionArgumentsNames, ASTNode functionBody) {
	
		this.closureEnvironment = closureEnvironment;
		this.functionArgumentsNames = functionArgumentsNames;
		this.functionBody = functionBody;
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}