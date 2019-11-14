package values.atomic;

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
 * Class for the VFloat, implementing IValue interface.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class VFloat implements IValue<Float> {
	
	// Global Instance Variables:
	
	/**
	 * The value of a Float Value.
	 */
	private float value;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of a Float Value;
	 * 
	 * @param value the value of a Float Value
	 */
	public VFloat(float value) {
		this.value = value;
	}
	
	
	// Methods:
	
	/**
	 * Returns the value of a Float Value.
	 * 
	 * @return the value of a Float Value
	 */
	public float getValue() {
		return this.value;
	}
		
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
}