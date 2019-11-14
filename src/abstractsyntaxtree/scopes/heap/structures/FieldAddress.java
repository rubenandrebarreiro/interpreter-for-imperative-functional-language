package abstractsyntaxtree.scopes.structures;

public class FieldAddress {
	private int numberOfFrameLevel;
	private int offsetField;
	
	public FieldAddress(int numberOfFrameLevel, int offsetField) {
		this.numberOfFrameLevel = numberOfFrameLevel; //fi
		this.offsetField = offsetField; //xi
	}
	
	public int getNumberOfFrameLevel() {
		return this.numberOfFrameLevel;
	}
	
	public int getOffsetField() {
		return this.offsetField;
	}
}