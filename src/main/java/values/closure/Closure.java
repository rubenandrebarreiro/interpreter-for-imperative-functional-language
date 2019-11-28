package main.java.values.closure;

import java.util.List;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.abstractsyntaxtree.scopes.Environment;
import main.java.values.atomic.IValue;


public class Closure implements IValue {

	// TODO generic type of Env
	private Environment env;
	
	private List<String> arguments;
	
	private ASTNode body;
	
	
	public Closure(Environment<?> env, List<String> arguments, ASTNode body) {
	
		this.env = env;
		this.arguments = arguments;
		this.body = body;
	
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
}