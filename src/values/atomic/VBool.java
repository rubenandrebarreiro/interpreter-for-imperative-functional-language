package values.atomic;

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
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
