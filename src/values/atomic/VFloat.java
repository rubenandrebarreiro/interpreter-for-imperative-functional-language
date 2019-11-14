package values.atomic;

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
