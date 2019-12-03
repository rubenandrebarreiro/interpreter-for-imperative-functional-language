package main.java.values.types;

public interface IType {
	
	String getTypeName();
	String getStackFrameName();
	boolean equals(IType other);
	
}
