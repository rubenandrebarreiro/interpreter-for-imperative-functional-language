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

import java.util.HashMap;
import java.util.Map;

/**
 * Class for the Field Offset Locations, in the Heap Stack Frame.
 * 
 * @supervisor Prof. Luis Manuel Caires - lcaires@fct.unl.pt
 * 
 * @author Eduardo Bras Silva (no. 41798) - emf.silva@campus.fct.unl.pt
 * @author Ruben Andre Barreiro (no. 42648) - r.barreiro@campus.fct.unl.pt
 *
 */
public class HeapStackFrameFieldOffsetLocations {
	
	// Global Instance Variables:
	
	/**
	 * The Map of the Field Offset Locations, in the Heap Stack Frame
	 */
	private Map<String, FieldAddress> fieldOffsetLocations;
	
	
	// Constructors:
	
	/**
	 * Constructor #1:
	 * - The constructor of the Field Offset Locations, in the Heap Stack Frame;
	 */
	public HeapStackFrameFieldOffsetLocations() {
		this.fieldOffsetLocations = new HashMap<String, FieldAddress>();
	}
	
	
	// Methods/Functions:
	
	/**
	 * Adds a new Field Offset Location to the Heap Stack Frame (Scope/Frame).
	 * 
	 * @param id the ID of the Field Offset Location, to be added to the Heap Stack Frame (Scope/Frame)
	 * 
	 * @param frameID the ID of the Heap Stack Frame (Scope/Frame)
	 * 
	 * @param fieldValue the value of the Field Offset Location to
	 * 		  be added to the Heap Stack Frame (Scope/Frame)
	 */
	public void addAssoc(String id, int frameID, int fieldValue) {
		this.fieldOffsetLocations.put(id, new FieldAddress(frameID, fieldValue));
	}
	
	/**
	 * Returns the Field Address of a Field Offset Location,
	 * given an ID of an Expression.
	 * 
	 * @param expressionID an ID of an Expression,
	 *        contained in Heap Stack Frame (Scope/Frame)
	 * 
	 * @return the Field Address of a Field Offset Location,
	 * 		   given an ID of an Expression
	 */
	public FieldAddress findFieldOffsetLocation(String expressionID) {
		return this.fieldOffsetLocations.get(expressionID);
	}
	
	/**
	 * Returns the current (i.e., the last) Field Offset Location to
	 * added to the Heap Stack Frame (Scope/Frame).
	 * 
	 * @return the current (i.e., the last) Field Offset Location to
	 * 		   added to the Heap Stack Frame (Scope/Frame)
	 */
	public int getCurrentFieldOffsetLocation() {
		return this.fieldOffsetLocations.size();
	}

}