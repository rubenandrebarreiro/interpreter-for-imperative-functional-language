package abstractsyntaxtree.scopes.heap.structures;

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

public class FieldAddress {
	
	// Global Instance Variables:
	/**
	 * The level of the Heap Stack Frame 
	 */
	private int heapStackFrameLevel;
	
	/**
	 * The Offset of the Field, inside of the Heap Stack Frame
	 */
	private int offsetField;
	
	
	// Constructors:
	/**
	 * Contructor #1:
	 * - 
	 * 
	 * @param heapStackFrameLevel
	 * 
	 * @param offsetField
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
	 * Returns the level of Heap Stack Frame.
	 * 
	 * @return the level of Heap Stack Frame
	 */
	public int getHeapStackFrameLevel() {
		return this.heapStackFrameLevel;
	}
	
	/**
	 * Returns the Offset Field of the Heap Stack Frame.
	 * 
	 * @return the Offset Field of the Heap Stack Frame
	 */
	public int getOffsetField() {
		return this.offsetField;
	}
}