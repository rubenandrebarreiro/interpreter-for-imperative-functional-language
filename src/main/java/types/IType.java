package main.java.types;

public interface IType {
	
	public String getTypeName();
	
	public String getStackFrameName();
	
	public boolean equals(IType other);
	
	
}
