package main.java.values.atomic;

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
 * Class for the VNegativeInt, implementing IValue interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VNegativeInt implements IValue<Integer> {
	
	// Global Instance Variables:
	
	/**
	 * The value of an Negative Integer Value.
	 */
	private int value;
	
	
	// Constructors:

	/**
	 * Constructor #1:
	 * - The constructor of a Negative Integer Value;
	 * 
	 * @param value the value of a Negative Integer Value
	 */
	public VNegativeInt(int value) {
		this.value = (-1) * value;
	}
	
	
	// Methods:
	
	/**
	 * Returns the value of a Negative Integer Value.
	 * 
	 * @return the value of a Negative Integer Value
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