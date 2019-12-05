package main.java.types.atomics;

import main.java.types.IType;

public class TBool implements IType{
	
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
		return other instanceof TBool;
	}
	
}