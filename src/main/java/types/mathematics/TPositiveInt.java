package main.java.types.mathematics;

import main.java.types.IType;

public class TPositiveInt extends TInt implements IType {

	@Override
	public String getTypeName() {
		return "Positive Integer";
	}

	@Override
	public String getStackFrameName() {
		return "I";
	}

	@Override
	public boolean equals(IType other) {
		return other instanceof TPositiveInt;
	}
	
}
