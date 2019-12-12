package main.java.types;

public interface IType {
	
	public String getTypeName();
	
	public String getStackFrameName();
	
	public String getStackRefName();
	
	public boolean equals(IType other);
	
	
}
