package main.java.types.atomics;

import main.java.types.IType;

public class TNegativeInt implements IType {

	@Override
	public String getTypeName() {
		return "Negative Integer";
	}

	@Override
	public String getStackFrameName() {
		return "I";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TNegativeInt;
	}

}
