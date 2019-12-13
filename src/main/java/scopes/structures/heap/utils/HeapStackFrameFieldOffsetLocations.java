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

public class HeapStackFrameFieldOffsetLocations {
	private Map<String, FieldAddress> offsetLocations;
	
	public HeapStackFrameFieldOffsetLocations() {
		this.offsetLocations = new HashMap<String, FieldAddress>();
	}
	
	public void addAssoc(String id, int frameID, int fieldValue) {
		//TODO Confirm if it exists?
		offsetLocations.put(id, new FieldAddress(frameID, fieldValue));
	}
	
	public FieldAddress findOffsetLocation(String expressionID) {
		return this.offsetLocations.get(expressionID);
	}
	
	public int getCurrentFieldLocation() {
		return offsetLocations.size();
	}
}