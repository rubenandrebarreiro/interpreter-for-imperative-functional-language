package main.java.values.types;

public class ASTIntType implements IType {

	@Override
	public String getTypeName() {
		return "int";
	}

	@Override
	public String getStackFrameName() {
		return "I";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof ASTIntType;
	}

}
