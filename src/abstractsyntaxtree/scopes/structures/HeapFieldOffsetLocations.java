package abstractsyntaxtree.scopes.structures;

import java.util.HashMap;
import java.util.Map;

public class HeapFieldOffsetLocations {
	private Map<String, FieldAddress> offsetLocations;
	
	public HeapFieldOffsetLocations() {
		this.offsetLocations = new HashMap<String, FieldAddress>();
	}
	
	public void addAssoc(String id, int frameID, int value) {
		//TODO Confirm if it exists?
		offsetLocations.put(id, new FieldAddress(frameID, value));
	}
	
	public int findOffsetLocation(String field) {
		return this.offsetLocations.get(field).getOffsetField();
	}
}