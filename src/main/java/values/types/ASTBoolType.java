package main.java.values.types;

public class ASTBoolType implements IType{

	@Override
	public String getTypeName() {
		return "boolean";
	}

	@Override
	public String getStackFrameName() {
		// TODO Change to boolean values
		return "I";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof ASTBoolType;
	}

}
