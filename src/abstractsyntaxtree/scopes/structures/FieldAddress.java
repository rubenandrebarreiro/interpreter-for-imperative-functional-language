package abstractsyntaxtree.scopes.structures;

public class FieldAddress {
	private int numberOfFrameLevel;
	private int offsetField;
	
	public FieldAddress(int numberOfFrameLevel, int offsetField) {
		this.numberOfFrameLevel = numberOfFrameLevel;
		this.offsetField = offsetField;
	}
	
	public int getNumberOfFrameLevel() {
		return this.numberOfFrameLevel;
	}
	
	public int getOffsetField() {
		return this.offsetField;
	}
}