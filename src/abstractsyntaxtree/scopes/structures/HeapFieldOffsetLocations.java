package abstractsyntaxtree.scopes.structures;

import java.util.HashMap;
import java.util.Map;

public class HeapFieldOffsetLocations {
	private Map<String, FieldAddress> offsetLocations;
	
	public HeapFieldOffsetLocations() {
		this.offsetLocations = new HashMap<String, Integer>();
	}
	
	public int findOffsetLocation(String field) {
		return this.offsetLocations.get(field);
	}
}