package abstractsyntaxtree.scopes.structures;

import java.util.HashMap;
import java.util.Map;

public class HeapFieldOffsetLocations {
	private Map<String, FieldAddress> offsetLocations;
	
	public HeapFieldOffsetLocations() {
		this.offsetLocations = new HashMap<String, FieldAddress>();
	}
	
	public void addAssoc(String id, int frameID, int fieldValue) {
		//TODO Confirm if it exists?
		offsetLocations.put(id, new FieldAddress(frameID, fieldValue));
	}
	
	public FieldAddress findOffsetLocation(String field) {
		return this.offsetLocations.get(field);
	}
}