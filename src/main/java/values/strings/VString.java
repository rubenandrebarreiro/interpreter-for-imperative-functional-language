package main.java.values.strings;

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
 * 
 * @author rubenandrebarreiro
 *
 */
public class VString implements IValue {
	
	// Global Instance Variables:

	/**
	 * The value of a String Value.
	 */
	private String value;
	
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The constructor of a String Value;
	 * 
	 * @param value the value of a String Value
	 */
	public VString(String value) {
		this.value = value;
	}
	
	
	// Methods/Functions:
	
	/**
	 * Returns the value of a String Value.
	 * 
	 * @return the value of a String Value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Prints the value of a String Value representation in String.
	 */
	@Override
	public void show() {
		System.out.println(this.value);
	}	
}