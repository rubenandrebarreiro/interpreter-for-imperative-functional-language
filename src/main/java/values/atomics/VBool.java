package main.java.values.atomics;

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

/**
 * Class for the VBool, implementing IValue interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VBool implements IValue<Boolean> {
	
	// Global Instance Variables:
	
	/**
	 * The value of a Boolean Value.
	 */
	private boolean value;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Boolean Value;
	 * 
	 * @param value the value of a Boolean Value
	 */
	public VBool(boolean value) {
		this.value = value;
	}
	
	
	// Methods:
	
	/**
	 * Returns the value of a Boolean Value.
	 * 
	 * @return the value of a Boolean Value
	 */
	public boolean getValue() {
		return this.value;
	}
	
	/**
	 * Prints the value of an Boolean Value representation in String.
	 */
	@Override
	public void show() {
		System.out.println(this.value);
	}
}