package main.java.values.references;

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
 * Class for the Reference Value, implementing Value interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VRef implements IValue {

	// Global Instance Variables:
	
	/**
	 * The value of a Reference Value.
	 */
	private IValue value;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Reference Value;
	 * 
	 * @param value the value of a Reference Value
	 */
	public VRef(IValue value) {
		this.value = value;
	}
	
	
	// Methods/Functions:
	
	/**
	 * Returns the value of a Reference Value.
	 * 
	 * @return the value of a Reference Value
	 */
	public IValue getValue() {
		return this.value;
	}
	
	/**
	 * Shows/Prints a String representation of the Value.
	 */
	@Override
	public void show() {

		System.out.println(String.format("Reference to the value ["));
		this.value.show();
		System.out.println(String.format("]"));
		
	}	
	
}