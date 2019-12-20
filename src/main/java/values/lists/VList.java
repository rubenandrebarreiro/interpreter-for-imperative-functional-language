package main.java.values.lists;

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

import main.java.values.atomics.IValue;


public class VList implements IValue {
	
	// Global Instance Variables:
	
	/**
	 * The List/Set of the Values contained in the List Value
	 */
	private List<IValue> listValues;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - Constructor of the List/Set of the Values contained in the List Value;
	 * 
	 * @param listValues the List/Set of the Values contained in the List Value
	 */
	public VList(List<IValue> listValues) {
		this.listValues = listValues;
	}

	
	// Methods/Functions:
	
	/**
	 * Returns the List/Set of the Values contained in the List Value.
	 * 
	 * @return the List/Set of the Values contained in the List Value
	 */
	public List<IValue> getListValues() {
		return this.listValues;
	}
	
	/**
	 * Shows/Prints a String representation of the Value.
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}