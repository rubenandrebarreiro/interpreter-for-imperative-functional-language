package main.java.values.strings;

import main.java.values.atomics.IValue;

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
	
	
	// Methods:
	
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
