package main.java.values.mathematics;

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

import main.java.values.atomics.IValue;

/**
 * Class for Integer Value, implementing the Value Interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VInt implements IValue {
	
	// Global Instance Variables:

	/**
	 * The value of an Integer Value.
	 */
	private int value;
	
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The constructor of a Negative Integer Value;
	 * 
	 * @param value the value of a Negative Integer Value
	 */
	public VInt(int value) {
		this.value = value;
	}
	
	
	// Methods/Functions:
	
	/**
	 * Returns the value of an Integer Value.
	 * 
	 * @return the value of an Integer Value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Shows/Prints a String representation of the Value.
	 */
	@Override
	public void show() {
		System.out.println(this.value);
	}
	
}
