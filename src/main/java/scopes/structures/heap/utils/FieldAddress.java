package main.java.scopes.structures.heap.utils;

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
 * Class for the Field Address, for the Heap Stack Frame.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class FieldAddress {
	
	// Global Instance Variables:

	/**
	 * The level of the Heap Stack Frame (Scope/Frame)
	 */
	private int heapStackFrameLevel;
	
	/**
	 * The Offset of the Field, inside of the Heap Stack Frame (Scope/Frame)
	 */
	private int offsetField;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - 
	 * 
	 * @param heapStackFrameLevel the level of the Heap Stack Frame (Scope/Frame)
	 * 
	 * @param offsetField the he Offset of the Field, inside of the Heap Stack Frame (Scope/Frame)
	 */
	public FieldAddress(int heapStackFrameLevel, int offsetField) {
		
		// The level of the Heap Stack Frame,
		// usually denoted as "frame_index" or "fi"
		this.heapStackFrameLevel = heapStackFrameLevel;
		
		// The Offset Field of the Heap Stack Frame,
		// usually denoted as "variable_index" or "xi"
		this.offsetField = offsetField;
		
	}
	
	
	// Methods:
	
	/**
	 * Returns the level of Heap Stack Frame (Scope/Frame).
	 * 
	 * @return the level of Heap Stack Frame (Scope/Frame)
	 */
	public int getHeapStackFrameLevel() {
		return this.heapStackFrameLevel;
	}
	
	/**
	 * Returns the Offset Field of the Heap Stack Frame (Scope/Frame).
	 * 
	 * @return the Offset Field of the Heap Stack Frame (Scope/Frame)
	 */
	public int getOffsetField() {
		return this.offsetField;
	}

}