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

/**
 * Class for the Closure Value, implementing Value interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VClosure implements IValue {

	// Global Instance Variables:
	
	/**
	 * The Environment of this Closure Value
	 */
	private Environment<IValue> closureEnvironment;
	
	/**
	 * The Names of Arguments of the Function
	 */
	private List<String> functionArgumentsNames;
	
	/**
	 * The Function Body Expression (its procedures)
	 */
	private ASTNode functionBodyExpression;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - Constructor of a Closure Value;
	 * 
	 * @param closureEnvironment the Environment of this Closure Value
	 * 
	 * @param functionArgumentsNames the Names of Arguments of the Function
	 * 
	 * @param functionBodyExpression the Function Body Expression (its procedures)
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
	 * Returns the Environment of this Closure Value.
	 * 
	 * @return the Environment of this Closure Value
	 */
	public Environment<IValue> getClosureEnvironment() {
		return this.closureEnvironment;
	}

	/**
	 * Returns the Names of Arguments of the Function.
	 * 
	 * @return the Names of Arguments of the Function
	 */
	public List<String> getFunctionArgumentsNames() {
		return this.functionArgumentsNames;
	}

	/**
	 * Returns the Function Body Expression (its procedures).
	 * 
	 * @return the Function Body Expression (its procedures)
	 */
	public ASTNode getFunctionBodyExpression() {
		return this.functionBodyExpression;
	}

	/**
	 * Shows/Prints a String representation of the Value.
	 */
	@Override
	public void show() {
		System.out.println(String.format("fun(%s)", this.functionBodyExpression.toString()));
	}
	
}
