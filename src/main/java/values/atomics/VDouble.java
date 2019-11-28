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
 * Class for the VDouble, implementing IValue interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VDouble implements IValue<Double> {
	
	// Global Instance Variables:
	
	/**
	 * The value of a Double Value.
	 */
	private double value;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Double Value;
	 * 
	 * @param value the value of a Double Value
	 */
	public VDouble(double value) {
		this.value = value;
	}
	
	
	// Methods:
	
	/**
	 * Returns the value of a Double Value.
	 * 
	 * @return the value of a Double Value
	 */
	public double getValue() {
		return this.value;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}