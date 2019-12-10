package main.java.values.mathematics;

import main.java.values.atomics.IValue;

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
	
	
	// Methods:
	
	/**
	 * Returns the value of an Integer Value.
	 * 
	 * @return the value of an Integer Value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Prints the value of a Negative Integer Value representation in String.
	 */
	@Override
	public void show() {
		System.out.println(this.value);
	}
	
}
