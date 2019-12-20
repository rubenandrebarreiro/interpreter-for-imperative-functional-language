package main.java.values.closures;

/**
 * Interpreter for Imperative/Functional Language
 * 
 * Interpretation and Compilation of Programming Languages
 * 
 * Faculty of Science and Technology of New University of Lisbon
 * (FCT NOVA | FCT/UNL)
 * 
 * Integrated Master of Computer Science and Engineering
 * (BSc. + MSc. Bologna Degree)
 * 
 * Academic Year 2019/2020
 * 
 */

import java.util.List;

import main.java.abstractsyntaxtree.node.ASTNode;
import main.java.scopes.Environment;
import main.java.values.atomics.IValue;


public class VClosure implements IValue {

	// Global Instance Variables:
	
	// TODO generic type of Env
	private Environment<IValue> closureEnvironment;
	
	private List<String> functionArgumentsNames;
	
	private ASTNode functionBodyExpression;
	
	
	// Constructors:
	
	/**
	 * Constructor:
	 * - Constructor of a Closure
	 * 
	 * @param closureEnvironment
	 * @param functionArgumentsNames
	 * @param functionBodyExpression
	 */
	public VClosure(Environment<IValue> closureEnvironment,
			        List<String> functionArgumentsNames,
			        ASTNode functionBodyExpression) {
	
		this.closureEnvironment = closureEnvironment;
		this.functionArgumentsNames = functionArgumentsNames;
		this.functionBodyExpression = functionBodyExpression;
		
	}

	
	// Methods/Functions:
	
	/**
	 * 
	 * @return
	 */
	public Environment<IValue> getClosureEnvironment() {
		return closureEnvironment;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getFunctionArgumentsNames() {
		return functionArgumentsNames;
	}

	/**
	 * 
	 * @return
	 */
	public ASTNode getFunctionBodyExpression() {
		return functionBodyExpression;
	}

	/**
	 * Shows/Prints a String representation of the Value.
	 */
	@Override
	public void show() {
		System.out.println("fun(" + this.functionBodyExpression.toString() + ")");
	}
	
}